package com.psi.psieasymanager.widget;

import android.app.Activity;
import android.view.View;
import android.widget.PopupWindow;

public class PopWin extends PopupWindow {
  private View mainView;
 // private final LinearLayout mLlSetting;

  public PopWin(Activity paramActivity, View.OnClickListener paramOnClickListener, int paramInt1, int paramInt2){
    super(paramActivity);
  /*  //窗口布局
    mainView = LayoutInflater.from(paramActivity).inflate(R.layout.order_popwin, null);
    mLlSetting = (LinearLayout) mainView.findViewById(R.id.ll_setting);

    //设置每个子布局的事件监听器
    if (paramOnClickListener != null){
      mLlSetting.setOnClickListener(paramOnClickListener);
    }
    setContentView(mainView);
    //设置宽度
    setWidth(paramInt1);
    //设置高度
    setHeight(paramInt2);
    //设置显示隐藏动画
    setAnimationStyle(R.style.AnimTools);
    //设置背景透明
    setBackgroundDrawable(new ColorDrawable(0));*/
  }
}