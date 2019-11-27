package com.cloude.xmut;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;

import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cloude.xmut.animation.SceneryActivity;
import com.cloude.xmut.zxing.android.CaptureActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/{
    private Banner banner;
    private ArrayList<Integer> list_path;   //网页 String 本地 Integer
    private ArrayList<String> list_title;

   /* private Button mBtnScenery;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.news:
                SceneryActivity.startActivity(this);
                break;
            default:
                break;
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //MovementMethod(ScrollingMovementMethod.getInstance());

       /* mBtnScenery = (Button) findViewById(R.id.news);
        mBtnScenery.setOnClickListener(this);*/

        Button mainpage_button = (Button) findViewById(R.id.mainpage);  //学校主页
        mainpage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViews.class);
                String ur = "http://www.xmut.edu.cn/";  //传入的网址
                Bundle bundle = new Bundle();
                bundle.putString("ur", ur);  //内容，名字
                intent.putExtra("url", bundle);  //总体名字，BUNDLE名字
                startActivity(intent);
            }
        });

        Button jiaowu_button = (Button) findViewById(R.id.jiaowu);  //教务
        jiaowu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViews.class);

                String ur = "https://jw.webvpn.xmut.edu.cn/";  //传入的网址
                Bundle bundle = new Bundle();
                bundle.putString("ur", ur);  //内容，名字
                intent.putExtra("url", bundle);  //总体名字，BUNDLE名字
                startActivity(intent);
            }
        });

        Button myinformation_button = (Button) findViewById(R.id.myinformation);  //个人信息
        myinformation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViews.class);

                String ur = "https://authserver.webvpn.xmut.edu.cn/authserver/login";  //传入的网址
                Bundle bundle = new Bundle();
                bundle.putString("ur", ur);  //内容，名字
                intent.putExtra("url", bundle);  //总体名字，BUNDLE名字
                startActivity(intent);
            }
        });

        Button allresource_button = (Button) findViewById(R.id.allresource);  //所有资源
        allresource_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViews.class);
                String ur = "https://webvpn.xmut.edu.cn/";  //传入的网址
                Bundle bundle = new Bundle();
                bundle.putString("ur", ur);  //内容，名字
                intent.putExtra("url", bundle);  //总体名字，BUNDLE名字
                startActivity(intent);
                // Toast.makeText(getApplicationContext(),"如没显示，多点几次",Toast.LENGTH_SHORT).show();
            }
        });

        Button course_button = (Button) findViewById(R.id.course);  //课程中心
        course_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViews.class);
                String ur = "http://kczx.xmut.edu.cn/";  //传入的网址
                Bundle bundle = new Bundle();
                bundle.putString("ur", ur);  //内容，名字
                intent.putExtra("url", bundle);  //总体名字，BUNDLE名字
                startActivity(intent);
                // Toast.makeText(getApplicationContext(),"记得用校园网哦",Toast.LENGTH_SHORT).show();
            }
        });

        Button lab_button = (Button) findViewById(R.id.lab);  //实验室
        lab_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViews.class);
                String ur = "http://lab.xmut.edu.cn/";  //传入的网址
                Bundle bundle = new Bundle();
                bundle.putString("ur", ur);  //内容，名字
                intent.putExtra("url", bundle);  //总体名字，BUNDLE名字
                startActivity(intent);
                // Toast.makeText(getApplicationContext(),"记得用校园网哦",Toast.LENGTH_SHORT).show();
            }
        });


        Button library_button = (Button) findViewById(R.id.library);  //图书馆
        library_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViews.class);
                String ur = "https://lib.webvpn.xmut.edu.cn/";  //传入的网址
                Bundle bundle = new Bundle();
                bundle.putString("ur", ur);  //内容，名字
                intent.putExtra("url", bundle);  //总体名字，BUNDLE名字
                startActivity(intent);
                // Toast.makeText(getApplicationContext(),"记得用校园网哦",Toast.LENGTH_SHORT).show();
            }
        });

       /* Button wrench_button=(Button) findViewById(R.id.wrench);  //维修
        wrench_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WebViews.class);
                String ur="http://zw.xmut.edu.cn/service.asp?Classid=72";  //传入的网址
                Bundle bundle = new Bundle();
                bundle.putString("ur",ur);  //内容，名字
                intent.putExtra("url",bundle);  //总体名字，BUNDLE名字
                startActivity(intent);
            }
        });*/

        Button storeonline_button = (Button) findViewById(R.id.storeonline);  //文化集市
        storeonline_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViews.class);
                String ur = "https://xmut1981.kuaizhan.com/clubpc/forums/VoITQj2o62YvG2lU#!/topics/tags/all/pages/0";  //传入的网址
                Bundle bundle = new Bundle();
                bundle.putString("ur", ur);  //内容，名字
                intent.putExtra("url", bundle);  //总体名字，BUNDLE名字
                startActivity(intent);
            }
        });

        Button volunteer_button = (Button) findViewById(R.id.volunteer);  //志愿者
        volunteer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViews.class);
                String ur = "http://zyz.xmut.edu.cn/";  //传入的网址
                Bundle bundle = new Bundle();
                bundle.putString("ur", ur);  //内容，名字
                intent.putExtra("url", bundle);  //总体名字，BUNDLE名字
                startActivity(intent);
                //  Toast.makeText(getApplicationContext(),"记得用校园网哦",Toast.LENGTH_SHORT).show();
            }
        });

        Button lovehelp_button = (Button) findViewById(R.id.lovehelp);  //乐帮帮
        lovehelp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViews.class);
                String ur = "https://xmut1981.kuaizhan.com/clubpc/forums/ViY8BpP84SuGmHXZ#!/topics/tags/all/pages/0";  //传入的网址
                Bundle bundle = new Bundle();
                bundle.putString("ur", ur);  //内容，名字
                intent.putExtra("url", bundle);  //总体名字，BUNDLE名字
                startActivity(intent);
            }
        });

        Button xiaotucao_button = (Button) findViewById(R.id.xiaotucao);  //小吐槽
        xiaotucao_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViews.class);
                String ur = "https://xmut1981.kuaizhan.com/clubpc/forums/WQwDfb9lnhCdVcTq#!/topics/tags/all/pages/0";  //传入的网址
                Bundle bundle = new Bundle();
                bundle.putString("ur", ur);  //内容，名字
                intent.putExtra("url", bundle);  //总体名字，BUNDLE名字
                startActivity(intent);
            }
        });


        Button calendar_button = (Button) findViewById(R.id.calendar);   //校历
        calendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViews.class);
                String ur = "http://yjs.xmut.edu.cn/bszn/201806/t20180615_213535.html";  //传入的网址
                Bundle bundle = new Bundle();
                bundle.putString("ur", ur);  //内容，名字
                intent.putExtra("url", bundle);  //总体名字，BUNDLE名字
                startActivity(intent);
            }
        });

      /*  Button course_table_button=(Button) findViewById(R.id.course_table);   //代办事项
        course_table_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"暂未接入",Toast.LENGTH_SHORT).show();
            }
        });*/

        Button map_button = (Button) findViewById(R.id.map);   //地图
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViews.class);
                String ur = "http://3d.xmut.edu.cn/";  //传入的网址
                Bundle bundle = new Bundle();
                bundle.putString("ur", ur);  //内容，名字
                intent.putExtra("url", bundle);  //总体名字，BUNDLE名字
                startActivity(intent);
            }
        });

