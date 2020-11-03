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
//选择排序
public class Button_Sel_Activity extends AppCompatActivity {
    private Button btn_Ins_Ex;
    private BarChart Sel_BarChart;
    private  float array[] = new float[11];
    private BarChartManager barChartManager1 ;
    private ArrayList<Float> xValues = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private List<Integer> colors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button__sel_);
        Sel_BarChart = (BarChart) findViewById(R.id.SelSort_Chart);//找到控件SelSort_Chart

        barChartManager1 = new BarChartManager(Sel_BarChart);
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

        btn_Ins_Ex = (Button) findViewById(R.id.btn_Sel_Ex);
        btn_Ins_Ex.setOnClickListener(new View.OnClickListener() {
            int count = -1;

            public void onClick(View view) {

                if(isSorted(array))
                    Toast.makeText(Button_Sel_Activity.this, "排序已完成", Toast.LENGTH_SHORT).show();
                else {
                    count++;
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                Thread.sleep(1000);//休眠1000毫秒
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            /**
                             * 要执行的操作
                             */
                            int lowest_value_index = count;
                            for (int j = count + 1; j < array.length; j++) {
                                if (array[j] < array[lowest_value_index]) {
                                    lowest_value_index = j;
                                }
                            }
                            List<Float> yValue = new ArrayList<>();
                            for (int k = 0; k < array.length; k++) {
                                yValue.add(array[k]);
                            }
                            yValues.add(yValue);
                            barChartManager1.showBarChart(xValues, yValues.get(0), names.get(0), colors, lowest_value_index, array[lowest_value_index]);
                            final int finalLowest_value_index = lowest_value_index;
                            new Thread() {
                                @Override
                                public void run() {
                                    super.run();
                                    try {
                                        Thread.sleep(500);//休眠1秒
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    /**
                                     * 要执行的操作
                                     */
                                    float temp_value = array[count];
                                    array[count] = array[finalLowest_value_index];
                                    array[finalLowest_value_index] = temp_value;
                                    yValues.clear();
                                    List<Float> yValue = new ArrayList<>();
                                    for (int k = 0; k < array.length; k++) {
                                        yValue.add(array[k]);
                                    }
                                    yValues.add(yValue);
                                    barChartManager1.showBarChart(xValues, yValues.get(0), names.get(0), colors, count, array[count]);
                                }
                            }.start();
                        }
                    }.start();
                }

            }
        });
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