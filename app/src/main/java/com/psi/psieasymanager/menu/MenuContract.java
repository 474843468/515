package com.psi.psieasymanager.menu;

import com.psi.psieasymanager.data.bean.PxProductCategory;
import com.psi.psieasymanager.data.bean.PxProductInfo;
import java.util.List;


public interface MenuContract {

  interface View {
    void   showCategoryList(List<PxProductCategory> productCategoryList);
    void   showProductList(List<PxProductInfo> productInfoList);

    void showNoCategoryList();

    void showPopUpWindow();
    void showNoProductList();
  }

  interface Presenter {

    void loadCategoryList();

    void loadProductList();
  }
}
