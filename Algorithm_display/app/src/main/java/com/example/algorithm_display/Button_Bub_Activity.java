package com.example.algorithm_display;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class Button_Bub_Activity extends AppCompatActivity {
    private BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button__bub_);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initBarChart();
    }
    private void initBarChart(){
        barChart = findViewById(R.id.BubSort_Chart);
        barChart.getDescription().setEnabled(false); // 不显示描述
        barChart.setExtraOffsets(20, 20, 20, 20); // 设置饼图的偏移量，类似于内边距 ，设置视图窗口大小
        setAxis(); // 设置坐标轴
        setLegend(); // 设置图例
        setData();  // 设置数据
    }
    private void setData(){
        List<IBarDataSet> sets = new ArrayList<>();
        // 此处有两个DataSet，所以有两条柱子，BarEntry（）中的x和y分别表示显示的位置和高度
        // x是横坐标，表示位置，y是纵坐标，表示高度
        List<BarEntry> barEntries1 = new ArrayList<>();
        barEntries1.add(new BarEntry(0, 390f));
        barEntries1.add(new BarEntry(1, 1100f));
        barEntries1.add(new BarEntry(2, 900f));
        barEntries1.add(new BarEntry(3, 700f));
        barEntries1.add(new BarEntry(4, 300f));
        BarDataSet barDataSet1 = new BarDataSet(barEntries1, "");
        barDataSet1.setValueTextColor(Color.RED); // 值的颜色
        barDataSet1.setValueTextSize(15f); // 值的大小
        barDataSet1.setColor(Color.parseColor("#1AE61A")); // 柱子的颜色
        barDataSet1.setLabel("蔬菜"); // 设置标签之后，图例的内容默认会以设置的标签显示
        // 设置柱子上数据显示的格式
        barDataSet1.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return value + "斤";
            }

        });

        sets.add(barDataSet1);

        List<BarEntry> barEntries2 = new ArrayList<>();
        barEntries2.add(new BarEntry(0, 210f));
        barEntries2.add(new BarEntry(1, 450f));
        barEntries2.add(new BarEntry(2, 430f));
        barEntries2.add(new BarEntry(3, 440f));
        barEntries2.add(new BarEntry(4, 180f));
        BarDataSet barDataSet2 = new BarDataSet(barEntries2, "");
        // 不显示第二根柱子上的值
        barDataSet2.setDrawValues(false); // 不显示值
        barDataSet2.setColor(Color.parseColor("#F7F709"));
        barDataSet2.setLabel("水果");
        sets.add(barDataSet2);

        BarData barData = new BarData(sets);
        barData.setBarWidth(0.4f); // 设置柱子的宽度
        barChart.setData(barData);
    }
    private void setLegend() {
        Legend legend = barChart.getLegend();
        legend.setFormSize(12f); // 图例的图形大小
        legend.setTextSize(15f); // 图例的文字大小
        legend.setDrawInside(true); // 设置图例在图中
        legend.setOrientation(Legend.LegendOrientation.VERTICAL); // 图例的方向为垂直
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); //显示位置，水平右对齐
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); // 显示位置，垂直上对齐
        // 设置水平与垂直方向的偏移量
        legend.setYOffset(55f);
        legend.setXOffset(30f);
    }

    private void setAxis() {
        // 设置x轴
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  // 设置x轴显示在下方，默认在上方
        xAxis.setDrawGridLines(false); // 将此设置为true，绘制该轴的网格线。
        xAxis.setLabelCount(5);  // 设置x轴上的标签个数
        xAxis.setTextSize(15f); // x轴上标签的大小
        final String labelName[] = {"周一", "周二", "周三", "周四", "周五"};
        // 设置x轴显示的值的格式
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if ((int) value < labelName.length) {
                    return labelName[(int) value];
                } else {
                    return "";
                }
            }
        });
        xAxis.setYOffset(15); // 设置标签对x轴的偏移量，垂直方向

        // 设置y轴，y轴有两条，分别为左和右
        YAxis yAxis_right = barChart.getAxisRight();
        yAxis_right.setAxisMaximum(1200f);  // 设置y轴的最大值
        yAxis_right.setAxisMinimum(0f);  // 设置y轴的最小值
        yAxis_right.setEnabled(false);  // 不显示右边的y轴

        YAxis yAxis_left = barChart.getAxisLeft();
        yAxis_left.setAxisMaximum(1200f);
        yAxis_left.setAxisMinimum(0f);
        yAxis_left.setTextSize(15f); // 设置y轴的标签大小
    }


}
