package com.psi.psieasymanager.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.psi.psieasymanager.App;
import com.psi.psieasymanager.R;
import com.psi.psieasymanager.base.BaseActivity;
import com.psi.psieasymanager.main.MainContract;
import com.psi.psieasymanager.main.MainPresenter;
import com.psi.psieasymanager.table.TableActivity;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainContract.View {
  //Presenter
  @Inject MainPresenter mMainPresenter;

  @Override protected void setContentView() {
    setContentView(R.layout.activity_main);
  }

  @Override protected void initStatus() {

  }

  @Override protected void initInject() {
    //注入Presenter
    DaggerMainComponent.builder()
        .appComponent(App.getInstance().getAppComponent())
        .mainModule(new MainModule(this))
        .build()
        .inject(this);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
  }

  /**
   * View
   */
  @Override public void showLoginView() {

  }

  @Override public void showLogoutView() {

  }

  @Override public void showLogoutHintDialog() {

  }

  @Override public void closeLogoutHintDialog() {

  }

  @Override public void toLoginActivity() {

  }

  @OnClick(R.id.btn) public void toTable(Button button) {
    startActivity(new Intent(this, TableActivity.class));
  }
}
