package com.swufestu.shengchen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SAVEActivity2 extends AppCompatActivity {


    private  TextView tvtextView81;
    private Timer textView81;

    private static final String TAG = "SAVEActivity2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sp = getSharedPreferences("mybir", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();//创建一个编辑器
        System.out.println("进入SAVEActivity2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save2);

        textView81 = new Timer();
        tvtextView81= findViewById(R.id.textView81);
        System.out.println("第二页第二页第二页第二页第二页第二页第二页");
        Intent intent2=getIntent();//获取用于启动第二个界面的intent

        Long bir = sp.getLong("bir",5555);
        Long yearget = sp.getLong("yearget",6666);
        String deadyear1 = sp.getString("deadyear","deadyearnull");
      //  Long bir=intent2.getLongExtra("bir1",0);//将year键值信息存到year里面
     //   Long yearget = intent2.getLongExtra("yearget1",0);
       // String deadyear1=intent2.getStringExtra("deadyear1");
        Long deadyear2 = Long.parseLong(deadyear1);
        System.out.println("bibbbbbbbbbbbbbbbbbbbbbbbb"+bir);
        System.out.println("bibbbbbbbbbbbbbbbbbbbbbbbb"+yearget);
        System.out.println("bibbbbbbbbbbbbbbbbbbbbbbbb"+deadyear2);


        Button button1=(Button)findViewById(R.id.button5);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            System.out.println("开始从SAVEActivity2转到SAVEActivity!!!!!!!!!!!!!!!!!!!!!!!!!");
                Intent intent4=new Intent(SAVEActivity2.this,SAVEActivity.class);
             //   setResult(RESULT_OK,intent4);
               // finish();
                startActivity(intent4);
            }
        });


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("sdf="+sdf);

        Date date=new Date();//获取当前时间
        System.out.println("date"+date);
        Long now = date.getTime();//获取1970到当前时间毫秒
        System.out.println("now="+now);

        Double deadold =(double)((deadyear2*yearget-(now-bir))/yearget);
        Double clock = (double)((double)((now-bir))/(double) ((deadyear2*yearget))*24*60);
        Double hour = clock/60;
        Double minute = clock%60;

        System.out.println("deadold="+deadold);
        System.out.println("clock="+clock);
        System.out.println("hour="+hour);
        System.out.println("minute="+minute);



        Double eat = deadold*365*3;
        Double week = deadold*52.14;
        Double vacation = week/25.88;

        System.out.println("eat="+eat);
        System.out.println("week="+week);
        System.out.println("vacation="+vacation);


        DecimalFormat df   = new DecimalFormat("######0.0");
        DecimalFormat df1   = new DecimalFormat("######0");
        String show = df1.format(hour)+"点"+df1.format(minute)+"分";
        String eat1 = "吃"+df1.format(eat)+"顿饭";
        String week1 = "度过"+df1.format(week)+"次周末";
        String vacation1 = "享受"+df1.format(vacation)+"个长假";


        //获取第二页想要输出信息的位置
        TextView textView31=(TextView) findViewById(R.id.textView31);
        TextView textView51=(TextView) findViewById(R.id.textView51);
        TextView textView61=(TextView) findViewById(R.id.textView61);
        TextView textView71=(TextView) findViewById(R.id.textView71);
     //   TextView textView81=(TextView) findViewById(R.id.textView81);


        //Intent intent =getIntent();//获取用于启动第二个界面的intent
        // String oldname=intent.getStringExtra("old");//将name键值信息存到tv里面
        // textView3.setText(df.format(old));//输出信息
        textView31.setText(show);
        textView51.setText(eat1);
        textView61.setText(week1);
        textView71.setText(vacation1);
    //    textView81.setText(deadold1);


        textView81.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.v("TextView81()","run()...");
                Message message=new Message();
                message.what=0;
                message.obj=deadold*365;
                mHandler1.sendMessage(message);

            }
        },0,1000);


    }

    Handler mHandler1=new Handler(){

        @Override
        public void handleMessage(Message msg1) {
            Log.v("Timer","handleMessage()..");
            super.handleMessage(msg1);
            DecimalFormat df   = new DecimalFormat("######0.0");
            String str="在接下来的"+(String)df.format(msg1.obj)+"个日子里，你大约还可以";
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbstr="+str);
            tvtextView81.setText(str);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//创建菜单文件
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;//真或者假按照这个来看是否显示
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings2) {

            Intent intent = new Intent(this, SAVEActivity3.class);
            SharedPreferences sp = getSharedPreferences("mybir", Activity.MODE_PRIVATE);
            SharedPreferences .Editor editor = sp.edit(); 		//获取编辑器
            editor.remove("deadyear");								//删除所有数据
            startActivity(intent);//回到原来窗口这里有一个code
            //noinspection SimplifiableIfStatement



        }
        return super.onOptionsItemSelected(item);
    }


}