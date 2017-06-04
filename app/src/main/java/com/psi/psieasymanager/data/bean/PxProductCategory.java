package com.psi.psieasymanager.data.bean;

import java.util.List;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.psi.psieasymanager.db.DaoSession;
import com.psi.psieasymanager.db.PxProductInfoDao;
import com.psi.psieasymanager.db.PxProductCategoryDao;

/**
 * Created by dorado on 2017/4/24.
 */

@Entity public class PxProductCategory {
  @Id(autoincrement = true) private Long id;

  private String objectId;

  private String name;
  private Long pxParentCategoryId;
  private Long pxProductCategoryId;
  @ToOne(joinProperty = "pxParentCategoryId")
  private PxProductCategory dbParentCategory;

  @ToMany(referencedJoinProperty = "pxParentCategoryId")
  private List<PxProductCategory> dbChildCateList;


  @ToOne(joinProperty = "pxProductCategoryId")
  private PxProductCategory dbCategory;

  @ToMany(referencedJoinProperty = "pxProductCategoryId")
  private List<PxProductInfo> dbProductInfoList;

  /** Used to resolve relations */
  @Generated(hash = 2040040024)
  private transient DaoSession daoSession;

  /** Used for active entity operations. */
  @Generated(hash = 2096276943)
  private transient PxProductCategoryDao myDao;

  @Generated(hash = 1736931705)
  public PxProductCategory(Long id, String objectId, String name,
          Long pxParentCategoryId, Long pxProductCategoryId) {
      this.id = id;
      this.objectId = objectId;
      this.name = name;
      this.pxParentCategoryId = pxParentCategoryId;
      this.pxProductCategoryId = pxProductCategoryId;
  }  public PxProductCategory( String objectId, String name,
          Long pxParentCategoryId, Long pxProductCategoryId) {

      this.objectId = objectId;
      this.name = name;
      this.pxParentCategoryId = pxParentCategoryId;
      this.pxProductCategoryId = pxProductCategoryId;
  }

