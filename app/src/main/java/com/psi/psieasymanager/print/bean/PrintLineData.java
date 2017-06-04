package com.psi.psieasymanager.print.bean;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：${ylw} on 2017-05-04 17:23
 * 打印机的一行的数据
 */
public class PrintLineData {
  private static final String DIVIDER_LINE_58 = "--------------------------------";
  private static final String DIVIDER_LINE_80 = "------------------------------------------------";

  @NonNull private List<PrintComponentData> mDataList;

  private boolean aboveBlankLine = false;//上方空行
  private boolean belowDividerLine = false;//底下分割线

  public List<PrintComponentData> getDataList() {
    return mDataList;
  }

  public PrintLineData() {
    mDataList = new ArrayList<>();
  }

  public boolean isAboveBlankLine() {
    return aboveBlankLine;
  }

  public void setAboveBlankLine(boolean aboveBlankLine) {
    this.aboveBlankLine = aboveBlankLine;
  }

  public boolean isBelowDividerLine() {
    return belowDividerLine;
  }

  public void setBelowDividerLine(boolean belowDividerLine) {
    this.belowDividerLine = belowDividerLine;
  }

  /**
   * 分割线
   */
  public static PrintLineData dividerLine(boolean is58) {
    PrintLineData lineData = new PrintLineData();
    PrintComponentData moduleData = new PrintComponentData(0);
    moduleData.setData(is58 ? DIVIDER_LINE_58 : DIVIDER_LINE_80);
    lineData.mDataList.add(moduleData);
    return lineData;
  }

  /**
   * 空行
   */
  public static PrintLineData spaceLine() {
    PrintLineData lineData = new PrintLineData();
    PrintComponentData moduleData = new PrintComponentData(0);
    moduleData.setData(" \n");
    lineData.mDataList.add(moduleData);
    return lineData;
  }
}
