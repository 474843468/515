package com.psi.psieasymanager.print.runnable.label;

import com.psi.psieasymanager.print.runnable.IBasePrint;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 作者：${ylw} on 2017-05-06 17:44
 * 只支持一个标签打印机
 */
public final class LabelPrint implements IBasePrint.ILabelPrint {

  private LabelPrintRunnable mRunnable;
  private ThreadPoolExecutor mExecutor;
  private LinkedBlockingQueue mTaskQueue;

  @Override public void receivePrintLabelTask(List detailsList) {
    if (mTaskQueue != null) {
      try {
        mTaskQueue.put(detailsList);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Override public void switchLabelPrint(boolean isOpen) {

  }

  @Override public boolean onStart() {
    //String ip = info.getIpAddress();
    String ip = "ip";
    int width = 0;
    int height = 0;
    int gap = 0;

    mTaskQueue = new LinkedBlockingQueue();
    //Executors.newSingleThreadExecutor();
    mExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, mTaskQueue);
    mRunnable = new LabelPrintRunnable(ip, width, height, gap, mTaskQueue);
    mExecutor.execute(mRunnable);
    return true;
  }

  @Override public void onDestroy() {
    if (mRunnable != null) {
      mRunnable.stopPrint();
      mRunnable = null;
    }
    if (mExecutor != null) {
      mExecutor.shutdown();
    }
  }
}
