package com.example.vova.usersbook.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.vova.usersbook.R;
import com.example.vova.usersbook.fragments.UserListFragment;

public class UserListActivity extends AppCompatActivity {

//    private ArrayList<UserInfo> mUserInfos = new ArrayList<>();
//    private UserInfoAdapter mAdapter;
//    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_info);

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new UserListFragment();
            manager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewUserInfoActivity);
//        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        mRecyclerView.setLayoutManager(manager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
//                manager.getOrientation());
//        mRecyclerView.addItemDecoration(dividerItemDecoration);
//
//        setData();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.item_list_user_info, menu);
//        return super.onCreateOptionsMenu(menu);
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.menu.item_list_user_info:
//                //TODO add intent
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//
//        }
//    }

//    private void setData() {
//        UserInfoEngine userInfoEngine = new UserInfoEngine(getApplicationContext());
//        if (!userInfoEngine.getAllUsers().isEmpty()) {
//            mUserInfos.clear();
//            mUserInfos.addAll(userInfoEngine.getAllUsers());
//            mAdapter = new UserInfoAdapter(mUserInfos);
//            mAdapter.notifyDataSetChanged();
//            mRecyclerView.setAdapter(mAdapter);
//        }
//    }
}
