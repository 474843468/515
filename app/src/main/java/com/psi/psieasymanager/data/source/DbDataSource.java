package com.psi.psieasymanager.data.source;

import android.support.annotation.NonNull;
import com.psi.psieasymanager.data.bean.PxProductCategory;
import com.psi.psieasymanager.data.bean.PxProductInfo;
import com.psi.psieasymanager.data.bean.PxTableArea;
import com.psi.psieasymanager.data.bean.PxTableInfo;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by dorado on 2017/4/24.
 */

@Singleton public class DbDataSource implements DataSourceImpl {
  /**
   * Constructor
   */
  @Inject public DbDataSource() {

  }

  @Override public void getCategoryList(@NonNull String brandId, @NonNull String companyId,
      @NonNull LoadCategoryListCallback callback) {
    ArrayList<PxProductCategory> myCate = new ArrayList<>();
    myCate.add(new PxProductCategory("1", "热菜", 1l, 1l));
    myCate.add(new PxProductCategory("2", "凉菜", 2l, 2l));
    myCate.add(new PxProductCategory("3", "全部", 3l, 3l));

    if (myCate.isEmpty()) {
      callback.onDataNotAvailable();
    } else {
      callback.onCategoryListLoaded(myCate);
    }
  }

  @Override public void getProductList(@NonNull String brandId, @NonNull String companyId,
      @NonNull LoadProductListCallback callback) {
    ArrayList<PxProductInfo> myProduc = new ArrayList<>();
    myProduc.add(new PxProductInfo("1", "糖醋里脊", 232d, "tc", 1l));
    myProduc.add(new PxProductInfo("2", "炒饭", 12d, "cf", 1l));
    myProduc.add(new PxProductInfo("3", "凉拌牛肉", 121d, "nr", 2l));
    myProduc.add(new PxProductInfo("4", "凉拌黄瓜", 12d, "hg", 2l));
    if (myProduc.isEmpty()) {
      callback.onDataNotAvailable();
    } else {
      callback.onProductListLoaded(myProduc);
    }
  }

  /**
   * 获取商品详情
   *
   * @param brandId 品牌
   * @param companyId 公司
   * @param productId 商品id
   * @param callback 回调
   */
  @Override public void getProductInfo(@NonNull String brandId, @NonNull String companyId,
      @NonNull String productId, @NonNull GetProductCallback callback) {

  }

  /**
   * 获取桌台区域列表
   *
   * @param brandId 品牌
   * @param companyId 公司
   * @param callback 回调
   */
  @Override public void getTableAreaList(@NonNull String brandId, @NonNull String companyId,
      @NonNull LoadTableAreaListCallback callback) {
    //获取本地数据并添加到tableAreaList中
    List<PxTableArea> tableAreaList = new ArrayList<>();
    //这楼里构造假数据
    PxTableArea pxTableArea1 = new PxTableArea();
    pxTableArea1.setId(1L);
    pxTableArea1.setName("大厅");
    tableAreaList.add(pxTableArea1);
    PxTableArea pxTableArea2 = new PxTableArea();
    pxTableArea2.setId(2L);
    pxTableArea2.setName("区域");
    tableAreaList.add(pxTableArea2);
    PxTableArea pxTableArea3 = new PxTableArea();
    pxTableArea3.setId(3L);
    pxTableArea3.setName("二楼");
    tableAreaList.add(pxTableArea3);

    if (tableAreaList.isEmpty()) {
      callback.onDataNotAvailable();
    } else {
      callback.onTableAreaListLoaded(tableAreaList);
    }
  }

  /**
   * 获取桌台列表
   *
   * @param brandId 品牌
   * @param companyId 公司
   * @param areaId 区域id
   * @param callback 回调
   */
  @Override public void getTableInfoList(@NonNull String brandId, @NonNull String companyId,
      @NonNull String areaId, @NonNull LoadTableInfoListCallback callback) {
    //构造假数据
    List<PxTableInfo> tableInfoList = new ArrayList<>();
    PxTableInfo pxTableInfo1 = new PxTableInfo();
    pxTableInfo1.setId(1L);
    pxTableInfo1.setName("一号桌");
    pxTableInfo1.setNum(8);
    pxTableInfo1.setStatus(PxTableInfo.STATUS_EMPTY);
    tableInfoList.add(pxTableInfo1);

    PxTableInfo pxTableInfo2 = new PxTableInfo();
    pxTableInfo2.setId(2L);
    pxTableInfo2.setName("二号桌");
    pxTableInfo2.setNum(10);
    pxTableInfo2.setStatus(PxTableInfo.STATUS_OCCUPIED);
    tableInfoList.add(pxTableInfo2);

    PxTableInfo pxTableInfo3 = new PxTableInfo();
    pxTableInfo3.setId(3L);
    pxTableInfo3.setName("三号桌");
    pxTableInfo3.setNum(10);
    pxTableInfo3.setStatus(PxTableInfo.STATUS_EMPTY);
    tableInfoList.add(pxTableInfo3);

    PxTableInfo pxTableInfo4 = new PxTableInfo();
    pxTableInfo4.setId(4L);
    pxTableInfo4.setName("四号桌");
    pxTableInfo4.setNum(10);
    pxTableInfo4.setStatus(PxTableInfo.STATUS_EMPTY);
    tableInfoList.add(pxTableInfo4);

    PxTableInfo pxTableInfo5 = new PxTableInfo();
    pxTableInfo5.setId(5L);
    pxTableInfo5.setName("五号桌");
    pxTableInfo5.setNum(10);
    pxTableInfo5.setStatus(PxTableInfo.STATUS_OCCUPIED);
    tableInfoList.add(pxTableInfo5);

    PxTableInfo pxTableInfo6 = new PxTableInfo();
    pxTableInfo6.setId(6L);
    pxTableInfo6.setName("六号桌");
    pxTableInfo6.setNum(10);
    pxTableInfo6.setStatus(PxTableInfo.STATUS_EMPTY);
    tableInfoList.add(pxTableInfo6);

    PxTableInfo pxTableInfo7 = new PxTableInfo();
    pxTableInfo7.setId(7L);
    pxTableInfo7.setName("七号桌");
    pxTableInfo7.setNum(8);
    pxTableInfo7.setStatus(PxTableInfo.STATUS_EMPTY);
    tableInfoList.add(pxTableInfo7);
    if (tableInfoList.isEmpty()) {
      callback.onDataNotAvailable();
    } else {
      callback.onTableInfoListLoaded(tableInfoList);
    }
  }
}
