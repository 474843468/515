package com.psi.psieasymanager.print.runnable.net;

import com.psi.psieasymanager.print.bean.PrintTask;
import com.psi.psieasymanager.print.constant.EpsonCommand;
import com.psi.psieasymanager.print.runnable.PrintRunnable;
import com.psi.psieasymanager.utils.IOUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;



/**
 * 作者：${ylw} on 2017-05-05 14:41
 * 网口票据打印
 */
public class NetPrintRunnable extends PrintRunnable {

  private LinkedBlockingQueue<PrintTask> mQueue;//任务队列
  private SocketAddress mSocketAddress;
  private Vector<String> mOpenCashBoxVector;//开钱箱任务
  private String mIp;

  //@formatter:off
  public NetPrintRunnable(String ip, LinkedBlockingQueue<PrintTask> queue, Vector<String> vector) {
    this.mIp = ip;
    this.mQueue = queue;
    this.mOpenCashBoxVector = vector;
    this.mSocketAddress = new InetSocketAddress(ip, 9100);
  }


  //@formatter:on
  private OutputStream os = null;
  private Socket socket = null;


  @Override protected void printRun() {
    PrintTask task = null;
    try {
      //开钱箱
      if (mOpenCashBoxVector.contains(mIp)) {
        connect();
        os.write(EpsonCommand.OPEN_BOX1);
        mOpenCashBoxVector.remove(mIp);
        os.flush();
      } else {// 打印票据
        task = mQueue.take();
        //connect socket
        connect();
        //print
        print(os, task);
        task = null;
      }
    } catch (IOException e) {

      if (task != null) {
        putTask(task, mQueue);
        task = null;//重置
      }
    } catch (Exception e1) {

    } finally {
      IOUtils.closeCloseables(os, socket);
    }
    //catch (InterruptedException e2){} 线程中断
  }

  @Override protected void closeCloseable() {
    IOUtils.closeCloseables(os, socket);
  }

  @Override protected void connect() throws IOException {
    socket = new Socket();
    socket.connect(mSocketAddress, CONNECT_TIME_OUT);
    os = socket.getOutputStream();
  }

  @Override protected void skipEmptyBlockQueue() {
    if(mQueue.isEmpty()){//TODO

    }
  }

}
