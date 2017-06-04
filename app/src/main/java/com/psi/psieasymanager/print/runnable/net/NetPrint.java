package com.psi.psieasymanager.print.runnable.net;

import android.support.v4.util.Pair;
import com.psi.psieasymanager.print.bean.PrintDataInfo;
import com.psi.psieasymanager.print.bean.PrintTask;
import com.psi.psieasymanager.print.runnable.IBasePrint;
import com.psi.psieasymanager.print.runnable.PrintRunnable;
import com.psi.psieasymanager.print.temp.PxPrinterInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 作者：${ylw} on 2017-05-04 18:05
 * 网口打印机,打印任务的分发
 */
public final class NetPrint implements IBasePrint.IPrint {
  //开钱箱任务队列 响应打印机的IP
  private Vector<String> mOpenCashBoxVector = null;
  //打印机IP 对应自己的任务队列
  private ConcurrentHashMap<String, PrintRunnable> mPrintRunnableMap = null;
  //打印机IP 对应自己的打印Runnable
  private ConcurrentHashMap<String, ThreadPoolExecutor> mExecutorMap = null;
  //打印机IP 对应自己的打印Thread
  private ConcurrentHashMap<String, LinkedBlockingQueue<PrintTask>> mQueueMap = null;

  private static List<PxPrinterInfo> mCashPrinterList = null;//收银打印机
  private static List<PxPrinterInfo> mKitchenPrinterList = null;//后厨打印及

  /**
   * 初始化打印机  数据更新后
   */
  public static void initPrinterList() {
    //mCashPrinterList = DaoServiceUtil.getPrinterInfoService()
    //    .queryBuilder()
    //    .where(PxPrinterInfoDao.Properties.DelFlag.eq("0"))
    //    .where(PxPrinterInfoDao.Properties.Type.eq(PxPrinterInfo.TYPE_CASH))
    //    .where(PxPrinterInfoDao.Properties.Status.eq(PxPrinterInfo.ENABLE))
    //    .list();
    //mKitchenPrinterList = DaoServiceUtil.getPrinterInfoService()
    //    .queryBuilder()
    //    .where(PxPrinterInfoDao.Properties.DelFlag.eq("0"))
    //    .where(PxPrinterInfoDao.Properties.Status.eq(PxPrinterInfo.ENABLE))
    //    .list();
  }

  //@formatter:off
  /**
   * 开启网口打印 + 标签打印(假如开启)
   */
  @Override
  public boolean onStart() {
    initPrinterList();
    int capacity = mKitchenPrinterList.size() ;//标签打印机

    if (capacity == 0) {
      onDestroy();
      return false;
    }

    mOpenCashBoxVector = new Vector<>(capacity);
    mQueueMap = new ConcurrentHashMap<>(capacity);
    mPrintRunnableMap = new ConcurrentHashMap<>(capacity);
    mExecutorMap = new ConcurrentHashMap<>(capacity);

    for (PxPrinterInfo info : mKitchenPrinterList) {
      String ip = info.getIpAddress();
      LinkedBlockingQueue taskQueue = new LinkedBlockingQueue();
      //Executors.newSingleThreadExecutor();
      ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, taskQueue);
      NetPrintRunnable billRunnable = new NetPrintRunnable(ip,taskQueue,mOpenCashBoxVector);
      executor.execute(billRunnable);
      //put
      mQueueMap.put(ip,taskQueue);
      mPrintRunnableMap.put(ip,billRunnable);
      mExecutorMap.put(ip,executor);
    }
    return true;
  }

  //@formatter:on

  /**
   * destroy 网口打印 + 标签打印(假如开启)
   */
  @Override public void onDestroy() {
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
   * 开钱箱
   */
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

  /**
   * 打印票据任务
   */
  @Override public void receivePrintDataInfo(PrintDataInfo dataInfo) {
    //处理数据,挨个分发

  }

  /**
   * 处理打印数据
   */
  //TODO 获取配置选项
  private List<Pair<String, PrintTask>> processPrintDataInfo(PrintDataInfo dataInfo) {
    String ip = "0.0.0.0";
    List<Pair<String, PrintTask>> pairList = null;
    //if (dataInfo.getMode() == 1) {//结账单
    if (true) {//结账单
      pairList = new ArrayList<>(mCashPrinterList.size());
      PrintTask task = new PrintTask();
    }

    return null;
  }
}
