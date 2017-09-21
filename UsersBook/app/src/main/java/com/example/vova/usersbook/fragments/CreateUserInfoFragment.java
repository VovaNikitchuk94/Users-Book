package com.example.vova.usersbook.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vova.usersbook.R;
import com.example.vova.usersbook.activities.UserCreateActivity;
import com.example.vova.usersbook.models.UserInfo;
import com.example.vova.usersbook.models.engines.UserInfoEngine;

public class CreateUserInfoFragment extends Fragment {

    private static final String DIALOG_DATE = "DIALOG_DATE";
    private static final int REQUEST_DATE = 0;
    private UserInfo mUserInfo = null;
    private long mUserID;

    private TextView mTextViewName, mTextViewSurname, mTextViewBDay, mTextViewNewBDay;
    private EditText mEditTextName, mEditTextSurname;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mUserID = getActivity().getIntent().getLongExtra(UserCreateActivity.INTENT_USER_ID, -1);
        UserInfoEngine engine = new UserInfoEngine(getContext());
        if (mUserID != -1) {
            mUserInfo = engine.getUserByID(mUserID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_info, container, false);

        mTextViewName = (TextView) view.findViewById(R.id.textViewUserNameFragmentUserInfo);
        mTextViewSurname = (TextView) view.findViewById(R.id.textViewUserSurnameFragmentUserInfo);
        mTextViewBDay = (TextView) view.findViewById(R.id.textViewUserBDayFragmentUserInfo);
        mTextViewNewBDay = (TextView) view.findViewById(R.id.textViewSetUserBDayFragmentUserInfo);
//        if (mUserInfo != null) {
//            updateDate(mUserInfo.getUserBDay());
//        }
        mTextViewNewBDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mUserInfo.getUserBDay());
                dialog.setTargetFragment(CreateUserInfoFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mEditTextName = (EditText) view.findViewById(R.id.editTextUserNameFragmentUserInfo);
        mEditTextSurname = (EditText) view.findViewById(R.id.editTextUserSurnameFragmentUserInfo);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_edit_user_info, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_user:
                createNewUser();
                return true;
            case android.R.id.home:
                deleteUser();
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            UserInfoEngine engine = new UserInfoEngine(getContext());
            String date = data.getStringExtra(DatePickerFragment.EXTRA_DATE);
            mUserInfo.setUserBDay(date);
            engine.updateUser(mUserInfo);
            mTextViewNewBDay.setText(date);
        }
    }

    private void deleteUser() {
        UserInfoEngine engine = new UserInfoEngine(getContext());
        engine.removeUserById(mUserID);
    }

    private void createNewUser() {
        UserInfoEngine engine = new UserInfoEngine(getContext());
        String name = mEditTextName.getText().toString();
        String surname = mEditTextSurname.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(getContext(), R.string.textNameCannotBeEmpty, Toast.LENGTH_SHORT).show();
        } else {
            if (mUserInfo != null) {
                mUserInfo.setUserName(name);
                mUserInfo.setUserSurname(surname);
                engine.updateUser(mUserInfo);
                getActivity().finish();
            }
        }
    }
}