  @Generated(hash = 953502348)
  public PxProductCategory() {
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

  public Long getPxParentCategoryId() {
      return this.pxParentCategoryId;
  }

  public void setPxParentCategoryId(Long pxParentCategoryId) {
      this.pxParentCategoryId = pxParentCategoryId;
  }

  public Long getPxProductCategoryId() {
      return this.pxProductCategoryId;
  }

  public void setPxProductCategoryId(Long pxProductCategoryId) {
      this.pxProductCategoryId = pxProductCategoryId;
  }

  @Generated(hash = 2046256986)
  private transient Long dbParentCategory__resolvedKey;

  /** To-one relationship, resolved on first access. */
  @Generated(hash = 1384483997)
  public PxProductCategory getDbParentCategory() {
      Long __key = this.pxParentCategoryId;
      if (dbParentCategory__resolvedKey == null
              || !dbParentCategory__resolvedKey.equals(__key)) {
          final DaoSession daoSession = this.daoSession;
          if (daoSession == null) {
              throw new DaoException("Entity is detached from DAO context");
          }
          PxProductCategoryDao targetDao = daoSession.getPxProductCategoryDao();
          PxProductCategory dbParentCategoryNew = targetDao.load(__key);
          synchronized (this) {
              dbParentCategory = dbParentCategoryNew;
              dbParentCategory__resolvedKey = __key;
          }
      }
      return dbParentCategory;
  }

  /** called by internal mechanisms, do not call yourself. */
  @Generated(hash = 1255036204)
  public void setDbParentCategory(PxProductCategory dbParentCategory) {
      synchronized (this) {
          this.dbParentCategory = dbParentCategory;
          pxParentCategoryId = dbParentCategory == null ? null
                  : dbParentCategory.getId();
          dbParentCategory__resolvedKey = pxParentCategoryId;
      }
  }

  @Generated(hash = 1445930856)
  private transient Long dbCategory__resolvedKey;

  /** To-one relationship, resolved on first access. */
  @Generated(hash = 1320772632)
  public PxProductCategory getDbCategory() {
      Long __key = this.pxProductCategoryId;
      if (dbCategory__resolvedKey == null
              || !dbCategory__resolvedKey.equals(__key)) {
          final DaoSession daoSession = this.daoSession;
          if (daoSession == null) {
              throw new DaoException("Entity is detached from DAO context");
          }
          PxProductCategoryDao targetDao = daoSession.getPxProductCategoryDao();
          PxProductCategory dbCategoryNew = targetDao.load(__key);
          synchronized (this) {
              dbCategory = dbCategoryNew;
              dbCategory__resolvedKey = __key;
          }
      }
      return dbCategory;
  }

  /** called by internal mechanisms, do not call yourself. */
  @Generated(hash = 1379832989)
  public void setDbCategory(PxProductCategory dbCategory) {
      synchronized (this) {
          this.dbCategory = dbCategory;
          pxProductCategoryId = dbCategory == null ? null : dbCategory.getId();
          dbCategory__resolvedKey = pxProductCategoryId;
      }
  }

  /**
   * To-many relationship, resolved on first access (and after reset).
   * Changes to to-many relations are not persisted, make changes to the target entity.
   */
  @Generated(hash = 24448861)
  public List<PxProductCategory> getDbChildCateList() {
      if (dbChildCateList == null) {
          final DaoSession daoSession = this.daoSession;
          if (daoSession == null) {
              throw new DaoException("Entity is detached from DAO context");
          }
          PxProductCategoryDao targetDao = daoSession.getPxProductCategoryDao();
          List<PxProductCategory> dbChildCateListNew = targetDao
                  ._queryPxProductCategory_DbChildCateList(id);
          synchronized (this) {
              if (dbChildCateList == null) {
                  dbChildCateList = dbChildCateListNew;
              }
          }
      }
      return dbChildCateList;
  }

  /** Resets a to-many relationship, making the next get call to query for a fresh result. */
  @Generated(hash = 1709148884)
  public synchronized void resetDbChildCateList() {
      dbChildCateList = null;
  }

  /**
   * To-many relationship, resolved on first access (and after reset).
   * Changes to to-many relations are not persisted, make changes to the target entity.
   */
  @Generated(hash = 1928191078)
  public List<PxProductInfo> getDbProductInfoList() {
      if (dbProductInfoList == null) {
          final DaoSession daoSession = this.daoSession;
          if (daoSession == null) {
              throw new DaoException("Entity is detached from DAO context");
          }
          PxProductInfoDao targetDao = daoSession.getPxProductInfoDao();
          List<PxProductInfo> dbProductInfoListNew = targetDao
                  ._queryPxProductCategory_DbProductInfoList(id);
          synchronized (this) {
              if (dbProductInfoList == null) {
                  dbProductInfoList = dbProductInfoListNew;
              }
          }
      }
      return dbProductInfoList;
  }

  /** Resets a to-many relationship, making the next get call to query for a fresh result. */
  @Generated(hash = 1785231309)
  public synchronized void resetDbProductInfoList() {
      dbProductInfoList = null;
  }

  /**
   * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
   * Entity must attached to an entity context.
   */
  @Generated(hash = 128553479)
  public void delete() {
      if (myDao == null) {
          throw new DaoException("Entity is detached from DAO context");
      }
      myDao.delete(this);
  }

  /**
   * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
   * Entity must attached to an entity context.
   */
  @Generated(hash = 1942392019)
  public void refresh() {
      if (myDao == null) {
          throw new DaoException("Entity is detached from DAO context");
      }
      myDao.refresh(this);
  }

  /**
   * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
   * Entity must attached to an entity context.
   */
  @Generated(hash = 713229351)
  public void update() {
      if (myDao == null) {
          throw new DaoException("Entity is detached from DAO context");
      }
      myDao.update(this);
  }

  /** called by internal mechanisms, do not call yourself. */
  @Generated(hash = 1161542235)
  public void __setDaoSession(DaoSession daoSession) {
      this.daoSession = daoSession;
      myDao = daoSession != null ? daoSession.getPxProductCategoryDao() : null;
  }

}
