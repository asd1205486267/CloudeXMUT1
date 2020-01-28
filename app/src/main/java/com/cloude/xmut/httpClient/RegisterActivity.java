package com.cloude.xmut.httpClient;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import android.app.Activity;
import android.content.ContentResolver;
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
import android.widget.Toast;
import android.widget.Toolbar;

import com.cloude.xmut.R;
import com.cloude.xmut.UserManage.User;
import com.google.android.material.snackbar.Snackbar;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

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

        Toolbar toolbar =(Toolbar)findViewById(R.id.register_toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String user_name=account.getText().toString();
                String key1=password1.getText().toString();
                String key2=password2.getText().toString();
                if(user_name.equals("")) {
                    account.setError("账号不能为空！");
                }
                else if(user_name.length()!=10){
                    account.setError("请输入正确的学号！");
                }
                else if(key1.equals("")) {
                    password1.setError("密码不能为空！");
                }else if(key2.equals("")) {
                    password2.setError("请确认密码！！");
                } else if(!key1.equals(key2)) {
                    password2.setError("两次输入的密码不一致！");
                }else {
                    User user=new User();
                  /*  String path= ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getResources().getResourcePackageName(R.drawable.no_avatar) + "/" +
                            getResources().getResourceTypeName(R.drawable.no_avatar) + "/" + getResources().getResourceEntryName(R.drawable.no_avatar);
                    BmobFile avatar=new BmobFile(new File(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getResources().getResourcePackageName(R.drawable.no_avatar) + "/" +
                            getResources().getResourceTypeName(R.drawable.no_avatar) + "/" + getResources().getResourceEntryName(R.drawable.no_avatar)));
                    avatar.upload(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null)
                            {
                                user.setAvatar(avatar);
                                Toast.makeText(RegisterActivity.this,"success",Toast.LENGTH_SHORT).show();
                            }
                        }  这里是头像测试
                    });*/

                    user.setUsername(user_name);
                    user.setPassword(key1);
                    user.setAge(99);
                    user.setNickname(user_name);
                    user.setGender(0);
                    user.setPersonal_note("此用户未填写");

                  //  user.setAvatar(R.drawable.no_avatar);
                    user.signUp(new SaveListener<User>() {
                        @Override
                        public void done(User user, BmobException e) {
                            if (e == null) {
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                                Intent intent=new Intent (RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "失败：" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

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



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
