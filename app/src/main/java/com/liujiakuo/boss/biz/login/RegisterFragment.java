package com.liujiakuo.boss.biz.login;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.gson.reflect.TypeToken;
import com.liujiakuo.boss.R;
import com.liujiakuo.boss.base.BaseFragment;
import com.liujiakuo.boss.base.http.RequestDefine;
import com.liujiakuo.boss.base.http.response.MessageResponse;
import com.liujiakuo.boss.utils.JsonUtils;
import com.liujiakuo.boss.utils.TextUtils;
import com.liujiakuo.core.http.CommonRequest;
import com.liujiakuo.core.http.HttpClient;
import com.liujiakuo.core.http.IParseNetwork;

/**
 * Created by 佳阔 on 2019/3/15.
 */

public class RegisterFragment extends BaseFragment {
    private EditText mNickEdit, mAccountEdit, mPassEdit, mConfirmPassEdit, mPostEdit;
    private RadioGroup mRadioGroup;
    private View mPostLayout;
    private boolean isCompanyAccount = false;

    @Override
    protected int getContentView() {
        return R.layout.register_main;
    }

    @Override
    protected void initViews(View view) {
        mRadioGroup = view.findViewById(R.id.register_account_type);
        mNickEdit = view.findViewById(R.id.register_nick_edit);
        mAccountEdit = view.findViewById(R.id.register_account_edit);
        mPassEdit = view.findViewById(R.id.register_password_edit);
        mConfirmPassEdit = view.findViewById(R.id.register_confirm_pass_edit);
        mPostLayout = view.findViewById(R.id.register_post_layout);
        mPostEdit = view.findViewById(R.id.register_post_edit);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.personal_account) {
                    mPostLayout.setVisibility(View.GONE);
                    isCompanyAccount = false;
                } else if (checkedId == R.id.company_account) {
                    mPostLayout.setVisibility(View.VISIBLE);
                    isCompanyAccount = true;
                }
            }
        });
        view.findViewById(R.id.register_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegister(v);
            }
        });
    }

    /**
     * 注册
     */
    public void onRegister(View view) {
        if (checkEdit()) {
            //注册
            UserBean userInfo = new UserBean();
            userInfo.setNick(mNickEdit.getText().toString());
            userInfo.setKey(mAccountEdit.getText().toString());
            userInfo.setPass(mPassEdit.getText().toString());
            userInfo.setType(1);
            if (isCompanyAccount) {
                //如果是企业用户
                userInfo.setType(2);
                userInfo.setPost(mPostEdit.getText().toString());
            }
            sendRegisterNet(userInfo);
        }
    }

    private void sendRegisterNet(UserBean userInfo) {
        if (userInfo == null) {
            return;
        }
        String json = JsonUtils.toJson(userInfo);
        CommonRequest<MessageResponse> request = new CommonRequest<>(
                RequestDefine.getRegisterRequest(json),
                new IParseNetwork<MessageResponse>() {
                    @Override
                    public MessageResponse parseNetworkResponse(String jsonStr) {
                        return JsonUtils.fromJson(jsonStr, new TypeToken<MessageResponse>() {
                        });
                    }
                });
        request.setNetCallBack(new CommonRequest.NetCallBack<MessageResponse>() {
            @Override
            public void onFailure(Exception e) {
                showToast("注册失败");
            }

            @Override
            public void onResponse(MessageResponse response) {
                /* code: 2000 success 2001 账户已存在 2002 参数错误*/
                switch (response.getCode()) {
                    case 2000:
                        showToast("注册成功");
                        getActivity().finish();
                        break;
                    case 2001:
                        showToast("账户已存在");
                        break;
                    case 2002:
                        showToast("注册失败，参数错误");
                        break;
                }
            }
        });
        HttpClient.addRequest(request);
    }

    private boolean checkEdit() {
        if (EditIsEmpty(mNickEdit)) {
            showToast("昵称不能为空");
            return false;
        }
        if (EditIsEmpty(mAccountEdit)) {
            showToast("账户不能为空");
            return false;
        }
        if (EditIsEmpty(mPassEdit)) {
            showToast("密码不能为空");
            return false;
        }
        if (EditIsEmpty(mConfirmPassEdit)) {
            showToast("请确认密码");
            return false;
        }
        if (!mPassEdit.getText().toString().equals(mConfirmPassEdit.getText().toString())) {
            showToast("两次密码不一样哦");
            return false;
        }
        if (isCompanyAccount && EditIsEmpty(mPostEdit)) {
            showToast("请填写您的职位");
            return false;
        }
        return true;
    }

    private boolean EditIsEmpty(EditText edit) {
        return edit == null || TextUtils.isEmpty(edit.getText().toString());
    }
}
