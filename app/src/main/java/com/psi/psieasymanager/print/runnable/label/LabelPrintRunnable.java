package com.psi.psieasymanager.print.runnable.label;

import com.psi.psieasymanager.print.runnable.PrintRunnable;
import com.psi.psieasymanager.utils.IOUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 作者：${ylw} on 2017-05-06 14:17
 * 标签打印
 */
public class LabelPrintRunnable extends PrintRunnable {

  private int width;
  private int height;
  private int gap;
  private LinkedBlockingQueue mQueue;
  private SocketAddress mSocketAddress;

  public LabelPrintRunnable(String ip, int width, int height, int gap, LinkedBlockingQueue queue) {
    mSocketAddress = new InetSocketAddress(ip, 9100);
    this.width = width;
    this.height = height;
    this.gap = gap;
    this.mQueue = queue;
  }

  @Override protected void closeCloseable() {
    IOUtils.closeCloseables(os, socket);
  }

  @Override protected void connect() throws IOException {
    socket = new Socket();
    socket.connect(mSocketAddress, CONNECT_TIME_OUT);
    os = socket.getOutputStream();
  }

  private Socket socket;
  private OutputStream os;

  @Override protected void printRun() {
    //List<PxOrderDetails> detailsList = null;
    //try {
    //  detailsList = mQueue.take();
    //  connect();
    //  for (PxOrderDetails details : detailsList) {
    //    printLabel(os, details);
    //  }
    //  detailsList = null;
    //} catch (IOException e) {
    //  if (detailsList != null) {
    //    putTask(detailsList, mQueue);
    //  }
    //} catch (Exception e1) {
    //
    //} finally {
    //  IOUtils.closeCloseables(os, socket);
    //}
  }

  /**
   * 标签打印
   */
  //private void printLabel(OutputStream os, PxOrderDetails details) throws IOException {
  //
  //}

  @Override protected void skipEmptyBlockQueue() {
    if (mQueue.isEmpty()) {//TODO

    }
  }
}
