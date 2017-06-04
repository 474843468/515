package com.psi.psieasymanager.table;

import com.psi.psieasymanager.data.bean.PxTableArea;
import com.psi.psieasymanager.data.bean.PxTableInfo;
import java.util.List;

/**
 * Created by psi on 2017/5/10.
 */

public interface TableContract {

  interface View {

    void showTableAreas(List<PxTableArea> tableAreaList);

    void showNoTableAreas();

    void showTableList(List<PxTableInfo> tableInfoList);

    void showNoTableList();
  }

  interface Presenter {

    void loadTableAreas();

    void loadTableList();
  }
}
