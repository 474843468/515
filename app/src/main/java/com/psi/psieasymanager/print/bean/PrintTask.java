package com.psi.psieasymanager.print.bean;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：${ylw} on 2017-05-05 13:51
 * 发往打印机的数据
 */
public class PrintTask {
  @NonNull private List<PrintLineData> mLineDataList;

  public PrintTask() {
    mLineDataList = new ArrayList<>();
  }

  public List<PrintLineData> getLineDataList() {
    return mLineDataList;
  }
}
