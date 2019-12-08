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
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.cloude.xmut.MainActivity;
import com.cloude.xmut.R;
import com.cloude.xmut.my_information.My_information;


public class ensure_name extends My_information {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_name);

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
            NavUtils.navigateUpFromSameTask(ensure_name.this);
        }
        else if(id==R.id.ensure1){
            ensure_name_button();

        }

        return true;

    }
    public void ensure_name_button() {

        final EditText nameET=(EditText)findViewById(R.id.change_name_t);
                String username1=nameET.getText().toString();
                if(!"".equals(username1)) {
                    SharedPreferences sp = getSharedPreferences("username", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("name", username1);
                    editor.commit();
                    Intent intent = new Intent();
                    intent.setClass(ensure_name.this, My_information.class);
                    startActivity(intent);
                }

                else{
                    Toast.makeText(ensure_name.this,"昵称不能为空",Toast.LENGTH_SHORT).show();
                }
    }






}
