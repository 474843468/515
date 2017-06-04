package com.psi.psieasymanager.main;

import com.psi.psieasymanager.data.bean.User;

/**
 * Created by dorado on 2017/4/21.
 */

public interface MainContract {

  interface View {
    void showLoginView();

    void showLogoutView();

    void showLogoutHintDialog();

    void closeLogoutHintDialog();

    void toLoginActivity();
  }

  interface Presenter {
    void confirmLogout();

    void cancelLogout();

    void saveLoginUser(User user);

    void clearLoginUser();
  }
}
