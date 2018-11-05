package com.example.quangtran.mvppattern.presenters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quangtran.mvppattern.OnRecyclerViewItemClickListener;
import com.example.quangtran.mvppattern.R;
import com.example.quangtran.mvppattern.models.Item;

import java.util.ArrayList;
import java.util.List;


public class GitHubUserAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
    private List<Item> mUsers = new ArrayList<>();

    public GitHubUserAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_github_user, parent, false);
        return new ItemNewFeedViewHolder(view, mOnRecyclerViewItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemNewFeedViewHolder itemNewFeedViewHolder = (ItemNewFeedViewHolder) holder;
        itemNewFeedViewHolder.fillData(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void setOnRecyclerViewItemClickListener(
            OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public void updateData(List<Item> users) {
        if (users == null) {
            return;
        }
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    static class ItemNewFeedViewHolder extends RecyclerView.ViewHolder {

        private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
        private Item mUser;
        private TextView userIdTextView, userNameTextView;

        public ItemNewFeedViewHolder(View itemView,
                OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
            super(itemView);
            mOnRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
            userIdTextView = itemView.findViewById(R.id.userIdTextView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnRecyclerViewItemClickListener.onItemClick(mUser);
                }
            });
        }

        public void fillData(Item user) {
            mUser = user;
            userIdTextView.setText(user.getId().toString());
            userNameTextView.setText(user.getLogin());
        }
    }
}
