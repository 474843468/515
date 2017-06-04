package com.psi.psieasymanager.print.runnable;

import com.psi.psieasymanager.print.bean.PrintDataInfo;
import com.psi.psieasymanager.print.temp.SwitchBTDeviceEvent;
import java.util.List;

/**
 * 作者：${ylw} on 2017-05-12 11:06
 */
public interface IBasePrint {
  /**
   * onStart
   *
   * @return 开启成功
   */
  boolean onStart();

  /**
   * onDestroy
   */
  void onDestroy();

  /**
   * 网口打印机用
   */
  interface IPrint extends IBasePrint {
    /**
     * 票据打印
     */
    void receivePrintDataInfo(PrintDataInfo dataInfo);

    /**
     * 开钱箱
     */
    void receiveOpenCashBox();
  }

  /**
   * 蓝牙打印机用
   */
  interface IBTPrint extends IPrint {
    /**
     * 蓝牙打印机 switch
     */
    void switchBTDevice(SwitchBTDeviceEvent event);
  }

  /**
   * 标签打印机用
   */
  interface ILabelPrint extends IBasePrint {
    /**
     * 标签打印任务
     */
    void receivePrintLabelTask(List detailsList);

    /**
     * 标签打印机 开关
     */
    void switchLabelPrint(boolean isOpen);
  }

  /**
   * USB打印机用
   */
  interface IUSBPrint extends IPrint {
    /**
     * USB打印机 开关
     */
    void switchUSBPrint(boolean isOpen);
  }
}
