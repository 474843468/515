package com.psi.psieasymanager.print.template;

/**
 * 作者：${ylw} on 2017-05-10 15:14
 * 具体组件的PrintTemplateComponents 字体样式、位置
 */
public class ValueStyle {
  public static final String ALIGN_LEFT = "alignLeft";
  public static final String ALIGN_CENTER = "alignCenter";
  public static final String ALIGN_RIGHT = "alignRight";

  //font
  public static final String FONT_SMALL = "fontSmall";
  public static final String FONT_MIDDLE = "fontMiddle";
  public static final String FONT_BIG = "fontBig";

  private String font = FONT_SMALL;
  private String align = ALIGN_LEFT;

  public String getFont() {
    return font;
  }

  public void setFont(String font) {
    this.font = font;
  }

  public String getAlign() {
    return align;
  }

  public void setAlign(String align) {
    this.align = align;
  }
}
