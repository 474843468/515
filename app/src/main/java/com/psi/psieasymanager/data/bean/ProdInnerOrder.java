package com.psi.psieasymanager.data.bean;

public class ProdInnerOrder {
  private PxProductInfo mProductInfo;
  private int mNum;

  public PxProductInfo getProductInfo() {
    return mProductInfo;
  }

  public void setProductInfo(PxProductInfo productInfo) {
    mProductInfo = productInfo;
  }

  public ProdInnerOrder(PxProductInfo productInfo, int num) {
    mProductInfo = productInfo;
    mNum = num;
  }

  public int getNum() {
    return mNum;
  }

  public void setNum(int num) {
    mNum = num;
  }
}