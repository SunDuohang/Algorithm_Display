package com.example.algorithm_display;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.List;
//快速排序
public class Button_Quick_Activity extends AppCompatActivity {
    private Button btn_Quick_Ex;
    private BarChart Quick_BarChart;
    private  float array[] = new float[11];
    private BarChartManager barChartManager1 ;
    private ArrayList<Float> xValues = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private List<Integer> colors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button__quick_);
        Quick_BarChart = findViewById(R.id.QuickSort_Chart);//找到控件QuickSort_Chart

        barChartManager1 = new BarChartManager(Quick_BarChart);
        //设置x轴的数据
        ArrayList<String> xValues0 = new ArrayList<>();
        //设置x轴的数据
        xValues = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            xValues.add((float) i);
        }
        //设置y轴的数据()
        final List<List<Float>> yValues = new ArrayList<>();
        List<Float> yValue = new ArrayList<>();
        for (int i = 0; i < 11; i++){
            array[i] = ((float) (Math.random() * 8) + 2);
            yValue.add(array[i]);
        }
        yValues.add(yValue);
        //颜色集合

        colors.add(Color.CYAN);
        colors.add(Color.RED);

        //线的名字集合
        names.add("柱状一");
        names.add("柱状二");
        //创建多条柱状的图表
        barChartManager1.showBarChart(xValues, yValues.get(0), names.get(0), colors,0,array[0]);

        btn_Quick_Ex = (Button) findViewById(R.id.btn_Quick_Ex);
        btn_Quick_Ex.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if(isSorted(array))
                    Toast.makeText(Button_Quick_Activity.this, "排序完成", Toast.LENGTH_SHORT).show();
                else
                    quicksort(array, 0, array.length - 1);
                    /*yValues.clear();
                    List<Float> yValue = new ArrayList<>();
                    for(int j = 0; j < array.length; j++){
                        yValue.add(array[j]);
                    }
                    yValues.add(yValue);
                    barChartManager1.showBarChart(xValues, yValues.get(0), names.get(0), colors);*/
                }
        });
    }
    private int partition(float array[], int first, int last){
        int i = first;
        int j = last;
        float key = array[first];
        while(i < j){
            while(i < j && array[j] >= key)
                j --;
            if(i < j) {
                array[i] = array[j];
                i ++;
            }
            //交换一次值以后重新显示一次Chart

            while (array[i] < key && i < j)
                i ++;
            if(i < j) {
                array[j] = array[i];
                j --;
            }
            //交换一次值以后重新显示一次Chart

        }
        array [i] = key;
        return i;
    }
    private void quicksort(final float array[], final int first, final int last){
        final List<List<Float>> yValues = new ArrayList<>();
        final List<Float> yValue = new ArrayList<>();
        float key = array[first];

        if(first < last){
            final int part = partition(array ,first, last);
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(1500);//休眠1500毫秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /**
                     * 要执行的操作
                     */
                    quicksort(array,first,part-1);
                    for(int j = 0; j < 11; j++){
                        yValue.add(array[j]);
                    }
                    yValues.clear();
                    yValues.add(yValue);
                    barChartManager1.showBarChart(xValues, yValues.get(0), names.get(0), colors,part,array[part]);
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(1500);//休眠1500毫秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /**
                     * 要执行的操作
                     */
                    quicksort(array,part+1,last);
                    for(int j = 0; j < 11; j++){
                        yValue.add(array[j]);
                    }
                    yValues.clear();
                    yValues.add(yValue);
                    barChartManager1.showBarChart(xValues, yValues.get(0), names.get(0), colors,part,array[part]);
                }
            }.start();

        }
    }
    public static boolean isSorted(float[] data) {
        boolean flag1 = false,flag2 = false;
        //数组是否为升序
        for (int i = 0; i < data.length-1; i++) {
            if (data[i] == Math.min(data[i], data[i + 1])) {
                flag1 = true;
            } else {
                flag1 = false;
                break;
            }
        }
        //数组是否为降序
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] == Math.max(data[i], data[i + 1])) {
                flag2 = true;
            } else {
                flag2 = false;
                break;
            }
        }
        if (flag1 || flag2) {
            return true;//有一个为真，即是已经排过序了
        } else {
            return  false;
        }
    }
}