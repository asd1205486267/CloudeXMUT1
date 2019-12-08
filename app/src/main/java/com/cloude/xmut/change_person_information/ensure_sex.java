package com.cloude.xmut.change_person_information;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.cloude.xmut.MainActivity;
import com.cloude.xmut.R;
import com.cloude.xmut.my_information.My_information;


public class ensure_sex extends My_information {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_sex);

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
            NavUtils.navigateUpFromSameTask(ensure_sex.this);
        }
        else if(id==R.id.ensure1){
            ensure_sex_button();

        }

        return true;

    }






    public void ensure_sex_button() {
        final RadioGroup sex=(RadioGroup)findViewById(R.id.sex_group);

        for (int i=0;i<sex.getChildCount();i++)
        {
            RadioButton r=(RadioButton)sex.getChildAt(i);
            if(r.isChecked()){
                r.getText();
                String usersex1=r.getText().toString();
                SharedPreferences sp = getSharedPreferences("user_sex", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("sex", usersex1);
                editor.commit();
                Intent intent = new Intent();
                intent.setClass(ensure_sex.this, My_information.class);
                startActivity(intent);
                break;
            }
        }
    }

}
