package com.psi.psieasymanager.print.bean;

import android.support.annotation.NonNull;
import com.psi.psieasymanager.print.template.ValueStyle;

/**
 * 作者：${ylw} on 2017-05-05 10:21
 * 组件数据
 */
public class PrintComponentData {
  //align
  public static final String TYPE_TEXT = "typeText";
  public static final String TYPE_QR = "typeQR";
  public static final String TYPE_BITMAP = "typeBitmap";


  /**
   * data type (text,QR,bitmap)
   */
  private String type = TYPE_TEXT;

  /**
   * 具体数据  data type == (text:具体内容,QR:二维码内容,bitmap:图片路径)
   */
  @NonNull private String data;
  //字体
  private String font = ValueStyle.FONT_SMALL;
  private int beforeSpace = 0;//前后空格
  private int afterSpace = 0;
  private int row, column;//第几行 第几列(仅在计算具体数据时用)

  public PrintComponentData(int column) {
    this.column = column;
  }

  public PrintComponentData(int row, int column, String font) {
    this.row = row;
    this.font = font;
    this.column = column;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public int getBeforeSpace() {
    return beforeSpace;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public void setBeforeSpace(int beforeSpace) {
    this.beforeSpace = beforeSpace;
  }

  public int getAfterSpace() {
    return afterSpace;
  }

  public void setAfterSpace(int afterSpace) {
    this.afterSpace = afterSpace;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getFont() {
    return font;
  }

  public void setFont(String font) {
    this.font = font;
  }
}
