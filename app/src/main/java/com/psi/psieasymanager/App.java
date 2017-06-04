package com.psi.psieasymanager;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.psi.psieasymanager.db.DbCore;

/**
 * Created by dorado on 2017/4/21.
 */

public class App extends Application {
  private static App sApp;
  private AppComponent mAppComponent;

  public static App getInstance() {
    return sApp;
  }

  @Override public void onCreate() {
    super.onCreate();
    sApp = this;
    initComponent();
    initDatabase();
    initStetho();
  }

  /**
   * 初始化数据库
   */
  private void initDatabase() {
    DbCore.init(this);
    DbCore.enableQueryBuilderLog();
  }

  /**
   * 初始化AppComponent
   */
  private void initComponent() {
    mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
  }

  /**
   * Getter AppComponent
   */
  public AppComponent getAppComponent() {
    return mAppComponent;
  }

  /**
   * Stetho
   */
  private void initStetho() {
    Stetho.initializeWithDefaults(this);
  }
}
