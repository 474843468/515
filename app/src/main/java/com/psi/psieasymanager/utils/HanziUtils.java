package com.psi.psieasymanager.utils;

import android.text.TextUtils;

/**
 * 作者：${ylw} on 2017-05-09 17:45
 * 查找字符串中汉字数量
 */
public class HanziUtils {
  public static int getHanziNum(String content) {
    String reg = "[^\u4e00-\u9fa5]";
    return content.replaceAll(reg, "").length();
  }

  //public static int getByteLength(String content) {
  //  if (TextUtils.isEmpty(content)) return 0;
  //  int length = content.length();
  //  int hanziNum = getHanziNum(content);
  //  return length + hanziNum;
  //}
  public static int getByteLength(String content, boolean bigFont) {
    if (TextUtils.isEmpty(content)) return 0;

    int length = content.length();
    int hanziNum = getHanziNum(content);
    return (length + hanziNum) * (bigFont ? 2 : 1);
  }
}
