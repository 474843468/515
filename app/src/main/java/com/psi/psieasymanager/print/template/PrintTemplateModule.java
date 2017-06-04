package com.psi.psieasymanager.print.template;

import java.util.List;

/**
 * 作者：${ylw} on 2017-05-09 13:42
 * 打印模板模块(标题、基础信息、订单明细、结算信息、支付信息、退货信息、二维码、底栏)
 * 由List<PrintTemplateRow> 组成
 */
public class PrintTemplateModule {
  private int moduleid;
  private String modulename;
  private String moduledescribe;
  private int sort;
  private List<PrintTemplateRow> rows;

  public void setModuleid(int moduleid) {
    this.moduleid = moduleid;
  }

  public int getModuleid() {
    return moduleid;
  }

  public void setModulename(String modulename) {
    this.modulename = modulename;
  }

  public String getModulename() {
    return modulename;
  }

  public void setModuledescribe(String moduledescribe) {
    this.moduledescribe = moduledescribe;
  }

  public String getModuledescribe() {
    return moduledescribe;
  }

  public void setSort(int sort) {
    this.sort = sort;
  }

  public int getSort() {
    return sort;
  }

  public void setRows(List<PrintTemplateRow> rows) {
    this.rows = rows;
  }

  public List<PrintTemplateRow> getRows() {
    return rows;
  }
}
