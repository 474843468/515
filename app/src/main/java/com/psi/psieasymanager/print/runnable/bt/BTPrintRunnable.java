package com.psi.psieasymanager.print.runnable.bt;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import com.psi.psieasymanager.print.constant.EpsonCommand;
import com.psi.psieasymanager.print.constant.PrintConstant;
import com.psi.psieasymanager.print.bean.PrintTask;
import com.psi.psieasymanager.print.runnable.PrintRunnable;
import com.psi.psieasymanager.utils.IOUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 作者：${ylw} on 2017-05-05 14:14
 */
public class BTPrintRunnable extends PrintRunnable {
  private LinkedBlockingQueue<PrintTask> mQueue;
  private BluetoothDevice mDevice;
  private String mAddress;
  private boolean mIs58 = false;
  private Vector<String> mOpenCashBoxVector = null;//开钱箱任务队列

  public BTPrintRunnable(LinkedBlockingQueue<PrintTask> queue, BluetoothDevice device,
      String address, boolean is58, Vector<String> vector) {
    this.mQueue = queue;
    this.mDevice = device;
    this.mAddress = address;
    this.mIs58 = is58;
    this.mOpenCashBoxVector = vector;
  }

  BluetoothSocket socket = null;
  OutputStream os = null;

  @Override protected void printRun() {
    PrintTask task = null;
    try {
      if (socket == null || os == null) {
        connect();
      } else {
        if (mOpenCashBoxVector.contains(mAddress)) {//开钱箱
          os.write(EpsonCommand.OPEN_BOX1);
          mOpenCashBoxVector.remove(mAddress);
        } else {
          task = mQueue.take();
          print(os, task);
        }
      }
    } catch (IOException e) {
      if (task != null) {
        putTask(task, mQueue);
      }
      IOUtils.closeCloseables(os, socket);
      //connect();
      os = null;
      socket = null;
    } catch (Exception e1) {

    } finally {//长连接 不关闭

    }
  }

  @Override protected void closeCloseable() {
    IOUtils.closeCloseables(os, socket);
  }

  @Override protected void connect() throws IOException {
    socket = mDevice.createRfcommSocketToServiceRecord(PrintConstant.BT_UUID);
    socket.connect();
    os = socket.getOutputStream();
  }

  @Override protected void skipEmptyBlockQueue() {
    if (mQueue.isEmpty()) {//TODO

    }
  }
}
