package com.psi.psieasymanager.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.psi.psieasymanager.R;
import com.psi.psieasymanager.data.bean.PxTableInfo;
import java.util.List;

/**
 * Created by psi on 2017/5/9.
 */

public class TableAdapter extends RecyclerView.Adapter {

  private List<PxTableInfo> mTableList;
  private Context mContext;
  private int mCurrentSelected = -1;//当前选中

  public TableAdapter(Context context, List<PxTableInfo> tableList) {
    mContext = context;
    mTableList = tableList;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = null;
    switch (viewType + "") {
      case PxTableInfo.STATUS_EMPTY://空闲
        view = LayoutInflater.from(mContext).inflate(R.layout.item_table_empty, parent, false);
        return new ViewHolderEmpty(view);
      case PxTableInfo.STATUS_OCCUPIED://占用
        view = LayoutInflater.from(mContext).inflate(R.layout.item_table_occupied, parent, false);
        return new ViewHolderOccupied(view);
    }
    return null;
  }

  @Override public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
    PxTableInfo tableInfo = mTableList.get(position);
    switch (holder.getItemViewType() + "") {
      case PxTableInfo.STATUS_EMPTY:
        final ViewHolderEmpty holderEmpty = (ViewHolderEmpty) holder;
        //建议人数
        holderEmpty.mTvSuggestPeopleNum.setText("0/" + tableInfo.getNum());
        //桌台名称
        holderEmpty.mTvTableNumber.setText(tableInfo.getName());
        //更换背景
        if (position == mCurrentSelected) {
          holderEmpty.mIvStartTable.setVisibility(View.GONE);
          holderEmpty.mLlStartTable.setVisibility(View.VISIBLE);
        } else {
          holderEmpty.mIvStartTable.setVisibility(View.VISIBLE);
          holderEmpty.mLlStartTable.setVisibility(View.GONE);
        }
        //点击
        if (mOnTableClickListener != null) {
          holderEmpty.mViewContainer.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
              if (position == mCurrentSelected) {
                setSelected(-1);
              } else {
                setSelected(position);
              }
            }
          });
          holderEmpty.mLlStartTable.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
              mOnTableClickListener.onEmptyTableClick(position);
            }
          });
        }
        break;
      case PxTableInfo.STATUS_OCCUPIED:
        ViewHolderOccupied holderOccupied = (ViewHolderOccupied) holder;
        //实际人数
        holderOccupied.mTvPeopleNumber.setText("8/" + tableInfo.getNum());
        //持续时间
        holderOccupied.mTvDuration.setText("0h0m");
        //桌台名称
        holderOccupied.mTvTableNumber.setText(tableInfo.getName());
        //更换背景
        if (position == mCurrentSelected) {
          holderOccupied.mViewContainer.setCardBackgroundColor(
              mContext.getResources().getColor(R.color.item_sel_table_container_status_normal));
          holderOccupied.mViewContent.setBackgroundResource(
              R.color.item_sel_table_content_status_normal);
        } else {
          holderOccupied.mViewContainer.setCardBackgroundColor(
              mContext.getResources().getColor(R.color.table_occupy_bg));
          holderOccupied.mViewContent.setBackgroundResource(R.color.table_occupy_bg);
        }

        //点击
        if (mOnTableClickListener != null) {
          holderOccupied.mViewContainer.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
              mOnTableClickListener.onOccupiedTableClick(
                  mTableList.get(holder.getLayoutPosition()));
            }
          });
        }
        break;
    }
  }

  @Override public int getItemCount() {
    return mTableList.size();
  }

  @Override public int getItemViewType(int position) {
    return Integer.parseInt(mTableList.get(position).getStatus());
  }

  /**
   * 已占用Adapter
   */
  class ViewHolderOccupied extends RecyclerView.ViewHolder {

    TextView mTvPeopleNumber;
    TextView mTvDuration;
    TextView mTvTableNumber;
    CardView mViewContainer;
    RelativeLayout mViewContent;

    public ViewHolderOccupied(View itemView) {
      super(itemView);
      mTvPeopleNumber = (TextView) itemView.findViewById(R.id.tv_people_num);
      mTvDuration = (TextView) itemView.findViewById(R.id.tv_duration);
      mTvTableNumber = (TextView) itemView.findViewById(R.id.tv_table_name);
      mViewContainer = (CardView) itemView.findViewById(R.id.view_container);
      mViewContent = (RelativeLayout) itemView.findViewById(R.id.view_content);
    }
  }

  /**
   * 空闲Adapter
   */
  class ViewHolderEmpty extends RecyclerView.ViewHolder {

    TextView mTvSuggestPeopleNum;
    TextView mTvTableNumber;
    CardView mViewContainer;
    LinearLayout mLlStartTable;
    ImageView mIvStartTable;
    RelativeLayout mViewContent;

    public ViewHolderEmpty(View itemView) {
      super(itemView);
      mTvSuggestPeopleNum = (TextView) itemView.findViewById(R.id.tv_suggest_people_num);
      mTvTableNumber = (TextView) itemView.findViewById(R.id.tv_table_name);
      mViewContainer = (CardView) itemView.findViewById(R.id.view_container);
      mViewContent = (RelativeLayout) itemView.findViewById(R.id.view_content);
      mLlStartTable = (LinearLayout) itemView.findViewById(R.id.ll_start_table);
      mIvStartTable = (ImageView) itemView.findViewById(R.id.iv_start_table);
    }
  }

  /**
   * 设置数据
   */
  public void setData(List<PxTableInfo> data) {
    if (null == data) {
      return;
    } else {
      mTableList = data;
      mCurrentSelected = -1;
      this.notifyDataSetChanged();
    }
  }

  /**
   * 设置选中
   */
  public void setSelected(int position) {
    mCurrentSelected = position;
    notifyDataSetChanged();
  }

  /**
   * Click
   */
  public static interface OnTableClickListener {
    void onOccupiedTableClick(PxTableInfo tableInfo);

    void onEmptyTableClick(int pos);
  }

  public OnTableClickListener mOnTableClickListener;

  public void setOnTableClickListener(OnTableClickListener onTableClickListener) {
    mOnTableClickListener = onTableClickListener;
  }
}
