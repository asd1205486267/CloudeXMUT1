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
import com.cloude.xmut.my_information.My_information;
import com.cloude.xmut.R;

public class LoginActivity extends Activity {
    private EditText name;			//用户名输入框
    private EditText pwd;			//登录密码输入框
    private Button button1;			//登录按钮
    private Button button2;			//注册按钮
    public static String name_sql="null";
    public static String sex_sql="null";
    public static String age_sql="null";
    public static String city_sql="null";
    public static String sign_sql="null";
    private TextView textView;
    private DefaultHttpClient httpClient;
    private  String ress;
    public static  String test="null";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        init();
        SharedPreferences sp=getSharedPreferences("New",MODE_PRIVATE);
        String p=sp.getString("newname","");
        name.setText(p);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Handler myHandler = new Handler(){
                    public void handleMessage(Message msg){
                        String responseResult = (String)msg.obj;
                        textView.setText(responseResult);
                        //登录失败
                        if(responseResult.equals("false")){
                            Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_LONG).show();
                        }
                        //登录成功
                        else {
                            SharedPreferences sp=getSharedPreferences("Coo",MODE_PRIVATE);
                            SharedPreferences.Editor editor=sp.edit();
                            editor.putString("uname",Post_to_login.r1);
                            editor.putString("pwd",Post_to_login.r2);
                            editor.commit();

                            SharedPreferences sp1=getSharedPreferences("username",MODE_PRIVATE);
                            SharedPreferences.Editor editor1=sp1.edit();
                            editor1.putString("name",name_sql);
                            editor1.commit();

                            SharedPreferences sp2=getSharedPreferences("user_sex",MODE_PRIVATE);
                            SharedPreferences.Editor editor2=sp2.edit();
                            editor2.putString("sex",sex_sql);
                            editor2.commit();

                            SharedPreferences sp3=getSharedPreferences("user_age",MODE_PRIVATE);
                            SharedPreferences.Editor editor3=sp3.edit();
                            editor3.putString("age",age_sql);
                            editor3.commit();

                            SharedPreferences sp4=getSharedPreferences("user_address",MODE_PRIVATE);
                            SharedPreferences.Editor editor4=sp4.edit();
                            editor4.putString("address",city_sql);
                            editor4.commit();

                            SharedPreferences sp5=getSharedPreferences("user_self_sign",MODE_PRIVATE);
                            SharedPreferences.Editor editor5=sp5.edit();
                            editor5.putString("self_sign",sign_sql);
                            editor5.commit();

                            Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent (LoginActivity.this, My_information.class);
                            startActivity(intent);
                        }
                    }
                };

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Post_to_login guestToServer = new Post_to_login();
                        try {
                            String result = guestToServer.doPost(name.getText().toString().trim(), pwd.getText().toString().trim());
                            Message msg = new Message();
                            msg.obj = result;						//servlet中out.print()的值
                            myHandler.sendMessage(msg);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
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
       textView=(TextView)findViewById(R.id.textview);

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
