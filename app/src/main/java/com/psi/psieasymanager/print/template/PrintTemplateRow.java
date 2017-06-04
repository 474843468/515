package com.psi.psieasymanager.print.template;

import java.util.List;

/**
 * 作者：${ylw} on 2017-05-09 13:47
 * 打印模板下的行
 * 由List<PrintTemplateComponent> 具体组成组件组成
 */
public class PrintTemplateRow {
  //text、grid、
  private String type;
  private boolean showline;
  private int blanknumber;
  private int sort;
  private List<PrintTemplateComponent> components;

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setShowline(boolean showline) {
    this.showline = showline;
  }

  public boolean getShowline() {
    return showline;
  }

  public void setBlanknumber(int blanknumber) {
    this.blanknumber = blanknumber;
  }

  public int getBlanknumber() {
    return blanknumber;
  }

  public void setSort(int sort) {
    this.sort = sort;
  }

  public int getSort() {
    return sort;
  }

  public void setComponents(List<PrintTemplateComponent> components) {
    this.components = components;
  }

  public List<PrintTemplateComponent> getComponents() {
    return components;
  }
}
