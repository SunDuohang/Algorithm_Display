package com.example.algorithm_display;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class Button_Quick_Activity extends AppCompatActivity {
    private BarChart chart;
    private List<BarEntry> list = new ArrayList<>();
    private CountDownTimer count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button__quick_);
        chart = findViewById(R.id.QuickSort_Chart);
        info();
    }


    private void info() {
        XAxis xAxis = chart.getXAxis();
        //取消x轴网格线
        xAxis.setDrawGridLines(false);
        //设置x轴位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置x轴显示标签数
        xAxis.setLabelCount(25);
        //定义x轴最大值
        xAxis.setAxisMaximum(25f);
        //禁用图表右边Y轴
        chart.getAxisRight().setEnabled(false);
        //取消图表左边y轴网格线
        chart.getAxisLeft().setDrawGridLines(false);

        //x轴标签集合,i的大小与自定义的最大值关联。
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i ++) {
            list.add("" + i);
        }
        //自定义x轴标签
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.e("demo", String.valueOf(value));
                if (value > 19) {
                    return "";
                } else {
                    return list.get((int) value);
                }
            }
        });

        //取消图例显示
        chart.getLegend().setEnabled(false);

        //取消描述显示
        Description description = new Description();
        description.setEnabled(false);
        chart.setDescription(description);

        //开启定时器
        count = new CountDownTimer(Integer.MAX_VALUE, 500) {
            @Override
            public void onTick(long millisUntilFinished) {
                setData((int) (Math.random() * 80));
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void setData(int k) {
        //每次存储在下标为0的位置
        list.add(0, new BarEntry(0, k));
        //更改x轴的下标
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setX(i);
        }
        //条目大于20时，删除最后的条目
        if (list.size() > 20) {
            list.remove(20);
        }
        BarDataSet set = new BarDataSet(list, "");
        BarData data = new BarData(set);
        data.setBarWidth(0.6f);
        chart.setData(data);
        //刷新布局
        chart.invalidate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(count!=null){
            count.cancel();
            count=null;
        }
    }

}