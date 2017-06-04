package com.psi.psieasymanager.print.runnable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.psi.psieasymanager.print.bean.PrintComponentData;
import com.psi.psieasymanager.print.bean.PrintLineData;
import com.psi.psieasymanager.print.bean.PrintTask;
import com.psi.psieasymanager.print.constant.EpsonCommand;
import com.psi.psieasymanager.print.constant.PrintConstant;
import com.psi.psieasymanager.print.template.ValueStyle;
import com.psi.psieasymanager.utils.BitmapUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 作者：${ylw} on 2017-05-04 17:55
 * 网口 + 蓝牙 共用
 */
public abstract class PrintRunnable implements Runnable {
  protected static final int CONNECT_TIME_OUT = 10 * 1000;//连接超时
  protected boolean isStop = false;

  public synchronized void stopPrint() {
    isStop = true;
    skipEmptyBlockQueue();
    closeCloseable();
  }

  @Override public void run() {
    while (!isStop) {
      printRun();
    }
  }

  /**
   * 跳过阻塞队列、终止线程
   */
  protected abstract void skipEmptyBlockQueue();

  /**
   * 死循环
   */
  protected abstract void printRun();

  /**
   * 关闭Closeable
   */
  protected abstract void closeCloseable();

  /**
   * connect
   */
  protected abstract void connect() throws IOException;

  /**
   * 打印
   * 网口票据打印 + 蓝牙打印用
   *
   * @throws IOException
   */
  protected void print(OutputStream os, PrintTask task) throws IOException {
    List<PrintLineData> lineDataList = task.getLineDataList();
    os.write(EpsonCommand.byteCommands[0]);//复位 字体恢复正常

    for (PrintLineData lineData : lineDataList) {//行数据
      List<PrintComponentData> dataList = lineData.getDataList();
      int size = dataList.size();

      for (int i = 0; i < size; i++) { //组件数据
        PrintComponentData componentData = dataList.get(i);
        int beforeSpace = componentData.getBeforeSpace();
        int afterSpace = componentData.getAfterSpace();
        String data = componentData.getData();
        String font = componentData.getFont();
        //beforeSpace
        if (beforeSpace != 0) {
          os.write(getSpaces(beforeSpace).getBytes(PrintConstant.PRINT_ENCODE));
        }
        //详情数据
        if (!TextUtils.isEmpty(data)) {
          //font
          switch (font) {
            case ValueStyle.FONT_BIG:
              os.write(EpsonCommand.byteCommands[5]);
              break;
            case ValueStyle.FONT_MIDDLE:
              os.write(EpsonCommand.byteCommands[4]);
              break;
            default:
              os.write(EpsonCommand.byteCommands[3]);
              break;
          }
          //data
          switch (componentData.getType()) {
            case PrintComponentData.TYPE_BITMAP://图片
              Bitmap bitmap = BitmapFactory.decodeFile(data);
              byte[] bitmapByte = BitmapUtils.createBitmapByte(bitmap);
              os.write(bitmapByte);
              break;
            case PrintComponentData.TYPE_QR://二维码
              byte[] qrBytes = BitmapUtils.createQRBitmapByte(data);
              os.write(qrBytes);
              break;
            default://文本
              os.write(data.getBytes(PrintConstant.PRINT_ENCODE));
              break;
          }
        }

        //afterSpace
        os.write(EpsonCommand.byteCommands[3]);
        if (i < (size - 1)) {// 多余一列数据时拼接 间隔
          os.write(getSpaces(afterSpace + 2).getBytes(PrintConstant.PRINT_ENCODE));
        }
      }
      os.write("\n".getBytes(PrintConstant.PRINT_ENCODE));//换行
    }
  }

  ///**
  // * 标签打印
  // */
  //protected void printLabel(OutputStream os, PrintLabelTask labelTask) {
  //}

  /**
   * 存放任务
   */
  protected void putTask(Object task, LinkedBlockingQueue queue) {
    if (isStop) return;
    try {
      queue.put(task);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private String getSpaces(int num) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < num; i++) {
      sb.append(PrintConstant.SPACE);
    }
    return sb.toString();
  }
}
