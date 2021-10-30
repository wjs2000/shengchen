package com.swufestu.shengchen;

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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    TextView show;
    EditText year;
    Handler handler;//将定义放在外面，因为要用到

    //
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getSharedPreferences("mybir", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();//创建一个编辑器
        Button button=(Button)findViewById(R.id.btn_count);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SAVEActivity.class);
                EditText year5 =(EditText) findViewById(R.id.textView03);
                System.out.println("year5"+year5);
                String year=year5.getText().toString();
                System.out.println("year"+year);
                EditText month5 =(EditText) findViewById(R.id.textView05);
                String month=month5.getText().toString();
                EditText day5 =(EditText) findViewById(R.id.textView07);
                String day=day5.getText().toString();
              //  intent.putExtra("year",year);
              //  intent.putExtra("month",month);
              //  intent.putExtra("day",day);

//                startActivityForResult(intent,1);
                editor.clear();
                editor.putString("year",year);//存储三个更新后的汇率文件
                editor.putString("month", month);
                editor.putString("day", day);
                editor.apply();//记得加apply完成提交！
                startActivity(intent);
            }
        });
    }





    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


}