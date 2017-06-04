package com.psi.psieasymanager.data.bean;

import com.psi.psieasymanager.db.DaoSession;
import com.psi.psieasymanager.db.PxTableAreaDao;
import com.psi.psieasymanager.db.PxTableInfoDao;
import java.util.List;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

/**
 * Created by psi on 2017/5/9.
 */
@Entity public class PxTableArea {
  @Id(autoincrement = true) private Long id;
  private String name;
  private Integer create_by;
  private String create_date;
  private Integer update_by;
  private String update_date;
  private String del_flag;
  private String remarks;
  private Integer version;
  private String store_id;
  private String brand_id;
  @ToMany(referencedJoinProperty = "area_id") private List<PxTableInfo> pxTableInfoList;
  /** Used to resolve relations */
  @Generated(hash = 2040040024) private transient DaoSession daoSession;
  /** Used for active entity operations. */
  @Generated(hash = 1505457678) private transient PxTableAreaDao myDao;

  @Generated(hash = 489413504)
  public PxTableArea(Long id, String name, Integer create_by, String create_date, Integer update_by,
      String update_date, String del_flag, String remarks, Integer version, String store_id,
      String brand_id) {
    this.id = id;
    this.name = name;
    this.create_by = create_by;
    this.create_date = create_date;
    this.update_by = update_by;
    this.update_date = update_date;
    this.del_flag = del_flag;
    this.remarks = remarks;
    this.version = version;
    this.store_id = store_id;
    this.brand_id = brand_id;
  }

  @Generated(hash = 1626214591) public PxTableArea() {
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
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

  /**
   * To-many relationship, resolved on first access (and after reset).
   * Changes to to-many relations are not persisted, make changes to the target entity.
   */
  @Generated(hash = 1660018369) public List<PxTableInfo> getPxTableInfoList() {
    if (pxTableInfoList == null) {
      final DaoSession daoSession = this.daoSession;
      if (daoSession == null) {
        throw new DaoException("Entity is detached from DAO context");
      }
      PxTableInfoDao targetDao = daoSession.getPxTableInfoDao();
      List<PxTableInfo> pxTableInfoListNew = targetDao._queryPxTableArea_PxTableInfoList(id);
      synchronized (this) {
        if (pxTableInfoList == null) {
          pxTableInfoList = pxTableInfoListNew;
        }
      }
    }
    return pxTableInfoList;
  }

  /** Resets a to-many relationship, making the next get call to query for a fresh result. */
  @Generated(hash = 1597793167) public synchronized void resetPxTableInfoList() {
    pxTableInfoList = null;
  }

  /**
   * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
   * Entity must attached to an entity context.
   */
  @Generated(hash = 128553479) public void delete() {
    if (myDao == null) {
      throw new DaoException("Entity is detached from DAO context");
    }
    myDao.delete(this);
  }

  /**
   * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
   * Entity must attached to an entity context.
   */
  @Generated(hash = 1942392019) public void refresh() {
    if (myDao == null) {
      throw new DaoException("Entity is detached from DAO context");
    }
    myDao.refresh(this);
  }

  /**
   * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
   * Entity must attached to an entity context.
   */
  @Generated(hash = 713229351) public void update() {
    if (myDao == null) {
      throw new DaoException("Entity is detached from DAO context");
    }
    myDao.update(this);
  }

  /** called by internal mechanisms, do not call yourself. */
  @Generated(hash = 977595900) public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getPxTableAreaDao() : null;
  }
}
