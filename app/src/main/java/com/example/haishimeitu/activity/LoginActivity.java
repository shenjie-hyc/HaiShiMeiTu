package com.example.haishimeitu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haishimeitu.MainActivity;
import com.example.haishimeitu.R;
import com.example.haishimeitu.util.Constants;
import com.example.haishimeitu.util.RegexUtil;
import com.example.haishimeitu.util.SharedPreferencesUtil;


public class LoginActivity /*extends AppCompatActivity*/ extends BaseActivity
{
    private EditText et_username;
    private EditText et_password;

    // private SharedPreferencesUtil sp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        Button bt_login = findViewById(R.id.bt_login);

        bt_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switch (view.getId())
                {
                    case R.id.bt_login:
                        login();
                        break;
                }
            }
        });

        // sp = SharedPreferencesUtil.getInstance(getApplicationContext());
    }

    // 获取用户输入的邮箱、密码、做校验
    private void login()
    {
        String username = et_username.getText().toString().trim();

        // 判断是否输入了邮箱
        if (TextUtils.isEmpty(username))
        {
            // 用户没有输入邮箱
            // makeText(Context context, int resId, int duration)
            // toast.show()
            Toast.makeText(this, R.string.email_hint, Toast.LENGTH_LONG).show();
            return;
        }

        // 通过正则表达式判断邮箱格式是否正确
        if (!RegexUtil.isEmail(username))
        {
            Toast.makeText(this, R.string.email_error, Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_password.getText().toString().trim();
        // 判断是否输入了密码
        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, R.string.password_hint, Toast.LENGTH_SHORT).show();
            return;
        }

        //判断密码长度是否为6~15位
        if (password.length() < 6 || password.length() > 15)
        {
            Toast.makeText(this, R.string.password_length_error, Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO 在这里就调用调用服务端的登录接口
        // 我们这里就简单实现,将密码和用户名都写到本地了
        if ((Constants.USERNAME.equals(username) && Constants.PASSWORD.equals(password)))
        {
            // TODO 通常软件的做法是,这里登录完成后保存一个标志,下次就不用在登录了
            // 我们这里就不讲解这么多了,因为涉及到的知识太多了
            // 登录成功,进入首页
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            sp.setLogin(true);

            // 关闭当前界面
            finish();
        }else
        {
            // 登录失败,进行提示
            //用户提示友好了对攻击你的人来说, 就更方便了,所以这里大家做一个权衡
            Toast.makeText(this, R.string.username_or_password_error, Toast.LENGTH_SHORT).show();

        }

    }
}
