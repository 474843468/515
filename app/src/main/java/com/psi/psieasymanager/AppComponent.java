package com.psi.psieasymanager;

import android.content.Context;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by dorado on 2017/4/21.
 */

@Singleton @Component(modules = { AppModule.class }) public interface AppComponent {
  App getApp();

  Context getContext();
}

