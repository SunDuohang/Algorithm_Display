package com.example.algorithm_display;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Button_Sel_Activity extends AppCompatActivity {
    private BarChart SelSort_Chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button__sel_);

        SelSort_Chart = (BarChart) findViewById(R.id.SelSort_Chart);
        SelSort_Chart = initBarChart(SelSort_Chart);
        BarData barData = setBarData(); //这里调用方法初始化模拟数据
        SelSort_Chart.setData(barData); //这里将模拟数据用于柱状图
        SelSort_Chart.invalidate();     //这里让柱状图在数据填充后刷新
        //
    }

    //该方法定义一个柱状图数据；
    public BarData setBarData(){
        List<BarEntry> entries = new ArrayList<>();  //定义一个存放数据的容器
        //这里用for循环为容器写入数据
        //填充12个200以内的随机数
        entries.add(new BarEntry(0, new Random().nextInt(200)));
        entries.add(new BarEntry(1, new Random().nextInt(200)));
        entries.add(new BarEntry(2, new Random().nextInt(200)));
        entries.add(new BarEntry(3, new Random().nextInt(200)));
        entries.add(new BarEntry(4, new Random().nextInt(200)));
        entries.add(new BarEntry(5, new Random().nextInt(200)));
        BarDataSet barDataSet = new BarDataSet(entries,"测试用数据");
        BarData barData = new BarData(barDataSet);
        return barData;   //返回可用于柱状图的数据
    }

    //初始化柱状图；
    public BarChart initBarChart(BarChart barChart){
        barChart.setDescription(null);    //设置柱状图解释性文字为空
        barChart.setDrawBarShadow(false); //设置柱状图每条柱子的阴影不显示
        barChart.setDrawValueAboveBar(true); //设置柱状图每一条柱子的数值显示
        XAxis xAxis = barChart.getXAxis();   //获取柱状图的x轴
        YAxis yAxisLeft = barChart.getAxisLeft();//获取柱状图左侧的y轴
        YAxis yAxisRight = barChart.getAxisRight();
        setAxis(xAxis,yAxisLeft,yAxisRight);            //调用方法设置柱状图轴线
        return barChart;      //返回初始化完成的柱状图实例
    }

    //设置柱状图的x轴与y轴；
    public void setAxis(XAxis xAxis, YAxis yAxisLeft,YAxis yAxisRight){
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//x轴在柱状图底部显示
        xAxis.setAxisLineWidth(1);    //设置x轴宽度
        xAxis.setAxisMaximum(0);//设置x轴从0刻度开始绘画
        xAxis.setDrawAxisLine(true);//这里设置x轴显示轴线
        xAxis.setGranularity(1);
        xAxis.setDrawGridLines(false);//这里设置x轴的表格线不显示
        xAxis.setEnabled(true);//设置x轴显示

        yAxisLeft.setAxisLineWidth(1);
        yAxisLeft.setAxisMinimum(0);
        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setDrawAxisLine(true);
        yAxisLeft.setEnabled(true);

        yAxisRight.setAxisLineWidth(1);
        yAxisRight.setAxisMinimum(0);
        yAxisRight.setDrawGridLines(false);
        yAxisRight.setDrawAxisLine(true);
        yAxisRight.setEnabled(false);
    }
}