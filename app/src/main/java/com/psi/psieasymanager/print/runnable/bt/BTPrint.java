package com.psi.psieasymanager.print.runnable.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.psi.psieasymanager.App;
import com.psi.psieasymanager.print.bean.PrintDataInfo;
import com.psi.psieasymanager.print.bean.PrintTask;
import com.psi.psieasymanager.print.constant.PrintConstant;
import com.psi.psieasymanager.print.runnable.IBasePrint;
import com.psi.psieasymanager.print.runnable.PrintRunnable;
import com.psi.psieasymanager.print.temp.BTPrintDevice;
import com.psi.psieasymanager.print.temp.SwitchBTDeviceEvent;
import com.psi.psieasymanager.utils.ToastUtil;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 作者：${ylw} on 2017-05-06 15:04
 * 蓝牙打印机
 */
public final class BTPrint implements IBasePrint.IBTPrint {

  //开钱箱任务队列 响应打印机的IP
  private Vector<String> mOpenCashBoxVector = null;
  //蓝牙地址 对应自己的任务队列
  private ConcurrentHashMap<String, PrintRunnable> mPrintRunnableMap = null;
  //蓝牙地址 对应自己的打印Runnable
  private ConcurrentHashMap<String, ThreadPoolExecutor> mExecutorMap = null;
  //蓝牙地址 对应自己的打印Thread
  private ConcurrentHashMap<String, LinkedBlockingQueue<PrintTask>> mQueueMap = null;

  private BluetoothAdapter mBTAdapter;
  private BTBroadcastReceiver mBroadcastReceiver;

  //@formatter:off
  @Override
  public boolean onStart() {
    mBTAdapter = BluetoothAdapter.getDefaultAdapter();
    //final List<BTPrintDevice> dbDeviceList = DaoServiceUtil.getBTDeviceService().queryAll();
    final List<BTPrintDevice> dbDeviceList = null;

    //Get a set of currently paired devices
    final Set<BluetoothDevice> pairedDevices = mBTAdapter.getBondedDevices();
    // If there are paired devices, add each one to the ArrayAdapter
    if (pairedDevices == null || pairedDevices.isEmpty()) {
      if (!dbDeviceList.isEmpty()) {
        //DaoServiceUtil.getBTDeviceService().deleteAll();
        //TODO delete
        ToastUtil.shortShow( "所有蓝牙打印机都已取消配对");
      }
      onDestroy();
      return false;
    }

    int capacity = pairedDevices.size();
    mOpenCashBoxVector = new Vector<>(capacity);
    mQueueMap = new ConcurrentHashMap<>(capacity);
    mPrintRunnableMap = new ConcurrentHashMap<>(capacity);
    mExecutorMap = new ConcurrentHashMap<>(capacity);
    //先找出DX065内置打印机
    for (BluetoothDevice device : pairedDevices) {
      if (PrintConstant.BT_INNER_PRINTER.equals(device.getName().trim())) {
        addPrintRunnable(device,false);
        break;
      }
    }

    //再启动数据库对应的
    if (!dbDeviceList.isEmpty()) {
      for (BTPrintDevice btDbDevice : dbDeviceList) {
        String address = btDbDevice.getAddress();
        BluetoothDevice device = isPair(address, pairedDevices);
        if (device != null) {
          boolean is58 = BTPrintDevice.FORMAT_58.equals(btDbDevice.getFormat());
          addPrintRunnable(device, is58);
        } else {
          ToastUtil.shortShowAsync(device.getName() + "(" + device.getAddress() + ")" + " 已被取消配对");
          //DaoServiceUtil.getBTDeviceService().delete(btDbDevice);
          //TODO delete
        }
      }
    }
    registReceiver();
    return true;
  }

