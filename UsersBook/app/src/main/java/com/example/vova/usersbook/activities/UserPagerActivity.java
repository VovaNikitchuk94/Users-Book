package com.example.vova.usersbook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.vova.usersbook.R;
import com.example.vova.usersbook.fragments.EditUserInfoFragment;
import com.example.vova.usersbook.models.UserInfo;
import com.example.vova.usersbook.models.engines.UserInfoEngine;

import java.util.ArrayList;

public class UserPagerActivity extends AppCompatActivity {

    public static final String KEY_USER_INTENT = "KEY_USER_INTENT";
    public static final String KEY_USER_ID = "KEY_USER_ID";
    public static final String KEY_IS_CREATE = "KEY_IS_CREATE";
    public static final String INTENT_USER_ID = "INTENT_USER_ID";

    private ViewPager mViewPager;
    private ArrayList<UserInfo> mUserInfos = new ArrayList<>();
    private UserInfo mUser = null;
    private long mUserID;
    private static boolean isCreate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pager);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                isCreate = bundle.getBoolean(KEY_IS_CREATE, false);
                if (isCreate) {
                    setContentView(R.layout.activity_fragment);
                    mUserID = bundle.getLong(KEY_USER_ID, -1);

                    FragmentManager manager = getSupportFragmentManager();

                    // add new fragment
                    Fragment fragment = new EditUserInfoFragment();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.add(R.id.fragment_container, fragment);
                    transaction.commit();
                } else {
                    setContentView(R.layout.activity_user_pager);
                    mUser = (UserInfo) bundle.get(UserPagerActivity.KEY_USER_INTENT);
                    setViewPager();
                }
            }
        }
    }

    private void setViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewPagerUserActivity);

        UserInfoEngine engine = new UserInfoEngine(getApplicationContext());
        mUserInfos.clear();
        mUserInfos.addAll(engine.getAllUsers());

        FragmentManager manager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                return EditUserInfoFragment.newInstance((mUserInfos.get(position)).getId());
            }

            @Override
            public int getCount() {
                return mUserInfos.size();
            }
        });

        for (int i = 0; i < mUserInfos.size(); i++) {
            if (mUserInfos.get(i).getId() == mUser.getId()) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static boolean isCreate() {
        return isCreate;
    }
}
