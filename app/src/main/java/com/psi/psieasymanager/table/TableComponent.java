package com.psi.psieasymanager.table;

import com.psi.psieasymanager.ActivityScoped;
import com.psi.psieasymanager.AppComponent;
import dagger.Component;

/**
 * Created by psi on 2017/5/10.
 */

@ActivityScoped @Component(modules = TableModule.class,dependencies = AppComponent.class)
public interface TableComponent {
  void inject(TableActivity tableActivity);
}