package com.example.vova.usersbook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vova.usersbook.R;
import com.example.vova.usersbook.models.UserInfo;

import java.util.ArrayList;

public class UserInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<UserInfo> mUserInfos = new ArrayList<>();
    private OnUserClickListener mOnUserClickListener = null;
    private OnLongUserClickListener mOnLongUserClickListener = null;

    public UserInfoAdapter(ArrayList<UserInfo> userInfos) {
        mUserInfos = userInfos;
    }

    public void setOnUserClickListener(OnUserClickListener onUserClickListener) {
        mOnUserClickListener = onUserClickListener;
    }

    public void setOnLongUserClickListener(OnLongUserClickListener onLongUserClickListener) {
        mOnLongUserClickListener = onLongUserClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        View viewUserInfo = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_user_info, parent, false);
        viewHolder = new UserInfoViewHolder(viewUserInfo);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        UserInfoViewHolder userInfoViewHolder = (UserInfoViewHolder) holder;
        final UserInfo userInfo = mUserInfos.get(position);
        userInfoViewHolder.mTextViewUserName.setText(userInfo.getUserName());
        userInfoViewHolder.mTextViewUserBDay.setText(userInfo.getUserBDay());
        userInfoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnUserClickListener != null) {
                    mOnUserClickListener.onUserClickItem(userInfo);
                }
            }
        });
        userInfoViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnLongUserClickListener != null) {
                    mOnLongUserClickListener.onLongUserClickItem(userInfo);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUserInfos.size();
    }

    public class UserInfoViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewUserName;
        private TextView mTextViewUserBDay;

        public UserInfoViewHolder(View itemView) {
            super(itemView);

            mTextViewUserName = (TextView) itemView.findViewById(R.id.textViewUserNameItemUserInfo);
            mTextViewUserBDay = (TextView) itemView.findViewById(R.id.textViewUserBDayItemUserInfo);
        }
    }

    public interface OnUserClickListener{
        void onUserClickItem(UserInfo userInfo);
    }

    public interface OnLongUserClickListener{
        void onLongUserClickItem(UserInfo userInfo);
    }
}
