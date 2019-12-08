package com.cloude.xmut.main_button;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.cloude.xmut.R;

import java.util.ArrayList;
import java.util.List;

public class phone_school extends AppCompatActivity {

    private Toolbar toolbar;

    List<String> list = new ArrayList<String>();
    ListView listView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_school);

        toolbar = findViewById(R.id.phone_school_toolbar);
        setActionBar(toolbar);
        getActionBar().setTitle("厦门理工学院办公电话表");
        getActionBar().setDisplayHomeAsUpEnabled(true);


        listView = (ListView) findViewById(R.id.phone_school_list);


        list.add("集美校区校办");  //0592  6291536

        list.add("机械与汽车工程学院");    //6291386
        list.add("材料科学与工程学院");    //"6291328"
        list.add("电气工程与自动化学院");     //"6291394"
        list.add("光电与通信工程学院");     //"6291615"
        list.add("计算机与信息工程学院");    //"6291317"
        list.add("土木工程与建筑学院");    //"6291229"
        list.add("环境科学与工程学院");    //"6291138"
        list.add("经济与管理学院");    //6291191"
        list.add("文化产业与旅游学院");    //"6291105"
        list.add("设计艺术学院");    //"6291130"
        list.add("时尚学院");    //"6291758"
        list.add("应用数学学院");    //"6291258"
        list.add("外国语学院");    //"6291202"
        list.add("马克思主义学院");    //6291307"
        list.add("体育部");    //"6291131"
        list.add("软件工程学院");    //2186160"
        list.add("继续教育学院");    //"2189129"
        list.add("国际学院");    //"6291058"
        list.add("研究生学院");    //"6291820"
        list.add("图书馆");    //"6291172"
        list.add("信息中心");    //"6291018"


        list.add("组织部");    //"6291906"
        list.add("宣传部");    //"6291903"
        list.add("统战部");    //"6291778"
        list.add("学工处");    //"6291309"
        list.add("监审处");    //"6291117"
        list.add("教务处");    //"6291566"
        list.add("科研处");    //"6291230"
        list.add("人事处");    //"6291113"
        list.add("规品处");    //"6291108"
        list.add("国合处");    //"6291577"
        list.add("财资处");    //"6291761"
        list.add("后勤处");    //"6291291"
        list.add("基建处");    //"6291092"
        list.add("保卫处");    //"6291513"
        list.add("产融处");   //"6291822"
        list.add("工  会");    //"6291511"
        list.add("团  委");    //"6291661"
        list.add("法务室");    //"6291551"
        list.add("基金会秘书处");    //"6291060"
        list.add("校友会");    //"6291660"
        list.add("档案馆");    //"6291122"

        list.add("现代工程训练中心");    //"6291597"
        list.add("资产公司");    //"6291898"


        ListViewAdapter adapter = new ListViewAdapter(list, this);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String telephone="";
                switch (position){
                    case 0: telephone="6291536";break;
                    case 1: telephone="6291386";break;
                    case 2: telephone="6291328";break;
                    case 3: telephone="6291394";break;
                    case 4: telephone="6291615";break;
                    case 5: telephone="6291317";break;
                    case 6: telephone="6291229";break;
                    case 7: telephone="6291138";break;
                    case 8: telephone="6291191";break;
                    case 9: telephone="6291105";break;
                    case 10: telephone="6291130";break;
                    case 11: telephone="6291758";break;
                    case 12: telephone="6291258";break;
                    case 13: telephone="6291202";break;
                    case 14: telephone="6291307";break;
                    case 15: telephone="6291131";break;
                    case 16: telephone="2186160";break;
                    case 17: telephone="2189129";break;
                    case 18: telephone="6291058";break;
                    case 19: telephone="6291820";break;
                    case 20: telephone="6291172";break;
                    case 21: telephone="6291018";break;
                    case 22: telephone="6291906";break;
                    case 23: telephone="6291903";break;
                    case 24: telephone="6291778";break;
                    case 25: telephone="6291309";break;
                    case 26: telephone="6291117";break;
                    case 27: telephone="6291566";break;
                    case 28: telephone="6291230";break;
                    case 29: telephone="6291113";break;
                    case 30: telephone="6291108";break;
                    case 31: telephone="6291577";break;
                    case 32: telephone="6291761";break;
                    case 33: telephone="6291291";break;
                    case 34: telephone="6291092";break;
                    case 35: telephone="6291513";break;
                    case 36: telephone="6291822";break;
                    case 37: telephone="6291511";break;
                    case 38: telephone="6291661";break;
                    case 39: telephone="6291551";break;
                    case 40: telephone="6291060";break;
                    case 41: telephone="6291660";break;
                    case 42: telephone="6291122";break;
                    case 43: telephone="6291597";break;
                    case 44: telephone="6291898";break;
                }
                Intent Intent =  new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:" + telephone));//跳转到拨号界面，同时传递电话号码
                startActivity(Intent);
            }
        });
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
