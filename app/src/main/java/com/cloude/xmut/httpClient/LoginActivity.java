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
import java.util.List;
import org.apache.http.cookie.Cookie;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import com.cloude.xmut.MainActivity;
import com.cloude.xmut.My_information;
import com.cloude.xmut.R;

public class LoginActivity extends Activity {
    private EditText name;			//用户名输入框
    private EditText pwd;			//登录密码输入框
    private Button button1;			//登录按钮
    private Button button2;			//注册按钮
    private TextView textView_response;
    private DefaultHttpClient httpClient;
    private  String ress;
    public static  String test="null";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        init();
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Handler myHandler = new Handler(){
                    public void handleMessage(Message msg){
                        String responseResult = (String)msg.obj;
                        textView_response.setText(responseResult);
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
        textView_response = (TextView)findViewById(R.id.textview);
    }
}
