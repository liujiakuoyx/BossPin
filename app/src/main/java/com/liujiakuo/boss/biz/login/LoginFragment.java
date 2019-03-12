package com.liujiakuo.boss.biz.login;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.liujiakuo.boss.R;
import com.liujiakuo.boss.base.BaseFragment;
import com.liujiakuo.boss.base.http.RequestDefine;
import com.liujiakuo.boss.base.http.response.DataResponse;
import com.liujiakuo.boss.config.ConfigData;
import com.liujiakuo.boss.utils.JsonUtils;
import com.liujiakuo.boss.utils.TextUtils;
import com.liujiakuo.core.http.CommonRequest;
import com.liujiakuo.core.http.HttpClient;
import com.liujiakuo.core.http.IParseNetwork;

import okhttp3.OkHttpClient;

/**
 * Created by 佳阔 on 2019/3/11.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private EditText mUserNameEdit;
    private EditText mUserPassEdit;
    private View mRegisterText;
    private View mLoginButton;

    @Override
    protected int getContentView() {
        return R.layout.login_main;
    }

    @Override
    protected void initViews(View view) {
        mUserNameEdit = view.findViewById(R.id.login_user_name);
        mUserPassEdit = view.findViewById(R.id.login_user_pass);
        mRegisterText = view.findViewById(R.id.login_register_account);
        mLoginButton = view.findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(this);
        mRegisterText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                doLogin();
                break;
            case R.id.login_register_account:
                doRegister();
                break;
        }
    }

    /**
     * 注册
     */
    private void doRegister() {

    }

    /**
     * 登录
     */
    private void doLogin() {
        String userName = mUserNameEdit.getText().toString().trim();
        String userPass = mUserPassEdit.getText().toString().trim();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPass)) {
            Toast.makeText(getContext(), "账号密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        CommonRequest<DataResponse<UserBean>> request = new CommonRequest<DataResponse<UserBean>>(
                RequestDefine.getLoginRequest(this, userName, userPass),
                new IParseNetwork() {
                    @Override
                    public DataResponse<UserBean> parseNetworkResponse(String jsonStr) {
                        return JsonUtils.fromJson(jsonStr, new TypeToken<DataResponse<UserBean>>() {
                        });
                    }
                });
        request.setNetCallBack(new CommonRequest.NetCallBack<DataResponse<UserBean>>() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(getContext(), "登录过程中出现问题", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(DataResponse<UserBean> response) {
                onLoginResponse(response);
            }
        });
        HttpClient.addRequest(request);
    }

    private void onLoginResponse(DataResponse<UserBean> response) {
        UserBean data = response.getData();
        int code = response.getCode();
        switch (code) {
            case 4000:
                Toast.makeText(getContext(), "账号不存在", Toast.LENGTH_SHORT).show();
                break;
            case 5000:
                Toast.makeText(getContext(), "密码错误", Toast.LENGTH_SHORT).show();
                break;
            case 200:
                //登录成功，跳回页面。
                if (data == null) {
                    Toast.makeText(getContext(), "登录过程中出现异常", Toast.LENGTH_SHORT).show();
                    return;
                }
                ConfigData.putLoginInfo(data);
                getActivity().finish();
                break;
            default:
                Toast.makeText(getContext(), "登录过程中出现异常", Toast.LENGTH_SHORT).show();
        }
    }
}
