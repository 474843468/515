package com.psi.psieasymanager.print.template;

/**
 * 作者：${ylw} on 2017-05-09 13:46
 * 打印行的 具体组成组件
 */
public class PrintTemplateComponent {
  public static final String LABEL_SHOP_LOG = "店铺Logo";
  public static final String LABEL_SHOP_NAME = "店铺名称";
  public static final String LABEL_BILL_NAME = "票据名称";
  public static final String LABEL_TABLE_NO = "桌台号";
  public static final String LABEL_QR_CODE = "二维码";

  private String label;
  private String servercreatetime;
  private String serverupdatetime;
  private int creatorid;
  private String creatorname;
  private int updatorid;
  private String updatorname;
  private int statusflag;
  private int id;
  private int systemcomponentid;
  private int moduleid;
  private int documenttempletid;
  private String parentid;
  private String refid;
  private String value;
  private String labelstyle;
  private ValueStyle valueStyle;
  private String placeholder;
  //text、gridShowChildren、hidden、
  private String type;
  private String componentproperty;
  private int sort;
  private String row;
  private String column;
  private String width;

  public ValueStyle getValueStyle() {
    return valueStyle;
  }

  public void setValueStyle(ValueStyle valueStyle) {
    this.valueStyle = valueStyle;
  }

  public void setServercreatetime(String servercreatetime) {
    this.servercreatetime = servercreatetime;
  }

  public String getServercreatetime() {
    return servercreatetime;
  }

  public void setServerupdatetime(String serverupdatetime) {
    this.serverupdatetime = serverupdatetime;
  }

  public String getServerupdatetime() {
    return serverupdatetime;
  }

  public void setCreatorid(int creatorid) {
    this.creatorid = creatorid;
  }

  public int getCreatorid() {
    return creatorid;
  }

  public void setCreatorname(String creatorname) {
    this.creatorname = creatorname;
  }

  public String getCreatorname() {
    return creatorname;
  }

  public void setUpdatorid(int updatorid) {
    this.updatorid = updatorid;
  }

  public int getUpdatorid() {
    return updatorid;
  }

  public void setUpdatorname(String updatorname) {
    this.updatorname = updatorname;
  }

  public String getUpdatorname() {
    return updatorname;
  }

  public void setStatusflag(int statusflag) {
    this.statusflag = statusflag;
  }

  public int getStatusflag() {
    return statusflag;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setSystemcomponentid(int systemcomponentid) {
    this.systemcomponentid = systemcomponentid;
  }

  public int getSystemcomponentid() {
    return systemcomponentid;
  }

  public void setModuleid(int moduleid) {
    this.moduleid = moduleid;
  }

  public int getModuleid() {
    return moduleid;
  }

  public void setDocumenttempletid(int documenttempletid) {
    this.documenttempletid = documenttempletid;
  }

  public int getDocumenttempletid() {
    return documenttempletid;
  }

  public void setParentid(String parentid) {
    this.parentid = parentid;
  }

  public String getParentid() {
    return parentid;
  }

  public void setRefid(String refid) {
    this.refid = refid;
  }

  public String getRefid() {
    return refid;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setLabelstyle(String labelstyle) {
    this.labelstyle = labelstyle;
  }

  public String getLabelstyle() {
    return labelstyle;
  }

  public void setPlaceholder(String placeholder) {
    this.placeholder = placeholder;
  }

  public String getPlaceholder() {
    return placeholder;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setComponentproperty(String componentproperty) {
    this.componentproperty = componentproperty;
  }

  public String getComponentproperty() {
    return componentproperty;
  }

  public void setSort(int sort) {
    this.sort = sort;
  }

  public int getSort() {
    return sort;
  }

  public void setRow(String row) {
    this.row = row;
  }

  public String getRow() {
    return row;
  }

  public void setColumn(String column) {
    this.column = column;
  }

  public String getColumn() {
    return column;
  }

  public void setWidth(String width) {
    this.width = width;
  }

  public String getWidth() {
    return width;
  }
}
