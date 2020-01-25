//package com.cloude.xmut.change_person_information;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.app.NavUtils;
//
//import com.cloude.xmut.MainActivity;
//import com.cloude.xmut.R;
//import com.cloude.xmut.httpClient.Post_to_updateinfo;
//import com.cloude.xmut.my_information.My_information;
//
//import java.io.IOException;
//
//
//public class ensure_self_sign extends My_information {
//    private Toolbar toolbar;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.change_self_sign);
//
//        toolbar = findViewById(R.id.main_toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.ensure,menu);
//
//        return true;
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        int id=item.getItemId();
//        if(id==android.R.id.home){
//            NavUtils.navigateUpFromSameTask(ensure_self_sign.this);
//        }
//        else if(id==R.id.ensure1){
//            ensure_self_sign_button();
//
//        }
//
//        return true;
//
//    }
//    public void ensure_self_sign_button() {
//
//        final EditText self_signET=(EditText)findViewById(R.id.change_self_sign_t);
//        final String userself_sign1=self_signET.getText().toString();
//            SharedPreferences sp = getSharedPreferences("user_self_sign", MODE_PRIVATE);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putString("self_sign", userself_sign1);
//            editor.commit();
//        final Handler myHandler = new Handler(){
//            public void handleMessage(Message msg){
//                String responseResult = (String)msg.obj;
//                //更新失败
//                if(responseResult.equals("false")){
//                    System.out.print("fail");
//                }
//                //更新成功
//                else if(responseResult.equals("true")){
//                    Intent intent = new Intent();
//                    intent.setClass(ensure_self_sign.this, My_information.class);
//                    startActivity(intent);
//                }
//            }
//        };
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Post_to_updateinfo guestToServer_1 = new Post_to_updateinfo();
//                try {
//                    SharedPreferences sp=getSharedPreferences("Coo",MODE_PRIVATE);
//                    String p=sp.getString("uname","000");
//                    String result = guestToServer_1.doPost(userself_sign1, "5",p);
//                    Message msg = new Message();
//                    msg.obj = result;
//                    myHandler.sendMessage(msg);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//    }
//
//}
