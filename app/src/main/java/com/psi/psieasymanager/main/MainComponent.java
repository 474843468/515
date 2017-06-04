package com.psi.psieasymanager.main;

import com.psi.psieasymanager.ActivityScoped;
import com.psi.psieasymanager.AppComponent;
import dagger.Component;

/**
 * Created by dorado on 2017/4/21.
 */

@ActivityScoped @Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {

  void inject(MainActivity mainActivity);
}
