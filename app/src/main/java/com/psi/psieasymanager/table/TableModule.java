package com.psi.psieasymanager.table;

import dagger.Module;
import dagger.Provides;

/**
 * Created by psi on 2017/5/10.
 */

@Module public class TableModule {
  private TableContract.View mView;

  public TableModule(TableContract.View view) {
    this.mView = view;
  }

  @Provides TableContract.View provideTableContractView() {
    return mView;
  }
}
