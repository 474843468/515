package com.psi.psieasymanager.data.source;

import android.support.annotation.NonNull;
import com.psi.psieasymanager.data.bean.PxProductCategory;
import com.psi.psieasymanager.data.bean.PxProductInfo;
import com.psi.psieasymanager.data.bean.PxTableArea;
import com.psi.psieasymanager.data.bean.PxTableInfo;
import java.util.List;

/**
 * Created by dorado on 2017/4/24.
 */

public interface DataSourceImpl {

  /**
   * 查询分类列表
   */
  void getCategoryList(@NonNull String brandId,@NonNull String companyId,@NonNull LoadCategoryListCallback callback);

  interface LoadCategoryListCallback {

    void onCategoryListLoaded(List<PxProductCategory> categoryList);

    void onDataNotAvailable();
  }

  /**
   * 查询商品列表
   */

  void getProductList(@NonNull String brandId,@NonNull String companyId,@NonNull LoadProductListCallback callback);

  interface LoadProductListCallback {

    void onProductListLoaded(List<PxProductInfo> productInfoList);

    void onDataNotAvailable();
  }

  /**
   * 获取商品信息
   */
  void getProductInfo(@NonNull String brandId,@NonNull String companyId,@NonNull String productId, @NonNull GetProductCallback callback);

  interface GetProductCallback {

    void onProductLoaded(PxProductInfo productInfo);

    void onDataNotAvailable();
  }

  /**
   * 查询桌台区域列表
   */
  void getTableAreaList(@NonNull String brandId, @NonNull String companyId,
      @NonNull LoadTableAreaListCallback callback);

  interface LoadTableAreaListCallback {

    void onTableAreaListLoaded(List<PxTableArea> tableAreaList);

    void onDataNotAvailable();
  }

  /**
   * 查询桌台信息列表
   */
  void getTableInfoList(@NonNull String brandId, @NonNull String companyId, @NonNull String areaId,
      @NonNull LoadTableInfoListCallback callback);

  interface LoadTableInfoListCallback {

    void onTableInfoListLoaded(List<PxTableInfo> tableInfoList);

    void onDataNotAvailable();
  }



}
