package com.psi.psieasymanager.main;

import android.content.Context;
import com.psi.psieasymanager.data.bean.User;
import javax.inject.Inject;

/**
 * Created by dorado on 2017/4/21.
 */

public class MainPresenter implements MainContract.Presenter {
  private Context mContext;
  private MainContract.View mView;
  private User mUser;

  @Inject public MainPresenter(Context context, MainContract.View view) {
    mContext = context;
    mView = view;
  }

  @Override public void confirmLogout() {
    mView.showLogoutView();
  }

  @Override public void cancelLogout() {
    mView.closeLogoutHintDialog();
  }

  @Override public void saveLoginUser(User user) {
    mUser = user;
  }

  @Override public void clearLoginUser() {
    mUser = null;
  }
}
