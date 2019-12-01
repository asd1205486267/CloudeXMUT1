package com.cloude.xmut;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cloude.xmut.httpClient.LoginActivity;
import com.cloude.xmut.httpClient.Post_to_login;
import com.cloude.xmut.love.MomentListActivity;

public class My_information extends MainActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_infor);


        TextView usname=(TextView)findViewById(R.id.name1);
//        usname.setText(Post_to_login.ress);
        //   Button set_button = (Button) findViewById(R.id.set);  //设置
        //  set_button.setOnClickListener(new View.OnClickListener() {
        //      @Override
        //     public void onClick(View view) {
        //         String url = "";
        //        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

        //   }
        // });

        Button home_button = (Button) findViewById(R.id.home);  //主页
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(My_information.this, MainActivity.class);
                startActivity(intent);

            }
        });

        Button love_button = (Button) findViewById(R.id.love);  //表白墙
        love_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5=new Intent (My_information.this, MomentListActivity.class);
                startActivity(intent5);

            }
        });

        Button my_button = (Button) findViewById(R.id.my);  //我的
        my_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(Post_to_login.ress!=null) {
//                    Intent intent = new Intent(My_information.this, My_information.class);
//                    startActivity(intent);
//                }else{
//
//                }
            }
        });


    }






}
