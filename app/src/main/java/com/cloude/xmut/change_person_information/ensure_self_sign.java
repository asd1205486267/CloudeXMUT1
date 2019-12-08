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


public class ensure_self_sign extends My_information {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_self_sign);

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
            NavUtils.navigateUpFromSameTask(ensure_self_sign.this);
        }
        else if(id==R.id.ensure1){
            ensure_self_sign_button();

        }

        return true;

    }
    public void ensure_self_sign_button() {

        final EditText self_signET=(EditText)findViewById(R.id.change_self_sign_t);
        String userself_sign1=self_signET.getText().toString();
            SharedPreferences sp = getSharedPreferences("user_self_sign", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("self_sign", userself_sign1);
            editor.commit();
            Intent intent = new Intent();
            intent.setClass(ensure_self_sign.this, My_information.class);
            startActivity(intent);

    }

}