//        Button todayvoice_button=(Button) findViewById(R.id.todayvoice);  //今日电台
//        todayvoice_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,WebViews.class);
//                String ur="https://music.163.com/#/discover/toplist?id=3778678";  //传入的网址
//                Bundle bundle = new Bundle();
//                bundle.putString("ur",ur);  //内容，名字
//                intent.putExtra("url",bundle);  //总体名字，BUNDLE名字
//                startActivity(intent);
//                Toast.makeText(getApplicationContext(),"暂未接入",Toast.LENGTH_SHORT).show();
//            }
//        });

         Button news_button = (Button) findViewById(R.id.news);   //新闻
        news_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, WebViews.class);
                String ur = "https://www.toutiao.com/";  //传入的网址
                Bundle bundle = new Bundle();
                bundle.putString("ur", ur);  //内容，名字
                intent.putExtra("url", bundle);  //总体名字，BUNDLE名字
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "暂未接入", Toast.LENGTH_SHORT).show();

            }
        });

        Button suggestions_button = (Button) findViewById(R.id.suggestions);  //建议
        suggestions_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "mqqwpa://im/chat?chat_type=wpa&uin=1427703250";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));//指定的QQ号只需要修改uin后的值即可。
            }
        });

        Button payment_button = (Button) findViewById(R.id.payment);  //奖励作者
        payment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://ds.alipay.com/?from=mobilecodec&scheme=alipays%3A%2F%2Fplatformapi%2Fstartapp%3FsaId%3D10000007%26clientVersion%3D3.7.0.0718%26qrcode%3Dhttps%253A%252F%252Fqr.alipay.com%252F00c09578wjcsxdblqzuzk66%253F_s%253Dweb-other";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));//支付宝店铺码。

            }
        });

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
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        Button love_button = (Button) findViewById(R.id.love);  //表白墙
        love_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));//支付宝店铺码。

            }
        });

 //       Button my_button = (Button) findViewById(R.id.my);  //我的
  //      my_button.setOnClickListener(new View.OnClickListener() {
   //         @Override
    //        public void onClick(View view) {
    //            String url = "";
     //           startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));//支付宝店铺码。
