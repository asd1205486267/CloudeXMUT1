package com.cloude.xmut.httpClient;
import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cloude.xmut.R;

public class RegisterActivity extends Activity{
    private Button button;
    private EditText account;
    private EditText password1;
    private EditText password2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        init();
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String user_name=account.getText().toString().trim();
                String key1=password1.getText().toString().trim();
                String key2=password2.getText().toString().trim();
                if(user_name.equals("")) {
                    account.setError("账号不能为空！");
                }else if(key1.equals("")) {
                    password1.setError("密码不能为空！");
                }else if(key2.equals("")) {
                    password2.setError("请确认密码！！");
                } else if(!key1.equals(key2)) {
                    password2.setError("两次输入的密码不一致！");
                }else {
                    final Handler myHandler = new Handler(){
                        public void handleMessage(Message msg){
                            String responseResult = (String)msg.obj;
                            //登录失败
                            if(responseResult.equals("false")){
                                System.out.print("fail");
                                Toast.makeText(RegisterActivity.this, "用户名已被注册！", Toast.LENGTH_LONG).show();
                            }
                            //登录成功
                            else if(responseResult.equals("true")){
                                Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_LONG).show();
                                Intent intent=new Intent (RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                    };

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Post_to_register guestToServer_1 = new Post_to_register();
                            try {
                                String result = guestToServer_1.doPost(account.getText().toString().trim(), password1.getText().toString().trim());
                                Message msg = new Message();
                                msg.obj = result;
                                myHandler.sendMessage(msg);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });
    }
    private void init() {
        button=(Button)findViewById(R.id.Register1);
        account=(EditText)findViewById(R.id.account_register);
        password1=(EditText)findViewById(R.id.password_register1);
        password2=(EditText)findViewById(R.id.password_register2);
    }
}
