package com.example.vova.usersbook.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.vova.usersbook.models.UserInfo;
import com.example.vova.usersbook.models.engines.UserInfoEngine;

public class EditUserInfoFragment extends Fragment {

    public static final String ARG_USER_ID = "ARG_USER_ID";

    private UserInfo mUserInfo = null;
    private long mUserID;

    private TextView mTextViewName, mTextViewSurname, mTextViewBDay, mTextViewNewBDay;
    private EditText mEditTextName, mEditTextSurname;

    public static EditUserInfoFragment newInstance(long id) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER_ID, id);

        EditUserInfoFragment fragment = new EditUserInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mUserID = (long) getArguments().getSerializable(ARG_USER_ID);
        UserInfoEngine engine = new UserInfoEngine(getContext());
        mUserInfo = engine.getUserByID(mUserID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);

        mTextViewName = (TextView) view.findViewById(R.id.textViewUserNameFragmentUserInfo);
        mTextViewSurname = (TextView) view.findViewById(R.id.textViewUserSurnameFragmentUserInfo);
        mTextViewBDay = (TextView) view.findViewById(R.id.textViewUserBDayFragmentUserInfo);
        mTextViewNewBDay = (TextView) view.findViewById(R.id.textViewSetUserBDayFragmentUserInfo);

        mEditTextName = (EditText) view.findViewById(R.id.editTextUserNameFragmentUserInfo);
        mEditTextSurname = (EditText) view.findViewById(R.id.editTextUserSurnameFragmentUserInfo);

        if (mUserInfo != null) {
            mEditTextName.setText(mUserInfo.getUserName());
            mEditTextSurname.setText(mUserInfo.getUserSurname());
        }

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
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
