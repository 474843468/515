package com.psi.psieasymanager.db;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.psi.psieasymanager.data.bean.PxProductInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PX_PRODUCT_INFO".
*/
public class PxProductInfoDao extends AbstractDao<PxProductInfo, Long> {

    public static final String TABLENAME = "PX_PRODUCT_INFO";

    /**
     * Properties of entity PxProductInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ObjectId = new Property(1, String.class, "objectId", false, "OBJECT_ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Price = new Property(3, Double.class, "price", false, "PRICE");
        public final static Property Py = new Property(4, String.class, "py", false, "PY");
        public final static Property PxProductCategoryId = new Property(5, Long.class, "pxProductCategoryId", false, "PX_PRODUCT_CATEGORY_ID");
    }

    private Query<PxProductInfo> pxProductCategory_DbProductInfoListQuery;

    public PxProductInfoDao(DaoConfig config) {
        super(config);
    }
    
    public PxProductInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PX_PRODUCT_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"OBJECT_ID\" TEXT," + // 1: objectId
                "\"NAME\" TEXT," + // 2: name
                "\"PRICE\" REAL," + // 3: price
                "\"PY\" TEXT," + // 4: py
                "\"PX_PRODUCT_CATEGORY_ID\" INTEGER);"); // 5: pxProductCategoryId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PX_PRODUCT_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PxProductInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String objectId = entity.getObjectId();
        if (objectId != null) {
            stmt.bindString(2, objectId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        Double price = entity.getPrice();
        if (price != null) {
            stmt.bindDouble(4, price);
        }
 
        String py = entity.getPy();
        if (py != null) {
            stmt.bindString(5, py);
        }
 
        Long pxProductCategoryId = entity.getPxProductCategoryId();
        if (pxProductCategoryId != null) {
            stmt.bindLong(6, pxProductCategoryId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PxProductInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String objectId = entity.getObjectId();
        if (objectId != null) {
            stmt.bindString(2, objectId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        Double price = entity.getPrice();
        if (price != null) {
            stmt.bindDouble(4, price);
        }
 
        String py = entity.getPy();
        if (py != null) {
            stmt.bindString(5, py);
        }
 
        Long pxProductCategoryId = entity.getPxProductCategoryId();
        if (pxProductCategoryId != null) {
            stmt.bindLong(6, pxProductCategoryId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PxProductInfo readEntity(Cursor cursor, int offset) {
        PxProductInfo entity = new PxProductInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // objectId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3), // price
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // py
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5) // pxProductCategoryId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PxProductInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setObjectId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPrice(cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3));
        entity.setPy(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPxProductCategoryId(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PxProductInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PxProductInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PxProductInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "dbProductInfoList" to-many relationship of PxProductCategory. */
    public List<PxProductInfo> _queryPxProductCategory_DbProductInfoList(Long pxProductCategoryId) {
        synchronized (this) {
            if (pxProductCategory_DbProductInfoListQuery == null) {
                QueryBuilder<PxProductInfo> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.PxProductCategoryId.eq(null));
                pxProductCategory_DbProductInfoListQuery = queryBuilder.build();
            }
        }
        Query<PxProductInfo> query = pxProductCategory_DbProductInfoListQuery.forCurrentThread();
        query.setParameter(0, pxProductCategoryId);
        return query.list();
    }

}