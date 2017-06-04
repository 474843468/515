package com.psi.psieasymanager.menu;

import android.content.Context;
import com.psi.psieasymanager.data.bean.PxProductCategory;
import com.psi.psieasymanager.data.bean.PxProductInfo;
import com.psi.psieasymanager.data.bean.User;
import com.psi.psieasymanager.data.source.DataSourceImpl;
import com.psi.psieasymanager.data.source.DbDataSource;
import java.util.List;
import javax.inject.Inject;


public class MenuPresenter implements MenuContract.Presenter {

  private Context mContext;
  private MenuContract.View mView;
  private User mUser;
  private DbDataSource mSource;

  @Inject public MenuPresenter(Context context, MenuContract.View view) {
    mContext = context;
    mView = view;
  }

  @Override public void loadCategoryList() {
    mSource.getCategoryList("","",new DataSourceImpl.LoadCategoryListCallback(){

      @Override public void onCategoryListLoaded(List<PxProductCategory> categoryList) {
        mView.showCategoryList(categoryList);
      }

      @Override public void onDataNotAvailable() {

      }
    });
  }

  @Override public void loadProductList() {
    mSource.getProductList("","",new DataSourceImpl.LoadProductListCallback(){

      @Override public void onProductListLoaded(List<PxProductInfo> productInfoList) {
        mView.showProductList(productInfoList);
      }

      @Override public void onDataNotAvailable() {

      }
    });
  }
}
