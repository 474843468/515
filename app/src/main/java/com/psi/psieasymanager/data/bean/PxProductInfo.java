package com.psi.psieasymanager.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dorado on 2017/4/24.
 */

@Entity public class PxProductInfo {
  @Id(autoincrement = true) private Long id;

  private String objectId;

  private String name;
  private Double price;
  private String py;
  private Long pxProductCategoryId;
  @Generated(hash = 2047807044)
  public PxProductInfo(Long id, String objectId, String name, Double price,
          String py, Long pxProductCategoryId) {
      this.id = id;
      this.objectId = objectId;
      this.name = name;
      this.price = price;
      this.py = py;
      this.pxProductCategoryId = pxProductCategoryId;
  } public PxProductInfo( String objectId, String name, Double price,
          String py, Long pxProductCategoryId) {
      this.objectId = objectId;
      this.name = name;
      this.price = price;
      this.py = py;
      this.pxProductCategoryId = pxProductCategoryId;
  }
  @Generated(hash = 411422401)
  public PxProductInfo() {
  }
  public Long getId() {
      return this.id;
  }
  public void setId(Long id) {
      this.id = id;
  }
  public String getObjectId() {
      return this.objectId;
  }
  public void setObjectId(String objectId) {
      this.objectId = objectId;
  }
  public String getName() {
      return this.name;
  }
  public void setName(String name) {
      this.name = name;
  }
  public Double getPrice() {
      return this.price;
  }
  public void setPrice(Double price) {
      this.price = price;
  }
  public String getPy() {
      return this.py;
  }
  public void setPy(String py) {
      this.py = py;
  }
  public Long getPxProductCategoryId() {
      return this.pxProductCategoryId;
  }
  public void setPxProductCategoryId(Long pxProductCategoryId) {
      this.pxProductCategoryId = pxProductCategoryId;
  }

}
