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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.swufestu.shengchen.databinding.ActivitySaveBinding;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
 import android.content.Context;
 import android.content.SharedPreferences;

public class SAVEActivity extends AppCompatActivity {
    private TextView tvTime;
    private Timer timer;
    //    private Handler mHandler;
    private Calendar mCalendar;

    private TextView tvtextView3;
    private Timer textView3;
    Handler handler;//将定义放在外面，因为要用到
    private static final String TAG = "SAVEActivity";
    TextView year2, month2, day2, time2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences("mybir", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();//创建一个编辑器
        System.out.println("进入SAVEActivity!!!!!!!!!!!!!!!!!!!!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        textView3 = new Timer();
        tvtextView3 = findViewById(R.id.textView3);
        timer = new Timer();//创建timer对象
        tvTime = findViewById(R.id.tvTime);
        timer.schedule(new TimerTask() {
            @Override

            public void run() {
                Log.v("Timer", "run()...");
                mCalendar = Calendar.getInstance();
                int hour = mCalendar.get(Calendar.HOUR_OF_DAY);//HOUR    进制为12小时   HOUR_OF_DAY  为24小时
                int minute = mCalendar.get(Calendar.MINUTE);//分钟
                int second = mCalendar.get(Calendar.SECOND) + 1;//秒数
                if (second == 60) {
                    minute += 1;
                    second = 0;
                }
                if (minute == 60) {
                    hour += 1;
                    minute = 0;
                }
                if (hour == 12) {
                    hour = 0;
                }
                String time = String.format("%d:%02d:%02d", hour, minute, second);
                mCalendar.set(Calendar.SECOND, second);
                mCalendar.set(Calendar.MINUTE, minute);
                mCalendar.set(Calendar.HOUR_OF_DAY, hour);

                Message message = new Message();
                message.what = 0;
                message.obj = time;
                mHandler.sendMessage(message);

            }
        }, 0, 1000);

        // Intent intent3 = getIntent();
        //   Long deadyear = intent3.getLongExtra("deadyear2",0);
        //  System.out.println("ddddddddddddddddddddddddddddddddddddddd"+deadyear);
        Intent intent = getIntent();//获取用于启动第二个界面的intent


        String year = sp.getString("year", "wjs1");                    //获取用户名
        String month = sp.getString("month", "wjs2");
        String day = sp.getString("day", "wjs3");

        //  String year5=intent.getStringExtra("year");//将year键值信息存到year里面
        //   System.out.println(year);
        //   String month=intent.getStringExtra("month");
        //   String day=intent.getStringExtra("day");


        // int numyear = Integer.parseInt(year);
        // int nummonth = Integer.parseInt(month);
        // int numday = Integer.parseInt(day);
        // int numtextView =  Integer.parseInt(textView.toString());;
        String birthday = year + "-" + month + "-" + day + " " + "00:00:00";//生日转化为标准日期
        System.out.println("birthday" + birthday);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("sdf=" + sdf);
        Date numbirthday = null;
        System.out.println("初始numbirthday=" + sdf);
        try {
            numbirthday = sdf.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("numbirthday=" + numbirthday);

        Date date = new Date();//获取当前时间
        System.out.println("date" + date);
        Long now = date.getTime();//获取1970到当前时间毫秒
        System.out.println("now=" + now);
        Long bir = numbirthday.getTime();//获取1970到时间毫秒
        System.out.println("bir=" + bir);
        Long yearget = 31536000000L;

        editor.putLong("bir", bir);
        editor.putLong("yearget", yearget);
        editor.apply();//记得加apply完成提交！

        System.out.println("yearget=" + yearget);
        Float old = (float) (now - bir) / yearget;//当前年龄（textView3）
        System.out.println("old=" + old);

        Float oldyear = old;//当前年龄-年（textView14）
        Float oldmonth = old * 12;//当前年龄-月（textView7）
        Double oldweek = old * 52.14;//当前年龄-周（textView8）
        double oldday = old * 365.25;//当前年龄-天（textView9）
        double oldhour = old * 365.25 * 24;//当前年龄-小时（textView10）
        double oldminute = old * 365.25 * 24 * 60;//当前年龄-分钟（textView11）


        DecimalFormat df = new DecimalFormat("######0.0000000000000000000000");
        DecimalFormat df1 = new DecimalFormat("######0");


        //获取第二页想要输出信息的位置
        TextView textView14 = (TextView) findViewById(R.id.textView14);
        TextView textView7 = (TextView) findViewById(R.id.textView7);
        TextView textView8 = (TextView) findViewById(R.id.textView8);
        TextView textView9 = (TextView) findViewById(R.id.textView9);
        TextView textView10 = (TextView) findViewById(R.id.textView10);
        TextView textView11 = (TextView) findViewById(R.id.textView11);
        //Intent intent =getIntent();//获取用于启动第二个界面的intent
        // String oldname=intent.getStringExtra("old");//将name键值信息存到tv里面
        // textView3.setText(df.format(old));//输出信息
        textView14.setText(df1.format(oldyear));
        textView7.setText(df1.format(oldmonth));
        textView8.setText(df1.format(oldweek));
        textView9.setText(df1.format(oldday));
        textView10.setText(df1.format(oldhour));
        textView11.setText(df1.format(oldminute));


        textView3.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.v("TextView3()", "run()...");
                String birthday = year + "-" + month + "-" + day + " " + "00:00:00";//生日转化为标准日期
                System.out.println("birthday" + birthday);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("sdf=" + sdf);
                Date numbirthday = null;
                System.out.println("初始numbirthday=" + sdf);
                try {
                    numbirthday = sdf.parse(birthday);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println("numbirthday=" + numbirthday);

                Date date = new Date();//获取当前时间
                System.out.println("date" + date);
                Long now = date.getTime();//获取1970到当前时间毫秒
                System.out.println("now=" + now);
                Long bir = numbirthday.getTime();//获取1970到时间毫秒
                System.out.println("bir=" + bir);
                Long yearget = 31536000000L;
                System.out.println("yearget=" + yearget);
                Double old = (double) (now - bir) / yearget;//当前年龄（textView3）
                System.out.println("old=" + old);
                Message message = new Message();
                message.what = 0;
                message.obj = old;
                mHandler1.sendMessage(message);

            }
        }, 0, 1000);

        Button button1 = (Button) findViewById(R.id.dead);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getString("deadyear", "null") != "null") {
                    Intent intent5 = new Intent(SAVEActivity.this, SAVEActivity2.class);
                    System.out.println("已有年龄!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    startActivity(intent5);
                } else {
                    System.out.println("开始传输到SAVEActivity3!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    Intent intent1 = new Intent(SAVEActivity.this, SAVEActivity3.class);
                    startActivity(intent1);
                }


            }


        });
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.v("Timer", "handleMessage()..");
            super.handleMessage(msg);
            String str = (String) msg.obj;
            tvTime.setText(str);
        }
    };
    Handler mHandler1 = new Handler() {

        @Override
        public void handleMessage(Message msg1) {
            Log.v("Timer", "handleMessage()..");
            super.handleMessage(msg1);
            DecimalFormat df = new DecimalFormat("######0.00000000");
            String str = (String) df.format(msg1.obj);

            tvtextView3.setText(str);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();//关闭timer
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//创建菜单文件
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;//真或者假按照这个来看是否显示
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {

            Intent intent = new Intent(this, MainActivity.class);
            SharedPreferences sp = getSharedPreferences("mybir", Activity.MODE_PRIVATE);
            SharedPreferences .Editor editor = sp.edit(); 		//获取编辑器
            editor.clear();								//删除所有数据
            startActivity(intent);//回到原来窗口这里有一个code
            //noinspection SimplifiableIfStatement



        }
        return super.onOptionsItemSelected(item);
    }
}
