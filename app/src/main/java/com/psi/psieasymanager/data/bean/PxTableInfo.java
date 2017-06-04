package com.psi.psieasymanager.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by psi on 2017/5/9.
 */
@Entity public class PxTableInfo {

  public static final String STATUS_EMPTY = "0";
  public static final String STATUS_OCCUPIED = "1";

  @Id(autoincrement = true) private Long id;
  private String code;
  private String name;
  private Integer num;
  private String status;
  private String order_no;
  private Integer create_by;
  private String create_date;
  private Integer update_by;
  private String update_date;
  private String del_flag;
  private String remarks;
  private Integer version;
  private String store_id;
  private String brand_id;
  private Long area_id;//外键

  @Generated(hash = 1716122287)
  public PxTableInfo(Long id, String code, String name, Integer num, String status, String order_no,
      Integer create_by, String create_date, Integer update_by, String update_date, String del_flag,
      String remarks, Integer version, String store_id, String brand_id, Long area_id) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.num = num;
    this.status = status;
    this.order_no = order_no;
    this.create_by = create_by;
    this.create_date = create_date;
    this.update_by = update_by;
    this.update_date = update_date;
    this.del_flag = del_flag;
    this.remarks = remarks;
    this.version = version;
    this.store_id = store_id;
    this.brand_id = brand_id;
    this.area_id = area_id;
  }

  @Generated(hash = 1445755562) public PxTableInfo() {
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getNum() {
    return this.num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getOrder_no() {
    return this.order_no;
  }

  public void setOrder_no(String order_no) {
    this.order_no = order_no;
  }

  public Integer getCreate_by() {
    return this.create_by;
  }

  public void setCreate_by(Integer create_by) {
    this.create_by = create_by;
  }

  public String getCreate_date() {
    return this.create_date;
  }

  public void setCreate_date(String create_date) {
    this.create_date = create_date;
  }

  public Integer getUpdate_by() {
    return this.update_by;
  }

  public void setUpdate_by(Integer update_by) {
    this.update_by = update_by;
  }

  public String getUpdate_date() {
    return this.update_date;
  }

  public void setUpdate_date(String update_date) {
    this.update_date = update_date;
  }

  public String getDel_flag() {
    return this.del_flag;
  }

  public void setDel_flag(String del_flag) {
    this.del_flag = del_flag;
  }

  public String getRemarks() {
    return this.remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Integer getVersion() {
    return this.version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getStore_id() {
    return this.store_id;
  }

  public void setStore_id(String store_id) {
    this.store_id = store_id;
  }

  public String getBrand_id() {
    return this.brand_id;
  }

  public void setBrand_id(String brand_id) {
    this.brand_id = brand_id;
  }

  public Long getArea_id() {
    return this.area_id;
  }

  public void setArea_id(Long area_id) {
    this.area_id = area_id;
  }
}
