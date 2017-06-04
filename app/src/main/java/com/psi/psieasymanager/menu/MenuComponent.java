package com.psi.psieasymanager.menu;

import com.psi.psieasymanager.ActivityScoped;
import com.psi.psieasymanager.AppComponent;
import dagger.Component;

@ActivityScoped @Component(modules = MenuModule.class, dependencies = AppComponent.class)
public interface MenuComponent {
  void inject(MenuActivity menuActivity);
}