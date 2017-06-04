package com.psi.psieasymanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.psi.psieasymanager.R;
import com.psi.psieasymanager.data.bean.PxTableArea;
import java.util.List;

/**
 * Created by psi on 2017/5/9.
 */

public class TableAreaAdapter extends BaseAdapter {

  private List<PxTableArea> mTableAreaList;
  private Context context;

  public TableAreaAdapter(Context context, List<PxTableArea> tableAreaList) {
    this.context = context;
    this.mTableAreaList = tableAreaList;
  }

  @Override public int getCount() {
    return mTableAreaList.size();
  }

  @Override public Object getItem(int position) {
    return mTableAreaList.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    Holder holer;
    if (convertView == null) {
      holer = new Holder();
      convertView = LayoutInflater.from(context).inflate(R.layout.item_table_type, null);
      holer.mTvName = (TextView) convertView.findViewById(R.id.tv_name);
      convertView.setTag(holer);
    } else {
      holer = (Holder) convertView.getTag();
    }
    PxTableArea tableArea = mTableAreaList.get(position);
    holer.mTvName.setText(tableArea.getName());
    return convertView;
  }

  class Holder {
    TextView mTvName;
  }

  /**
   * 设置数据
   */
  public void setData(List<PxTableArea> data) {
    if (null == data) {
      return;
    } else {
      mTableAreaList = data;
      this.notifyDataSetChanged();
    }
  }
}
