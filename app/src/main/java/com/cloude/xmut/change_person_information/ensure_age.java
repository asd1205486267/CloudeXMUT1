package com.cloude.xmut.change_person_information;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.cloude.xmut.MainActivity;
import com.cloude.xmut.R;
import com.cloude.xmut.httpClient.Post_to_updateinfo;
import com.cloude.xmut.my_information.My_information;

import java.io.IOException;


public class ensure_age extends My_information {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_age);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.ensure,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==android.R.id.home){
            NavUtils.navigateUpFromSameTask(ensure_age.this);
        }
        else if(id==R.id.ensure1){
            ensure_age_button();

        }

        return true;

    }
    public void ensure_age_button() {

        final EditText ageET=(EditText)findViewById(R.id.change_age_t);
        final String userage1=ageET.getText().toString();
        if(!"".equals(userage1)) {
            SharedPreferences sp = getSharedPreferences("user_age", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("age", userage1);
            editor.commit();
            final Handler myHandler = new Handler(){
                public void handleMessage(Message msg){
                    String responseResult = (String)msg.obj;
                    //更新失败
                    if(responseResult.equals("false")){
                        System.out.print("fail");
                    }
                    //更新成功
                    else if(responseResult.equals("true")){
                        Intent intent = new Intent();
                        intent.setClass(ensure_age.this, My_information.class);
                        startActivity(intent);
                    }
                }
            };
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Post_to_updateinfo guestToServer_1 = new Post_to_updateinfo();
                    try {
                        SharedPreferences sp=getSharedPreferences("Coo",MODE_PRIVATE);
                        String p=sp.getString("uname","000");
                        String result = guestToServer_1.doPost(userage1, "3",p);
                        Message msg = new Message();
                        msg.obj = result;
                        myHandler.sendMessage(msg);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        else{
            Toast.makeText(ensure_age.this,"年龄不能为空",Toast.LENGTH_SHORT).show();
        }



    }

}
