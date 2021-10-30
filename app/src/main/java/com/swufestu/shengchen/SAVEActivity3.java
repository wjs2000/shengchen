package com.swufestu.shengchen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.swufestu.shengchen.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.content.Intent;
public class SAVEActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save3);
        Intent intent1 = getIntent();
     //   Long bir1 = intent1.getLongExtra("bir",0);
      //  Long yearget1 = intent1.getLongExtra("yearget",0);
       // String bir1 = intent1.getStringExtra("bir");//将bir键值信息存到bir里面
       // String yearget1 = intent1.getStringExtra("yearget");//将bir键值信息存到bir里面
     //   System.out.println("aaaaaaaaaaaaaaaaaa"+bir1);
     //   System.out.println("aaaaaaaaaaaaaaaaaa"+yearget1 );
        SharedPreferences sp = getSharedPreferences("mybir", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();//创建一个编辑器
        Button button33 = (Button) findViewById(R.id.button33);
        button33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SAVEActivity3.this, SAVEActivity2.class);
                System.out.println("第三页第三页第三页第三页第三页第三页第三页" );

                EditText deadyear = (EditText) findViewById(R.id.textView22);
                String deadyear1=deadyear.getText().toString();
                System.out.println("deadyear1" + deadyear1);
                editor.putString("deadyear",deadyear1);
                editor.apply();//记得加apply完成提交！
                startActivity(intent2);
//                startActivityForResult(intent,1);


            }
        });


    }

}