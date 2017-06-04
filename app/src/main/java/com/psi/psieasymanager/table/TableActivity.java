package com.psi.psieasymanager.table;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.psi.psieasymanager.App;
import com.psi.psieasymanager.R;
import com.psi.psieasymanager.adapter.TableAdapter;
import com.psi.psieasymanager.adapter.TableAreaAdapter;
import com.psi.psieasymanager.base.BaseActivity;
import com.psi.psieasymanager.data.bean.PxTableArea;
import com.psi.psieasymanager.data.bean.PxTableInfo;
import com.psi.psieasymanager.menu.MenuActivity;
import com.psi.psieasymanager.widget.RecyclerViewSpaceItemDecoration;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by psi on 2017/5/10.
 */

public class TableActivity extends BaseActivity
    implements TableContract.View, TableAdapter.OnTableClickListener, View.OnClickListener {

  //Presenter
  @Inject TablePresenter mTablePresenter;

  private ListView mLvTableType;
  private RecyclerView mRcvTable;
  private PopupWindow mPopupWindow;
  private LinearLayout mLayout;
  private ImageView mWaiterChoice;
  private LinearLayout mSet;

  private TableAreaAdapter mAdapter;
  private List<PxTableArea> mTableAreaList = new ArrayList<>();

  private TableAdapter mTableAdapter;
  private List<PxTableInfo> mTableInfoList = new ArrayList<>();

  private PxTableInfo tableInfo;//选中的桌台

  @Override protected void setContentView() {
    setContentView(R.layout.activity_table);
  }

  @Override protected void initStatus() {
    mLvTableType = (ListView) findViewById(R.id.lv_table_type);
    mAdapter = new TableAreaAdapter(this, mTableAreaList);
    mLvTableType.setAdapter(mAdapter);
    mLvTableType.setItemChecked(0, true);

    mRcvTable = (RecyclerView) findViewById(R.id.rcv_table);
    mTableAdapter = new TableAdapter(this, mTableInfoList);
    mRcvTable.setHasFixedSize(true);
    GridLayoutManager mManager =
        new GridLayoutManager(this, 5, LinearLayoutManager.VERTICAL, false);
    mRcvTable.setLayoutManager(mManager);
    mRcvTable.setAdapter(mTableAdapter);
    int spaceWidth =
        getResources().getDimensionPixelSize(R.dimen.find_bill_rcv_item_horizontal_space_width);
    int spaceHeight =
        getResources().getDimensionPixelSize(R.dimen.find_bill_rcv_item_vertical_space_height);
    mRcvTable.addItemDecoration(new RecyclerViewSpaceItemDecoration(spaceWidth, spaceHeight));
    mTableAdapter.setOnTableClickListener(this);

    mLayout = (LinearLayout) findViewById(R.id.ll_hint_more_function);
    mLayout.setOnClickListener(this);
    mWaiterChoice = (ImageView) findViewById(R.id.im_waiter_choice);
    mWaiterChoice.setOnClickListener(this);
    mSet = (LinearLayout) findViewById(R.id.ll_set);

    ImageView mIvBack =  (ImageView)findViewById(R.id.iv_back);
    mIvBack.setVisibility(View.GONE);
  }

  @Override protected void initInject() {
    //注入Presenter
    DaggerTableComponent.builder()
        .appComponent(App.getInstance().getAppComponent())
        .tableModule(new TableModule(this))
        .build()
        .inject(this);

    mTablePresenter.loadTableAreas();
    mTablePresenter.loadTableList();
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public void showTableAreas(List<PxTableArea> tableAreaList) {
    mAdapter.setData(tableAreaList);
  }

  @Override public void showNoTableAreas() {

  }

  @Override public void showTableList(List<PxTableInfo> tableInfoList) {
    mTableAdapter.setData(tableInfoList);
  }

  @Override public void showNoTableList() {

  }

  @Override public void onOccupiedTableClick(PxTableInfo tableInfo) {

  }

  @Override public void onEmptyTableClick(int pos) {
    //mTableAdapter.initData(mTableInfoList);
    Toast.makeText(this, pos + "", Toast.LENGTH_SHORT).show();
    startActivity(new Intent(this, MenuActivity.class));
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.ll_hint_more_function:
        showPopupWindow();
        break;
      case R.id.im_waiter_choice:
        showSetWindow();
        break;
    }
  }

  private void showPopupWindow() {
    View contentView = LayoutInflater.from(this).inflate(R.layout.popup_table_set, null);
    mPopupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT, true);
    mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    mPopupWindow.setTouchable(true);
    mPopupWindow.setOutsideTouchable(true);
    mPopupWindow.setFocusable(true);
    contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
    int popupWidth = contentView.getMeasuredWidth();
    int popupHeight = contentView.getMeasuredHeight();
    int[] location = new int[2];
    mLayout.getLocationOnScreen(location);
    mPopupWindow.showAtLocation(mLayout, Gravity.NO_GRAVITY,
        (location[0] + mLayout.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
    mPopupWindow.setContentView(contentView);
  }

  private void showSetWindow() {
    View setView = LayoutInflater.from(this).inflate(R.layout.popup_waiter_set, null);
    mPopupWindow = new PopupWindow(setView, LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT, true);
    mPopupWindow.setFocusable(true);
    mPopupWindow.setOutsideTouchable(true);
    mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    //控件宽度是showAsDropDown的x轴的两倍才能显示均衡
    mPopupWindow.setWidth(mSet.getWidth() - 20);
    mPopupWindow.showAsDropDown(mSet, 10, 0);
  }
}
