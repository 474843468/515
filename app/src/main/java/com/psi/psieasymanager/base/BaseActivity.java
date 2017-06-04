package com.psi.psieasymanager.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by psi on 2017/5/11.
 */

public abstract class BaseActivity extends AppCompatActivity {

  abstract protected void setContentView();

  abstract protected void initStatus();

  abstract protected void initInject();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView();
    initStatus();
    initInject();
  }

  /**
   * 通过Id得到view的实例
   */
  protected <T> T findView(int viewId) {
    return (T) findViewById(viewId);
  }

  /**
   * 弹出对话框
   */
  protected void showDialog(String msg) {

  }

  /**
   * 关闭对话框
   */
  protected void dismissDialog() {

  }

  /**
   * 通过类名启动activity
   */
  protected void openActivity(Class<?> clazz) {
    openActivity(clazz, null);
  }

  /**
   * 通过类名启动activity
   */
  protected void openActivity(Context context, Class<?> clazz) {
    Intent intent = new Intent(context, clazz);
    openActivity(intent);
  }

  /**
   * 通过类名带参启动Activity
   */
  protected void openActivity(Class<?> clazz, Bundle bundle) {
    Intent intent = new Intent(this, clazz);
    if (bundle != null) {
      intent.putExtras(bundle);
    }
    openActivity(intent);
  }

  /**
   * 启动Activity
   */
  protected void openActivity(Intent intent) {
    startActivity(intent);
  }

  /**
   * 通过action名启动activity
   */
  protected void openActivity(String action) {
    openActivity(action, null);
  }

  /**
   * 通过action名带参启动activity
   */
  protected void openActivity(String action, Bundle bundle) {
    Intent intent = new Intent(action);
    if (bundle != null) {
      intent.putExtras(bundle);
    }
    openActivity(intent);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }
}
