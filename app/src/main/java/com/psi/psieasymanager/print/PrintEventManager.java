package com.psi.psieasymanager.print;

import com.psi.psieasymanager.print.bean.PrintDataInfo;
import com.psi.psieasymanager.print.runnable.IBasePrint;
import com.psi.psieasymanager.print.runnable.bt.BTPrint;
import com.psi.psieasymanager.print.runnable.label.LabelPrint;
import com.psi.psieasymanager.print.runnable.net.NetPrint;
import com.psi.psieasymanager.print.temp.SwitchBTDeviceEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者：${ylw} on 2017-05-05 16:43
 * 分发打印任务
 */
public final class PrintEventManager {
  private PrintEventManager() {
    mService = Executors.newSingleThreadExecutor();
  }

  private ExecutorService mService;

  public static PrintEventManager getManager() {
    return ManagerHolder.sInstance;
  }

  private static class ManagerHolder {
    private static final PrintEventManager sInstance = new PrintEventManager();
  }

  /*********************Net Print************************/
  private IBasePrint.IPrint mINetPrint;

  public void registINetPrint() {
    if (mINetPrint != null) {
      throw new UnsupportedOperationException("IPrint has already been registered");
    }
    mService.execute(new Runnable() {
      @Override public void run() {
        mINetPrint = new NetPrint();
        mINetPrint.onStart();
      }
    });
  }

  public void unRegistINetPrint() {
    if (mINetPrint == null) return;
    mService.execute(new Runnable() {
      @Override public void run() {
        mINetPrint.onDestroy();
        mINetPrint = null;
      }
    });
  }

  //后厨打印
  public void postNetPrintTask(final PrintDataInfo dataInfo) {
    if (mINetPrint == null) return;
    mService.execute(new Runnable() {
      @Override public void run() {
        mINetPrint.receivePrintDataInfo(dataInfo);
      }
    });
  }

  /*********************BT Print************************/

  private IBasePrint.IBTPrint mIBTPrint;

  public void registIBTPrint() {
    if (mIBTPrint != null) {
      throw new UnsupportedOperationException("IBTPrint has already been registered");
    }
    mService.execute(new Runnable() {
      @Override public void run() {
        mIBTPrint = new BTPrint();
        mIBTPrint.onStart();
      }
    });
  }

  public void unRegistIBTPrint() {
    if (mIBTPrint == null) return;
    mService.execute(new Runnable() {
      @Override public void run() {
        mIBTPrint.onDestroy();
        mIBTPrint = null;
      }
    });
  }

  public void postSwitchBTDevice(final SwitchBTDeviceEvent event) {
    if (mIBTPrint == null) return;
    mService.execute(new Runnable() {
      @Override public void run() {
        mIBTPrint.switchBTDevice(event);
      }
    });
  }

  /***********************USB Print*************************/

  private IBasePrint.IUSBPrint mIUSBPrint;

  public void registUSBPrint(IBasePrint.IUSBPrint iusbPrint) {
    if (mIUSBPrint != null) {
      throw new UnsupportedOperationException("IUSBPrint has already been registered");
    }
    //mIUSBPrint = new
  }

  public void unRegistUSBPrint() {
    if (mIUSBPrint == null) return;
    mService.execute(new Runnable() {
      @Override public void run() {
        mIUSBPrint.onDestroy();
        mIUSBPrint = null;
      }
    });
  }

  @Deprecated//
  public void postSwitchUSBPrint(boolean isOpen) {
    if (mIUSBPrint != null) {
      mIUSBPrint.switchUSBPrint(isOpen);
    }
  }

  /***********************Label Print *****************************/
  private IBasePrint.ILabelPrint mILabelPrint;

  public void registLabelPrint(String ip, int width, int height, int gap) {
    if (mILabelPrint != null) {
      throw new UnsupportedOperationException("ILabelPrint has been already registered");
    }
    mService.execute(new Runnable() {
      @Override public void run() {
        mILabelPrint = new LabelPrint();
        mILabelPrint.onStart();
      }
    });
  }

  public void unRegistLabelPrint() {
    if (mILabelPrint == null) return;
    mService.execute(new Runnable() {
      @Override public void run() {
        mILabelPrint.onDestroy();
        mILabelPrint = null;
      }
    });
  }

  /**
   * 标签打印任务
   */
  //public void postPrintLabelTask(final List<PxOrderDetails> detailsList) {
  //  if (mILabelPrint != null || detailsList != null || !detailsList.isEmpty()) return;
  //  mService.execute(new Runnable() {
  //    @Override public void run() {
  //      mILabelPrint.receivePrintLabelTask(detailsList);
  //    }
  //  });
  //}

  /**
   * 标签打印机 开关
   */
  @Deprecated public void switchLabelPrint(boolean isOpen) {
    throw new UnsupportedOperationException("直接使用注册和反注册切换!");
    //if (isOpen) {//开启
    //} else {//关闭
    //  if (mILabelPrint != null) {
    //    //mILabelPrint.switchLabelPrint(isOpen);
    //    mILabelPrint.onDestroy();
    //    mILabelPrint = null;
    //  }
    //}
  }

  /***********************ALL *****************************/
  public void postPrintTask(final PrintDataInfo dataInfo) {
    if (dataInfo == null) return;
    mService.execute(new Runnable() {
      @Override public void run() {
        if (mINetPrint != null) {
          mINetPrint.receivePrintDataInfo(dataInfo);
        }
        if (mIBTPrint != null) {
          mIBTPrint.receivePrintDataInfo(dataInfo);
        }
        if (mIUSBPrint != null) {
          mIUSBPrint.receivePrintDataInfo(dataInfo);
        }
      }
    });
  }

  public void postOpenCashBox() {
    mService.execute(new Runnable() {
      @Override public void run() {
        if (mINetPrint != null) {
          mINetPrint.receiveOpenCashBox();
        }
        if (mIBTPrint != null) {
          mIBTPrint.receiveOpenCashBox();
        }
        if (mIUSBPrint != null) {
          mIUSBPrint.receiveOpenCashBox();
        }
      }
    });
  }
}
