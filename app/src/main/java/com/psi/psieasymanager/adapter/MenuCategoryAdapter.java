package com.psi.psieasymanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.psi.psieasymanager.R;
import com.psi.psieasymanager.data.bean.PxProductCategory;
import java.util.List;


public class MenuCategoryAdapter extends RecyclerView.Adapter<MenuCategoryAdapter.ViewHolder> {

  private Context mContext;
  private List<PxProductCategory> mCategoryList;
  //当前选中
  private int mCurrentSelected = -1;

  public MenuCategoryAdapter(Context context, List<PxProductCategory> categoryList) {
    mContext = context;
    mCategoryList = categoryList;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.item_category, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, final int position) {
    PxProductCategory category = mCategoryList.get(position);
    holder.mTvCateName.setText(category.getName());
    holder.mTvCateName.setTextColor(mContext.getResources().getColor(R.color.item_cate_text_color_unselected));
    holder.mTvCateName.setBackground(mContext.getResources().getDrawable(R.drawable.bg_item_cate_unsel));
    if (mCurrentSelected == position) {
      holder.mTvCateName.setTextColor(mContext.getResources().getColor(R.color.item_cate_text_color_selected));
      holder.mTvCateName.setBackground(mContext.getResources().getDrawable(R.drawable.bg_item_cate_sel));
    }

    //Click
    if (mOnCateClickListener != null) {
      holder.mTvCateName.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          mOnCateClickListener.onCateClick(position);
        }
      });
    }
  }

  @Override public int getItemCount() {
    return mCategoryList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    TextView mTvCateName;

    public ViewHolder(View itemView) {
      super(itemView);
      mTvCateName = (TextView) itemView.findViewById(R.id.tv_cate_name);

    }
  }

  /**
   * Click
   */
  public static interface OnCateClickListener {
    void onCateClick(int pos);
  }

  public OnCateClickListener mOnCateClickListener;

  public void setOnCateClickListener(OnCateClickListener onCateClickListener) {
    mOnCateClickListener = onCateClickListener;
  }

  /**
   * 设置选中
   */
  public void setSelected(int original, int now) {
    mCurrentSelected = now;
    notifyItemChanged(original);
    notifyItemChanged(now);
  }

  /**
   * 返回当前选中 pos
   */
  public int getSelected() {
    return mCurrentSelected;
  }

  /**
   * 设置数据
   */
  public void setData(List<PxProductCategory> data) {
    if (null == data) {
      return;
    } else {
      mCategoryList = data;
      mCurrentSelected = -1;
      this.notifyDataSetChanged();
    }
  }
}
