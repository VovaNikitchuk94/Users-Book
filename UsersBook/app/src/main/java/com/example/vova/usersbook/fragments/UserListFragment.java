package com.example.vova.usersbook.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vova.usersbook.R;
import com.example.vova.usersbook.adapters.UserInfoAdapter;
import com.example.vova.usersbook.models.UserInfo;
import com.example.vova.usersbook.models.engines.UserInfoEngine;

import java.util.ArrayList;

public class UserListFragment extends Fragment implements UserInfoAdapter.OnUserClickListener{

    private ArrayList<UserInfo> mUserInfos = new ArrayList<>();
    private UserInfoAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        UserInfoEngine engine = new UserInfoEngine(getContext());
//        for (int i = 0; i < 10; i++) {
//            engine.addUser(new UserInfo("Name " + i, "Surname " + i, i));
//        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_user_info, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                manager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        setData();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    private void setData() {
        UserInfoEngine userInfoEngine = new UserInfoEngine(getContext());
        if (!userInfoEngine.getAllUsers().isEmpty()) {
            mUserInfos.clear();
            mUserInfos.addAll(userInfoEngine.getAllUsers());
            mAdapter = new UserInfoAdapter(mUserInfos);
            mAdapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onUserClickItem(UserInfo userInfo) {

    }
}
