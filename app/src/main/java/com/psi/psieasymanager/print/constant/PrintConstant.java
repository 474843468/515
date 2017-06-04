package com.psi.psieasymanager.print.constant;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * 作者：${ylw} on 2017-05-04 17:28
 * 打印常量
 */
public class PrintConstant {
  //encode
  public static final String PRINT_ENCODE = "GB2312";
  /**
   * 蓝牙 内置打印机名
   */
  public static final String BT_INNER_PRINTER = "Inner printer";

  //配置信息 正在使用的
  //  UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FA");
  public static final UUID BT_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

  public static String SPACE = null;//补齐用空字符串

  static {
    try {
      SPACE = new String(" ".getBytes(), PRINT_ENCODE);
    } catch (UnsupportedEncodingException e) {
    }
  }
}
