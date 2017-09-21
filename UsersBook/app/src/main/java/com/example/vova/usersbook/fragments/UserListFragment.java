package com.example.vova.usersbook.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.vova.usersbook.R;
import com.example.vova.usersbook.activities.UserPagerActivity;
import com.example.vova.usersbook.adapters.UserInfoAdapter;
import com.example.vova.usersbook.models.UserInfo;
import com.example.vova.usersbook.models.engines.UserInfoEngine;

import java.util.ArrayList;

public class UserListFragment extends Fragment implements UserInfoAdapter.OnUserClickListener,
UserInfoAdapter.OnLongUserClickListener{

    private ArrayList<UserInfo> mUserInfos = new ArrayList<>();
    private UserInfoAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_user_list, container, false);

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
        inflater.inflate(R.menu.menu_list_user_info, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_user:
                Intent intent = new Intent(getContext(), UserPagerActivity.class);
                UserInfo userInfo = new UserInfo();
                UserInfoEngine engine = new UserInfoEngine(getContext());
                long id = engine.addUser(userInfo);

                intent.putExtra(UserPagerActivity.INTENT_USER_ID, id);
                intent.putExtra(UserPagerActivity.KEY_IS_CREATE, true);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setData() {
        UserInfoEngine userInfoEngine = new UserInfoEngine(getContext());

        if (!userInfoEngine.getAllUsers().isEmpty()) {
            mUserInfos.clear();
            mUserInfos.addAll(userInfoEngine.getAllUsers());
            mAdapter = new UserInfoAdapter(mUserInfos);
            mAdapter.setOnUserClickListener(UserListFragment.this);
            mAdapter.setOnLongUserClickListener(UserListFragment.this);
            mAdapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onUserClickItem(UserInfo userInfo) {
        Intent intent = new Intent(getActivity(), UserPagerActivity.class);
        intent.putExtra(UserPagerActivity.KEY_USER_INTENT, userInfo);
        startActivity(intent);
    }

    @Override
    public void onLongUserClickItem(final UserInfo userInfo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false)
                .setMessage(R.string.textMessageDeleteUser)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserInfoEngine engine = new UserInfoEngine(getContext());
                        engine.removeUserById(userInfo.getId());
                        updateUI();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updateUI() {
        UserInfoEngine engine = new UserInfoEngine(getContext());
        mUserInfos.clear();
        mUserInfos.addAll(engine.getAllUsers());
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI();
    }
}
