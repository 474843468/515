package com.psi.psieasymanager.menu;

import dagger.Module;
import dagger.Provides;


@Module public class MenuModule {
  private MenuContract.View mView;

public MenuModule(MenuContract.View view) {
    this.mView = view;
  }

  @Provides MenuContract.View provideContractContractView() {
    return mView;
  }
}


