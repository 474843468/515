package com.psi.psieasymanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.psi.psieasymanager.R;
import com.psi.psieasymanager.data.bean.PxProductInfo;
import java.util.List;

public class MenuProductAdapter
    extends RecyclerView.Adapter<MenuProductAdapter.ViewHolder> {
  private Context mContext;
  private List<PxProductInfo> mProductInfoList;

  public MenuProductAdapter(Context context, List<PxProductInfo> productInfoList) {
    mContext = context;
    this.mProductInfoList = productInfoList;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, final int position) {
    final PxProductInfo productInfo = mProductInfoList.get(position);
    holder.mTvFullName.setText(productInfo.getName());
    int prodNum = 3;
    if (prodNum > 0) {
      holder.mTvProdNum.setText(String.valueOf(prodNum));
      holder.mTvProdNum.setVisibility(View.VISIBLE);
    } else {
      holder.mTvProdNum.setVisibility(View.GONE);
    }

    String price = "¥"+productInfo.getPrice();
    holder.mTvPrice.setText(price);
    if (mOnProductClickListener != null) {
      holder.mViewContainer.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          mOnProductClickListener.onProductClick(holder.getLayoutPosition());
        }
      });
      holder.mViewContainer.setOnLongClickListener(new View.OnLongClickListener() {
        @Override public boolean onLongClick(View v) {
          mOnProductClickListener.onProductLongClick(
              mProductInfoList.get(holder.getLayoutPosition()));
          return true;
        }
      });
    }
  }

  @Override public int getItemCount() {
    return mProductInfoList.size();
  }

  public interface OnProductClickListener {
    void onProductClick(int pos);

    void onProductLongClick(PxProductInfo productInfo);
  }

  public OnProductClickListener mOnProductClickListener;

  public void setOnProductClickListener(OnProductClickListener onProductClickListener) {
    mOnProductClickListener = onProductClickListener;
  }

  public void setData(List<PxProductInfo> list) {
    if (null == list) {
      return;
    } else {
      mProductInfoList = list;
      notifyDataSetChanged();
    }
  }

  public void clearData() {
    mProductInfoList.clear();
    this.notifyDataSetChanged();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    TextView mTvName;
    TextView mTvFullName;
    TextView mTvPrice;
    LinearLayout mViewContainer;
    TextView mTvProdNum;

    public ViewHolder(View itemView) {
      super(itemView);
      mTvName = (TextView) itemView.findViewById(R.id.tv_name);
      mTvFullName = (TextView) itemView.findViewById(R.id.tv_full_name);
      mTvPrice = (TextView) itemView.findViewById(R.id.tv_price);
      mTvProdNum = (TextView) itemView.findViewById(R.id.tv_prod_num);
      mViewContainer = (LinearLayout) itemView.findViewById(R.id.view_container);
    }
  }
}
