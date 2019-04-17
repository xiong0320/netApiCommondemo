package com.example.bear.netapicommondemo;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity {
    @BindView(R.id.user_name_til)
    TextInputLayout userNameTIL;
    @BindView(R.id.password_til)
    TextInputLayout passwordTIL;
    @BindView(R.id.login_btn)
    Button loginBtn;
    private static final String PATTERN = "[^\\x00-\\xff]";
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.login_btn)
    void login(){
        String userName = userNameTIL.getEditText().getText().toString();
        String password = passwordTIL.getEditText().getText().toString();
        if (!validateUserName(userName)) {
            userNameTIL.setErrorEnabled(true);
            Toast.makeText(this,"用户名含中文字符",Toast.LENGTH_SHORT).show();
        }else if (!validatePassword(password)) {
            passwordTIL.setErrorEnabled(true);
            Toast.makeText(this,"密码长度不能少于6",Toast.LENGTH_SHORT).show();
        } else {
            userNameTIL.setErrorEnabled(false);
            passwordTIL.setErrorEnabled(false);
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password) || password.length()<6) {
            return false;
        }
        return true;
    }

    private boolean validateUserName(String userName) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(userName);
        return !matcher.matches();
    }
}
