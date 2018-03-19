package com.spannerstringdemo;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private LinearLayout activityMain;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv;
    private TextView tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }


    private void initView() {
        activityMain = (LinearLayout) findViewById(R.id.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv = (TextView) findViewById(R.id.tv);
        tv5 = (TextView) findViewById(R.id.tv5);
    }


    private void initData() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder("会挽雕弓如满月");
        //start 下标从0开始 end
//        spannableString.setSpan(new UnderlineSpan(),0,7,Spanned.SPAN_EXCLUSIVE_INCLUSIVE);//前面不包括，后面包括
//        spannableString.setSpan(new UnderlineSpan(),0,7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//前后都不包括
        spannableString.setSpan(new UnderlineSpan(), 0, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//包含前面，不包含后面
//        spannableString.setSpan(new UnderlineSpan(),0,7,Spanned.SPAN_INCLUSIVE_INCLUSIVE);//前后都包括
        spannableString.insert(0, "诗词歌赋-");
        spannableString.insert(spannableString.length(), "，西北望，射天狼。");//后面插入
        tv.setText(spannableString);


        SpannableString sp1 = new SpannableString("花间一壶酒，独酌无相亲");
        //设置文本背景色
        sp1.setSpan(new BackgroundColorSpan(Color.parseColor("#ff0000")), 0, sp1.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        //设置字体颜色
        sp1.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffff")), 0, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        sp1.setSpan(new ForegroundColorSpan(Color.parseColor("#00ff00")), 6, sp1.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv1.setText(sp1);
        tv1.setMovementMethod(LinkMovementMethod.getInstance());


        SpannableString sp2 = new SpannableString("举杯邀明月，对影成三人");
        //设置字体大小
        sp2.setSpan(new StyleSpan(Typeface.BOLD), 0, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);//粗体
        sp2.setSpan(new StyleSpan(Typeface.ITALIC), 6, sp2.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);//斜体
        sp2.setSpan(new UnderlineSpan(), 0, sp2.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);//下划线
        sp2.setSpan(new AbsoluteSizeSpan(18), 0, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);//绝对大小
        sp2.setSpan(new RelativeSizeSpan(2), 0, sp2.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);//android默认字体大小的倍数

        //设置文本字体
        sp2.setSpan(new TypefaceSpan("sans-serif"), 0, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        sp2.setSpan(new TypefaceSpan("serif"), 6, sp2.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tv2.setText(sp2);


        //设置部分文本点击事件
        SpannableString sp3 = new SpannableString("君不见黄河之水天上来，奔流到海不复回");
        sp3.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "君不见黄河之水天上来", Toast.LENGTH_LONG).show();
            }
        }, 0, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);


        sp3.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "奔流到海不复回", Toast.LENGTH_LONG).show();
            }
        }, 11, sp3.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        sp3.setSpan(new BackgroundColorSpan(Color.parseColor("#ee0000")), 0, 10, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        sp3.setSpan(new BackgroundColorSpan(Color.parseColor("#0000ff")), 11, sp3.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tv3.setText(sp3);
        tv3.setMovementMethod(LinkMovementMethod.getInstance());//必须加


        //textview设置超链接
        SpannableString sp4 = new SpannableString("百度,打电话,发短信");
        sp4.setSpan(new URLSpan("https://www.baidu.com"), 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE); //打开网页
        sp4.setSpan(new URLSpan("tel:18337104423"), 3, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);//打电话
        sp4.setSpan(new URLSpan("smsto:18337104423"), 7, sp4.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);//发短信
        tv4.setText(sp4);
        tv4.setMovementMethod(LinkMovementMethod.getInstance());

        //imageSpan
        SpannableStringBuilder sp5=new SpannableStringBuilder("关关雎鸠，在河之洲洲");
        Drawable drawable = getResources().getDrawable(R.mipmap.image);
        drawable.setBounds(0,0,100,100);
        sp5.setSpan(new ImageSpan(drawable),sp5.length()-1,sp5.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv5.setText(sp5);
    }
}
