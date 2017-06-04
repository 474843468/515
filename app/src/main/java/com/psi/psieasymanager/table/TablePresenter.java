package com.psi.psieasymanager.table;

import android.content.Context;
import com.psi.psieasymanager.data.bean.PxTableArea;
import com.psi.psieasymanager.data.bean.PxTableInfo;
import com.psi.psieasymanager.data.bean.User;
import com.psi.psieasymanager.data.source.DataSourceImpl;
import com.psi.psieasymanager.data.source.DbDataSource;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by psi on 2017/5/10.
 */

public class TablePresenter implements TableContract.Presenter {

  private Context mContext;
  private TableContract.View mView;
  private User mUser;
  private DbDataSource mSource;

  @Inject public TablePresenter(Context context, TableContract.View view) {
    mContext = context;
    mView = view;
  }

  @Override public void loadTableAreas() {

    mSource.getTableAreaList("", "", new DataSourceImpl.LoadTableAreaListCallback() {
      @Override public void onTableAreaListLoaded(List<PxTableArea> tableAreaList) {

        mView.showTableAreas(tableAreaList);
      }

      @Override public void onDataNotAvailable() {

      }
    });
  }

  @Override public void loadTableList() {
    mSource.getTableInfoList("", "", "", new DataSourceImpl.LoadTableInfoListCallback() {
      @Override public void onTableInfoListLoaded(List<PxTableInfo> tableInfoList) {
        mView.showTableList(tableInfoList);
      }

      @Override public void onDataNotAvailable() {

      }
    });
  }
}
