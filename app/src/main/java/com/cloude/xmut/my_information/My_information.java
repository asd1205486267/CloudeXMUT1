package com.cloude.xmut.my_information;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.cloude.xmut.MainActivity;
import com.cloude.xmut.R;

import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.cloude.xmut.change_person_information.ensure_address;
import com.cloude.xmut.change_person_information.ensure_age;
import com.cloude.xmut.change_person_information.ensure_name;
import com.cloude.xmut.change_person_information.ensure_self_sign;
import com.cloude.xmut.change_person_information.ensure_sex;
import com.cloude.xmut.httpClient.LoginActivity;
import com.cloude.xmut.love.MomentListActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class My_information extends MainActivity {

    public  String path0=null;
    public  String pathm=null;
    public  Uri uri1=null;
    public  Uri uri2=null;
    public  Uri imageUri=null;
    public  Uri uritempFile=null;
    public  Bitmap cropBitmap=null;
    public  Uri imageCropUri=null;
    public Bitmap bitmap1=null;
    public Bitmap bitmap2=null;
    private int photo_0=0;
    private int photo_1=1;
    private int photo_2=2;
    private int change_name=0;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_infor);
        my_information_button();
        photo_select_button();
       delete_strict_model();

        ensure_namex();
        ensure_sexx();
        ensure_agex();
        ensure_addressx();
        ensure_self_signx();
        head_image();
    }


    private void ensure_namex(){
        TextView nameTV=(TextView)findViewById(R.id.name1);
        SharedPreferences sp=getSharedPreferences("username",MODE_PRIVATE);
        String name=sp.getString("name","xx");
        nameTV.setText(name);

    }

    private void ensure_sexx(){
        TextView sexTV=(TextView)findViewById(R.id.sex1);
        SharedPreferences sp=getSharedPreferences("user_sex",MODE_PRIVATE);
        String sex=sp.getString("sex","xx");
        sexTV.setText(sex);

    }

    private void ensure_agex(){
        TextView ageTV=(TextView)findViewById(R.id.age1);
        SharedPreferences sp=getSharedPreferences("user_age",MODE_PRIVATE);
        String age=sp.getString("age","xx");
        ageTV.setText(age);

    }
    private void ensure_addressx(){
        TextView addressTV=(TextView)findViewById(R.id.address1);
        SharedPreferences sp=getSharedPreferences("user_address",MODE_PRIVATE);
        String address=sp.getString("address","xx xx");
        addressTV.setText(address);

    }
    private void ensure_self_signx(){
        TextView self_signTV=(TextView)findViewById(R.id.self_signal);
        SharedPreferences sp=getSharedPreferences("user_self_sign",MODE_PRIVATE);
        String self_sign=sp.getString("self_sign","点击左侧标签修改个人信息~");
        self_signTV.setText(self_sign);

    }

    private void head_image(){
        RoundImageView roundImageView = (RoundImageView)findViewById(R.id.roundImageView);
            String path = Environment.getExternalStorageDirectory() + "/w65/icon_bitmap/" + "myicon.jpg";
            if (path != null) {
                roundImageView.setImageBitmap(getDiskBitmap(path));
            }

    }


    private void delete_strict_model(){
        //取消严格模式  FileProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy( builder.build() );
        }

    }




    private void photo_select_button(){
        //获取组件
        RoundImageView roundImageView = (RoundImageView)findViewById(R.id.roundImageView);
        //绑定监听
        roundImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //PopupWindow----START-----这里开始到下面标记的地方是实现点击头像弹出PopupWindow，实现用户从PopupWindow中选择更换头像的方式

                if (ContextCompat.checkSelfPermission(My_information.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(My_information.this, new String[]{Manifest.permission.CAMERA}, 5);
                }



                    backgroundAlpha(0.3f);
                View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.popu_view, null);
                final PopupWindow popupWindow = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
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
                        Button button = (Button) view.findViewById(R.id.take_photo);//通过拍照的方式
                        Button button1 = (Button) view.findViewById(R.id.all_photo);//通过相册的方式
                        Button button2 = (Button) view.findViewById(R.id.out);//取消按钮
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
                                if (ContextCompat.checkSelfPermission(My_information.this, Manifest.permission.WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(My_information.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 5);
                                }
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
                                File file = FileUitlity.getInstance(getApplicationContext()).makeDir("head_image");
                                //定义图片路径和名称
                                path0 = file.getParent() + "/head_image/" + System.currentTimeMillis() + ".jpg";
                                //图片质量
                                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                                //保存图片到Intent中，并通过Intent将照片传给系统裁剪器
                                uri1 = Uri.fromFile(new File(path0));
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri1);
                                //启动有返回的Intent，即返回裁剪好的图片到RoundImageView组件中显示
                                startActivityForResult(intent, photo_1);
                            }
                        });
            }
        });

    }


    private void my_information_button(){

        //   Button set_button = (Button) findViewById(R.id.set);  //设置
        //  set_button.setOnClickListener(new View.OnClickListener() {
        //      @Override
        //     public void onClick(View view) {
        //         String url = "";
        //        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

        //   }
        // });

        Button my_home_button = (Button) findViewById(R.id.my_home);  //主页
        my_home_button.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(My_information.this, MomentListActivity.class);
                startActivity(intent);
            }
        });

        Button my_button = (Button) findViewById(R.id.my);  //我的
        my_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(My_information.this, My_information.class);
               startActivity(intent);
            }
        });



        Button change_name_button = (Button) findViewById(R.id.change_name);  //修改昵称
        change_name_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(My_information.this, ensure_name.class);

                startActivityForResult(intent,change_name );
            }
        });


        Button change_sex_button = (Button) findViewById(R.id.change_sex);  //修改性别
        change_sex_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(My_information.this, ensure_sex.class);

                startActivity(intent);
            }
        });


        Button change_age_button = (Button) findViewById(R.id.change_age);  //修改年龄
        change_age_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(My_information.this, ensure_age.class);

                startActivity(intent);
            }
        });

        Button change_address_button = (Button) findViewById(R.id.change_address);  //修改地区
        change_address_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(My_information.this, ensure_address.class);

                startActivity(intent);
            }
        });

        Button change_self_sign_button = (Button) findViewById(R.id.change_self_sign);  //修改个性签名
        change_self_sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(My_information.this, ensure_self_sign.class);

                startActivity(intent);
            }
        });
        Button exit_information_button = (Button) findViewById(R.id.exit_information);  //退出登录
        exit_information_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(My_information.this, LoginActivity.class);

                startActivity(intent);
            }
        });

        Button change_head_image_button = (Button) findViewById(R.id.head_button);  //修改头像
        change_head_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(My_information.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(My_information.this, new String[]{Manifest.permission.CAMERA}, 5);
                }


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

                        if (ContextCompat.checkSelfPermission(My_information.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(My_information.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 6);
                        }

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
                        path0 = file.getParent() + "/head_image/" + System.currentTimeMillis() + ".jpg";
                        //图片质量
                        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                        //保存图片到Intent中，并通过Intent将照片传给系统裁剪器
                        uri1=Uri.fromFile(new File(path0));
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri1);
                        //启动有返回的Intent，即返回裁剪好的图片到RoundImageView组件中显示
                        startActivityForResult(intent,photo_1 );
                    }
                });
            }
        });


    }


    //调用手机相册
    private void allPhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,photo_0);
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
        intent.putExtra("outputX",400);
        intent.putExtra("outputY",400);
        //返回数据

        /*
        String pathBase = "file://" + Environment.getExternalStorageDirectory().getPath() + "/file";
        File file1 = new File(pathBase);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        String path = pathBase + "/crop" + ".jpg";
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
       imageCropUri = Uri.parse(path);

         */
        //这里生成图片到本地，由于小米手机调用裁剪无效，且用intent传不了值，只能用这种方法先保存到本地再调用本地上传
        //这个参数的意思就是输出文件到本地

        imageCropUri = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageCropUri);

        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", false);
        //return-data这个参数只能传小内存的数据，小米会屏蔽掉
        //intent.putExtra("return-data", true);

        startActivityForResult(intent, photo_2);


     //   intent.putExtra("outputFormat",Bitmap.CompressFormat.PNG.toString());
     //   intent.putExtra("noFaceDetection",true);


       // uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
    //    intent.putExtra(MediaStore.EXTRA_OUTPUT,uritempFile);

        //      intent.putExtra("return-data",true);
    //    startActivityForResult(intent,photo_2);
    }




    //该方法实现通过何种方式跟换图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        //获取组件
        RoundImageView roundImageView = (RoundImageView) findViewById(R.id.roundImageView);

        /*
        try{
            cropBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritempFile));
        }catch(FileNotFoundException e){
            e.printStackTrace();;
        }

         */

        //如果返回码不为-1，则表示不成功
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == photo_0) {
            //调用相册


/*
            try{
                imageUri = data.getData();
                Log.e("imageUri:",imageUri+"");
                String selectPhoto = getRealPathFromUri(this,imageUri);
                Log.e("selectPhoto:",selectPhoto);
         //       setImageCompressBitmap(selectPhoto);
            }catch (Exception e){
                e.printStackTrace();
            }
            startPhoneZoom(imageUri);
*/


            Cursor cursor = this.getContentResolver().query(data.getData(),
                   new String[]{MediaStore.Images.Media.DATA}, null, null, null);
            //游标移到第一位，即从第一位开始读取
            cursor.moveToFirst();
           String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            cursor.close();
            //调用系统裁剪
            uri2=Uri.fromFile(new File(path));
            startPhoneZoom(uri2);


        } else if (requestCode == photo_1) {
            //相机返回结果，调用系统裁剪
            startPhoneZoom(uri1);
        } else if (requestCode == photo_2) {



            Bitmap bp = getBitmap();
            try {
                saveFile(bp);
            } catch (IOException e) {
                e.printStackTrace();
            }

            roundImageView.setImageBitmap(bp);
        /*
            //设置裁剪返回的位图
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap bitmap = bundle.getParcelable("data");
                //将裁剪后得到的位图在组件中显示
            //    saveMyBitmap(f, bitmap);
                roundImageView.setImageBitmap(bitmap);
            }
*/
        }


        super.onActivityResult(requestCode, resultCode, data);

    }

    // 将uri转换为bitmap
    public Bitmap getBitmap() {
        try {
            bitmap1 = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageCropUri));
            bitmap1= ThumbnailUtils.extractThumbnail(bitmap1, 300, 300);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return bitmap1;
    }






    /**
     * 保存文件
     */
    public File saveFile(Bitmap bm) throws IOException {
        String path = Environment.getExternalStorageDirectory().toString()+"/w65/icon_bitmap/";
        File dirFile = new File(path);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        File myIconFile= new File(path + "myicon.jpg");
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myIconFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myIconFile;
    }

    /**
     * 从本地获取图片
     * @param pathString 文件路径
     * @return 图片
     */
    public Bitmap getDiskBitmap(String pathString)
    {
        Bitmap bitmap = null;
        try
        {
            File file = new File(pathString);
            if(file.exists())
            {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e)
        {
            // TODO: handle exception
        }
        return bitmap;
    }





}




