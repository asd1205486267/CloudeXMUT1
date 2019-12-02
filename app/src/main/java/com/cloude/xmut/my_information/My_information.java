package com.cloude.xmut.my_information;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.cloude.xmut.MainActivity;
import com.cloude.xmut.R;

import android.widget.PopupWindow;
import android.widget.TextView;

import com.cloude.xmut.httpClient.LoginActivity;
import com.cloude.xmut.httpClient.Post_to_login;

import java.io.File;

public class My_information extends MainActivity{

    public  static String path0=null;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_infor);


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
                String url = "";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));//支付宝店铺码。

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

        //获取组件
        RoundImageView roundImageView = (RoundImageView)findViewById(R.id.roundImageView);
        //绑定监听
        roundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PopupWindow----START-----这里开始到下面标记的地方是实现点击头像弹出PopupWindow，实现用户从PopupWindow中选择更换头像的方式
                
                backgroundAlpha(0.3f);
                View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.popu_view,null);
                final PopupWindow popupWindow = new PopupWindow(view, ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                //获取屏幕宽度
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                popupWindow.setWidth(dm.widthPixels);
                popupWindow.setAnimationStyle(R.style.AnimHorizontal);
                //显示位置
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                popupWindow.setOnDismissListener(new poponDismissListener());
                //PopupWindow-----END


                //PopupWindow中对应的选择按钮
                Button button = (Button)view.findViewById(R.id.take_photo);//通过拍照的方式
                Button button1 = (Button)view.findViewById(R.id.all_photo);//通过相册的方式
                Button button2 = (Button)view.findViewById(R.id.out);//取消按钮
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        backgroundAlpha(1f);
                        popupWindow.dismiss();
                    }
                });
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        backgroundAlpha(1f);
                        popupWindow.dismiss();
                        //调用手机相册的方法,该方法在下面有具体实现
                        allPhoto();
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        backgroundAlpha(1f);

                        popupWindow.dismiss();
                        //调用手机照相机的方法,通过Intent调用系统相机完成拍照，并调用系统裁剪器裁剪照片
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //创建文件路径,头像保存的路径
                        File file =FileUitlity.getInstance(getApplicationContext()).makeDir("head_image");
                        //定义图片路径和名称
                          path0 = file.getParent() + File.separatorChar + System.currentTimeMillis() + ".jpg";
                        //图片质量
                        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                        //保存图片到Intent中，并通过Intent将照片传给系统裁剪器
                        Uri uri=Uri.fromFile(new File(path0));
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        //启动有返回的Intent，即返回裁剪好的图片到RoundImageView组件中显示
                        startActivityForResult(intent,1 );
                    }
                });
            }
        });




    }


    //调用手机相册
    private void allPhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,0);
    }


    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }


    /**
     * 添加PopupWindow关闭的事件，主要是为了将背景透明度改回来
     *
     */
    class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }

    //调用系统裁剪的方法
    private void startPhoneZoom(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //是否可裁剪
        intent.putExtra("corp", "true");
        //裁剪器高宽比
        intent.putExtra("aspectY",1);
        intent.putExtra("aspectX",1);
        //设置裁剪框高宽
        intent.putExtra("outputX",150);
        intent.putExtra("outputY", 150);
        //返回数据

        intent.putExtra("return-data",true);
        startActivityForResult(intent,2);
    }



    //该方法实现通过何种方式跟换图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //获取组件
        RoundImageView roundImageView = (RoundImageView)findViewById(R.id.roundImageView);

        super.onActivityResult(requestCode, resultCode, data);

        //如果返回码不为-1，则表示不成功
        if (resultCode != Activity.RESULT_OK){
            return;
        }

            if (requestCode == 0) {
                //调用相册
                Cursor cursor = this.getContentResolver().query(data.getData(),
                        new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                //游标移到第一位，即从第一位开始读取
                cursor.moveToFirst();
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                cursor.close();
                //调用系统裁剪
                startPhoneZoom(Uri.fromFile(new File(path)));
            } else if (requestCode == 1) {
                //相机返回结果，调用系统裁剪
                startPhoneZoom(Uri.fromFile(new File(path0)));
            } else if (requestCode == 2) {
                //设置裁剪返回的位图
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    Bitmap bitmap = bundle.getParcelable("data");
                    //将裁剪后得到的位图在组件中显示
                    roundImageView.setImageBitmap(bitmap);
                }

            }

    }














}




