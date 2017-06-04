package com.psi.psieasymanager.print.runnable.usb;

import com.psi.psieasymanager.print.bean.PrintDataInfo;
import com.psi.psieasymanager.print.runnable.IBasePrint;

/**
 * 作者：${ylw} on 2017-05-11 11:21
 */
public class USBPrint implements IBasePrint.IUSBPrint {
  @Override public void receivePrintDataInfo(PrintDataInfo dataInfo) {

  }

  @Override public void receiveOpenCashBox() {

  }

  @Override public boolean onStart() {
    return true;
  }

  @Override public void onDestroy() {

  }

  @Override public void switchUSBPrint(boolean isOpen) {

  }
}