  /**
   * 添加打印Runnable
   */
  private void addPrintRunnable(BluetoothDevice device, boolean is58) {
    String address = device.getAddress();
    LinkedBlockingQueue taskQueue = new LinkedBlockingQueue();
    //Executors.newSingleThreadExecutor();
    ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, taskQueue);
    BTPrintRunnable runnable = new BTPrintRunnable(taskQueue, device, address, is58, mOpenCashBoxVector);
    executor.execute(runnable);
    //put
    mQueueMap.put(address, taskQueue);
    mPrintRunnableMap.put(address, runnable);
    mExecutorMap.put(address, executor);
  }

  /**
   * 改地址蓝牙是否在配对中
   */
  private BluetoothDevice isPair(String address, Set<BluetoothDevice> pairedDevices) {
    for (BluetoothDevice device : pairedDevices) {
      if (address.equals(device.getAddress())) return device;
    }
    return null;
  }

  /**
   * 获取已配对的蓝牙
   */
  private BluetoothDevice getDeviceByAddress(String address){
    Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
    for (BluetoothDevice bondedDevice : bondedDevices) {
      if (bondedDevice.getAddress().equals(address)) return bondedDevice;
    }
    return null;
  }


  //@formatter:off
  /**
   * 监听蓝牙事件广播
   */
  private void registReceiver() {
    //receiver
    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
    filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
    filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
    filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
    // 注册广播接收器，接收并处理搜索结果
    mBroadcastReceiver = new BTBroadcastReceiver();
    Context context = App.getInstance();
    if (context!= null) {
      context.registerReceiver(mBroadcastReceiver, filter);
    }
  }

  //@formatter:on
  @Override public void onDestroy() {
    Context context = App.getInstance();
    if (mBroadcastReceiver != null && context != null) {
      context.unregisterReceiver(mBroadcastReceiver);
    }

    //1.清空钱箱
    if (mOpenCashBoxVector != null) {
      mOpenCashBoxVector.clear();
      mOpenCashBoxVector = null;
    }
    //2.停止打印
    if (mPrintRunnableMap != null) {
      for (String printRunnableKey : mPrintRunnableMap.keySet()) {
        PrintRunnable printRunnable = mPrintRunnableMap.get(printRunnableKey);
        printRunnable.stopPrint();
        printRunnable = null;
      }
      mPrintRunnableMap.clear();
      mPrintRunnableMap = null;
    }

    //2.
    if (mExecutorMap != null) {
      for (String executorKey : mExecutorMap.keySet()) {
        ThreadPoolExecutor executor = mExecutorMap.get(executorKey);
        executor.shutdown();
        executor = null;
      }
      mExecutorMap.clear();
      mExecutorMap = null;
    }

    //3.清空任务队列
    if (mQueueMap != null) {
      for (String queueKey : mQueueMap.keySet()) {
        LinkedBlockingQueue<PrintTask> queue = mQueueMap.get(queueKey);
        queue.clear();
        queue = null;
      }
      mQueueMap.clear();
      mQueueMap = null;
    }
  }

  /**
   * 打印票据任务
   */
  @Override public void receivePrintDataInfo(PrintDataInfo dataInfo) {
    //处理数据
    //挨个分发
  }

  @Override public void receiveOpenCashBox() {
    //支持钱箱的挨个分发
    Set<String> keySet = mQueueMap.keySet();
    for (String ipKey : keySet) {
      LinkedBlockingQueue<PrintTask> queue = mQueueMap.get(ipKey);
      if (queue.isEmpty()) {//TODO 添加空任务跳过去

      }
      //添加开钱箱任务
      if (!mOpenCashBoxVector.contains(ipKey)) {
        mOpenCashBoxVector.add(ipKey);
      }
    }
  }

  @Override public void switchBTDevice(SwitchBTDeviceEvent event) {
    //添加打印队列中
    BTPrintDevice dbDbDevice = event.getDevice();
    String address = dbDbDevice.getAddress();
    boolean aSwitch = event.isSwitch();
    if (aSwitch) {
      if (!mPrintRunnableMap.contains(address)) {
        boolean is58 = BTPrintDevice.FORMAT_58.equals(dbDbDevice.getFormat());
        BluetoothDevice device = getDeviceByAddress(address);
        if (device != null) {
          addPrintRunnable(device, is58);
        }
      }
    } else {
      if (!mPrintRunnableMap.containsKey(address)) return;
      closeDevice(address);
    }
  }

  /**
   * 关闭蓝牙打印
   */
  private void closeDevice(String address) {

    PrintRunnable printRunnable = mPrintRunnableMap.get(address);
    printRunnable.stopPrint();
    mPrintRunnableMap.remove(address);
    printRunnable = null;

    ThreadPoolExecutor executor = mExecutorMap.get(address);
    executor.shutdownNow();
    mExecutorMap.remove(address);
    executor = null;

    LinkedBlockingQueue<PrintTask> queue = mQueueMap.get(address);
    queue.clear();
    mQueueMap.remove(address);
    queue = null;
  }

  /**
   * bound state changed
   */
  //@formatter:on
  private class BTBroadcastReceiver extends BroadcastReceiver {
    @Override public void onReceive(Context context, Intent intent) {
      String action = intent.getAction();
      BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
      if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
        // 状态改变的广播
        String name = device.getName();
        if (device.getName().equalsIgnoreCase(name)) {
          int connectState = device.getBondState();
          switch (connectState) {
            case BluetoothDevice.BOND_NONE:  //10
              ToastUtil.shortShow("取消配对：" + device.getName());

              break;
            case BluetoothDevice.BOND_BONDING:  //11
              ToastUtil.shortShow("正在配对：" + device.getName());
              break;
            case BluetoothDevice.BOND_BONDED:   //12
              ToastUtil.shortShow("完成配对：" + device.getName());
              break;
          }
        }
      } else if (action.equals(BluetoothDevice.ACTION_ACL_CONNECTED)) { //连接
        ToastUtil.shortShow(device.getName() + "蓝牙-连接成功");
        Log.d("Fire", device.getName() + " ACTION_ACL_CONNECTED");
      } else if (action.equals(BluetoothDevice.ACTION_ACL_DISCONNECTED)) { //断开连接
        Log.d("Fire", device.getName() + " ACTION_ACL_DISCONNECTED");
        ToastUtil.shortShow(device.getName() + "蓝牙-断开连接");
      }
      //else if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) { //蓝牙关闭
      //  int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
      //  switch (state) {
      //    case BluetoothAdapter.STATE_OFF:
      //      ToastUtils.showShort(context, "蓝牙已关闭，无法使用蓝牙打印!");
      //      stopSelf();
      //      break;
      //    case BluetoothAdapter.STATE_TURNING_OFF:
      //      ToastUtils.showShort(context, "蓝牙正在关闭，无法使用蓝牙打印!");
      //      break;
      //    case BluetoothAdapter.STATE_ON:
      //      ToastUtils.showShort(context, "蓝牙开启!");
      //      startService(new Intent(context, BTPrintService.class));
      //      break;
      //    case BluetoothAdapter.STATE_TURNING_ON:
      //      ToastUtils.showShort(context, "蓝牙正在开启!");
      //      break;
      //  }
      //}
    }
  }
}
