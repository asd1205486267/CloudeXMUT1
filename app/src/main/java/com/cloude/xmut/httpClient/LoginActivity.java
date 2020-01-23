package com.cloude.xmut.httpClient;

import android.app.Activity;
import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.List;
import org.apache.http.cookie.Cookie;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import com.cloude.xmut.MainActivity;
import com.cloude.xmut.UserManage.User;
import com.cloude.xmut.my_information.My_information;
import com.cloude.xmut.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends Activity {
    private EditText name;			//用户名输入框
    private EditText pwd;			//登录密码输入框
    private Button button1;			//登录按钮
    private Button button2;			//注册按钮
//    public static String name_sql="null";
//    public static String sex_sql="null";
//    public static String age_sql="null";
//    public static String city_sql="null";
//    public static String sign_sql="null";
//    private TextView textView;
//    private DefaultHttpClient httpClient;
//    private  String ress;
//    public static  String test="null";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        init();
//        SharedPreferences sp=getSharedPreferences("New",MODE_PRIVATE);
//        String p=sp.getString("newname","");
//        name.setText(p);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String user_name=name.getText().toString();
                String user_pwd=pwd.getText().toString();

                User user=new User();
                user.setUsername(user_name);
                user.setPassword(user_pwd);
                user.login(new SaveListener<User>() {
                    @Override
                    public void done(User bmobUser, BmobException e) {
                        if (e == null) {
                            User user = User.getCurrentUser(User.class);
                            Toast.makeText(LoginActivity.this, "登录成功：" + user.getUsername(), Toast.LENGTH_LONG).show();
                            Intent intent=new Intent (LoginActivity.this, My_information.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "登录失败：" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent1=new Intent (LoginActivity.this,RegisterActivity.class);
                startActivity(intent1);
            }
        });

    }
    private void init() {
        name = (EditText)findViewById(R.id.account);
        pwd = (EditText)findViewById(R.id.password);
        button1 = (Button)findViewById(R.id.login);
        button2 = (Button)findViewById(R.id.Register);
//       textView=(TextView)findViewById(R.id.textview);

        Toolbar toolbar =(Toolbar)findViewById(R.id.login_toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
