package com.psi.psieasymanager.menu;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.psi.psieasymanager.App;
import com.psi.psieasymanager.R;
import com.psi.psieasymanager.adapter.MenuCategoryAdapter;
import com.psi.psieasymanager.adapter.MenuProductAdapter;
import com.psi.psieasymanager.base.BaseActivity;
import com.psi.psieasymanager.data.bean.PxProductCategory;
import com.psi.psieasymanager.data.bean.PxProductInfo;
import com.psi.psieasymanager.table.TableActivity;
import com.psi.psieasymanager.widget.RecyclerViewSpaceItemDecoration;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;


public class MenuActivity extends BaseActivity
    implements MenuContract.View,MenuCategoryAdapter.OnCateClickListener{

  //Presenter
  @Inject MenuPresenter mMenuPresenter;
  private Button mOpenMenu;
  private TextView mProSearch;

  //分类
  private RecyclerView mRcvCate;
  private List<PxProductCategory> mCategoryList=new ArrayList<>();
  private MenuCategoryAdapter mCategoryAdapter;

  //菜单
  private RecyclerView mRcvMenu;
  private List<PxProductInfo> mProductInfos=new ArrayList<>();
  private MenuProductAdapter mProductAdapter;
  private RelativeLayout mRlContent;
  private ImageView mIvBack;

  @Override protected void initStatus() {
    mRcvCate = (RecyclerView) findViewById(R.id.rcv_cate);
    mRcvMenu= (RecyclerView) findViewById(R.id.rcv_menu);
    mProSearch = (TextView) findViewById(R.id.pro_search);
    mIvBack =  (ImageView)findViewById(R.id.iv_back);
    mRlContent = (RelativeLayout) findViewById(R.id.rl_content);

    //分类
    LinearLayoutManager managerCategory =
        new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
    mRcvCate.setLayoutManager(managerCategory);
    mRcvCate.setHasFixedSize(true);
    mCategoryAdapter = new MenuCategoryAdapter(this, mCategoryList);
    mRcvCate.setAdapter(mCategoryAdapter);
    mCategoryAdapter.setOnCateClickListener(this);
    //商品
    GridLayoutManager
        managerDish = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
    mProductAdapter = new MenuProductAdapter(this, mProductInfos);
    mProductAdapter.setOnProductClickListener(new OnProductClickListener());
    mRcvMenu.setHasFixedSize(true);
    mRcvMenu.setLayoutManager(managerDish);
    mRcvMenu.setAdapter(mProductAdapter);
    int prodSpaceWidth = getResources().getDimensionPixelSize(R.dimen.menu_rcv_item_horizontal_space_width);
    int prodSpaceHeight = getResources().getDimensionPixelSize(R.dimen.menu_rcv_item_vertical_space_height);
    mRcvMenu.addItemDecoration(new RecyclerViewSpaceItemDecoration(prodSpaceWidth, prodSpaceHeight));
    mIvBack.setVisibility(View.VISIBLE);
    mRlContent.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        startActivity(new Intent(MenuActivity.this, TableActivity.class));
      }
    });


  }

  @Override protected void initInject() {
    //注入Presenter
    DaggerMenuComponent.builder()
        .appComponent(App.getInstance().getAppComponent())
        .menuModule(new MenuModule(this))
        .build()
        .inject(this);
    mMenuPresenter.loadCategoryList();
    mMenuPresenter.loadProductList();
  }


  @Override protected void setContentView() {
    setContentView(R.layout.activity_menu);
  }
  @Override public void showCategoryList(List<PxProductCategory> productCategoryList) {
    mCategoryAdapter.setData(productCategoryList);

  }

  @Override public void showProductList(List<PxProductInfo> productInfoList) {
    mProductAdapter.setData(productInfoList);
  }

  @Override public void showNoCategoryList() {

  }

  @Override public void showPopUpWindow() {
    /*mIbSetting.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        if ( mPopWin== null) {
          OnClickLintener paramOnClickListener = new OnClickLintener();
          mPopWin =  new PopWin(MenuActivity.this, paramOnClickListener, DensityUtils
              .dp2px(mContext, 295), DensityUtils.dp2px(mContext, 240));
          View contentView = mPopWin.getContentView();
          mOrder_pop_ok =  (Button)contentView.findViewById(R.id.order_ok);
          mBt_choice_waiter = (Button)contentView.findViewById(R.id.ib_choice_waiter);
          contentView.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
              if (!hasFocus) {
                mPopWin.dismiss();
              }
            }
          });
        }
        //设置默认获取焦点
        mPopWin.setFocusable(true);
        mPopWin.showAsDropDown(mIbSetting, -510, 30);
        mPopWin.update();
        //  mBt_choice_waiter
      }
    });*/
  }

  @Override public void showNoProductList() {

  }

  //分类item点击
  @Override public void onCateClick(int pos) {

  }

  //菜单商品点击
  class OnProductClickListener implements MenuProductAdapter.OnProductClickListener{

    @Override public void onProductClick(int pos) {

    }

    @Override public void onProductLongClick(PxProductInfo productInfo) {
    }
  }

}