//
     //       }
     //   });


    }

    public void initView() {
        banner = (Banner) findViewById(R.id.banner);  //定位

        list_path = new ArrayList<>(); //放图片地址的集合

        list_title = new ArrayList<>(); //放标题的集合

        list_path.add(R.drawable.welcome1);   //网页要加 “”  本地不用加“”
        list_path.add(R.drawable.welcome2);
       /* list_path.add(R.drawable.o5);
        list_path.add(R.drawable.o5);*/

        list_title.add("云上理工");
        list_title.add("欢迎你");
        /*list_title.add("热爱劳动");
        list_title.add("不搞对象");*/

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置内置样式，共有六种可以点入方法内逐一体验使用。

        banner.setImageLoader(new MyLoader()); //设置图片加载器，图片加载器在下方

        banner.setImages(list_path); //设置图片网址或地址的集合

        banner.setBannerAnimation(Transformer.DepthPage); //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验

        banner.setBannerTitles(list_title); //设置轮播图的标题集合

        banner.setDelayTime(3000); //设置轮播间隔时间

        banner.setViewPagerIsScroll(true); //设置是否允许手动滑动轮播图（默认true）

        banner.isAutoPlay(true); //设置是否为自动轮播，默认是“是”。

        banner.setIndicatorGravity(BannerConfig.CENTER) //设置指示器的位置，小点点，左中右。
                .start(); //必须最后调用的方法，启动轮播图。

    }

    private class MyLoader extends ImageLoader { //自定义的图片加载器
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((Integer) path).into(imageView);   //网页 String 本地 Integer
        }
    }


   /*  @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            // 隐藏导航栏
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            // 全屏(隐藏状态栏)
                           | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            // 使用沉浸式必须加这个flag
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载menu菜单的布局文件menu.xml
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.scan:
//                Toast.makeText(this, "等待开发中····", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this,scan.class);
//                startActivity(intent);
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    goScan();
                }
                return true;

         /*    case R.id.action_delete:
               Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;*/
            default:
            return super.onOptionsItemSelected(item);
        }
    }


    //zing 扫码
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_CODE_SCAN = 0x0000;

    private void goScan(){
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent,REQUEST_CODE_SCAN);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScan();
                } else {
                    Toast.makeText(this, "你拒绝了权限申请，可能无法打开相机扫码哟！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    public static boolean isUrl(String str) {
        // 转换为小写
        str = str.toLowerCase();
        String domainRules = "com.cn|net.cn|org.cn|gov.cn|com.hk|com|net|org|int|edu|gov|mil|arpa|Asia|biz|info|name|pro|coop|aero|museum|ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cf|cg|ch|ci|ck|cl|cm|cn|co|cq|cr|cu|cv|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|eh|es|et|ev|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gh|gi|gl|gm|gn|gp|gr|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|in|io|iq|ir|is|it|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|ml|mm|mn|mo|mp|mq|mr|ms|mt|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nt|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|pt|pw|py|qa|re|ro|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|st|su|sy|sz|tc|td|tf|tg|th|tj|tk|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|va|vc|ve|vg|vn|vu|wf|ws|ye|yu|za|zm|zr|zw";
        String regex = "^((https|http|ftp|rtsp|mms)?://)" + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
                + "|" // 允许IP和DOMAIN（域名）
                + "(([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]+\\.)?" // 域名- www.
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
                + "(" + domainRules + "))" // first level domain- .com or
                // .museum
                + "(:[0-9]{1,4})?" // 端口- :80
                + "((/?)|" // a slash isn't required if there is no file name
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher isUrl = pattern.matcher(str);
        return isUrl.matches();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                //返回的文本内容
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                //返回的BitMap图像
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);

                if (isUrl(data.getStringExtra(DECODED_CONTENT_KEY))){
                    Intent intent = new Intent(MainActivity.this, WebViews.class);
                    String ur = data.getStringExtra(DECODED_CONTENT_KEY);  //传入的网址
                    Bundle bundle = new Bundle();
                    bundle.putString("ur", ur);  //内容，名字
                    intent.putExtra("url", bundle);  //总体名字，BUNDLE名字
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, data.getStringExtra(DECODED_CONTENT_KEY), Toast.LENGTH_SHORT).show();
                }

                //Toast.makeText(this, data.getStringExtra(DECODED_CONTENT_KEY), Toast.LENGTH_SHORT).show();
             //   tv_scanResult.setText("你扫描到的内容是：" + content);
            }
            else {
                Toast.makeText(this, "内容为空！", Toast.LENGTH_SHORT).show();
            }
        }
    }



}