package com.psi.psieasymanager.print.template;

import android.util.SparseArray;

/**
 * 作者：${ylw} on 2017-05-09 14:34
 */
public class PrintTemplate {
  public static final int MODULEID_TITLE = 1;//标题
  public static final int MODULEID_BASIC_INFO = 2;//基础信息
  public static final int MODULEID_ORDER_DETAIL = 3;//订单明细
  public static final int MODULEID_CHECKOUT_INFO = 4;//结算信息
  public static final int MODULEID_PAY_INFO = 5;//支付信息
  public static final int MODULEID_REFUND_INFO = 6;//退货信息
  public static final int MODULEID_QR_CODE = 7;//二维码
  public static final int MODULEID_BOTTOM_BAR = 8;//底栏

  private SparseArray<PrintTemplateModule> mArray;//key: moduleId ,value:PrintTemplateModule

  public SparseArray<PrintTemplateModule> getArray() {
    return mArray;
  }

  public void setArray(SparseArray<PrintTemplateModule> array) {
    mArray = array;
  }
}
