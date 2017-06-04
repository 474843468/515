package com.psi.psieasymanager.main;

import dagger.Module;
import dagger.Provides;

/**
 * Created by dorado on 2017/4/21.
 */

@Module public class MainModule {

  private MainContract.View mView;

  public MainModule(MainContract.View view) {

    this.mView = view;
  }

  @Provides MainContract.View provideMainContractView() {
    return mView;
  }
}
