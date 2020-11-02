package com.example.algorithm_display;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class test extends AppCompatActivity {
    private Button btn_Ins_Ex;
    private MyBarChart barChart1;
    private  float array[] = new float[5];
    private MyBarChartManager barChartManager1 ;
    private ArrayList<Float> xValues = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private List<Integer> colors = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        barChart1 = findViewById(R.id.btn_test1);
        barChartManager1 = new MyBarChartManager(barChart1);
        //设置x轴的数据
        ArrayList<String> xValues0 = new ArrayList<>();
        //设置x轴的数据
        xValues = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            xValues.add((float) i);
        }
        //设置y轴的数据()
        final List<List<Float>> yValues = new ArrayList<>();
        List<Float> yValue = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            array[i] = ((float) (Math.random() * 8) + 2);
            yValue.add(array[i]);
        }
        yValues.add(yValue);
        //颜色集合


        colors.add(Color.RED);
        colors.add(Color.CYAN);
        //线的名字集合
        names.add("柱状一");
        names.add("柱状二");
        //创建多条柱状的图表
        barChartManager1.showBarChart(xValues, yValues.get(0), names.get(0), colors);

        btn_Ins_Ex = (Button) findViewById(R.id.test);
        btn_Ins_Ex.setOnClickListener(new View.OnClickListener() {
            int count = 0;

            public void onClick(View view) {
                count++;
                if(count < array.length - 1 ) {
                    Toast.makeText(test.this, "第" + count + "次遍历", Toast.LENGTH_SHORT).show();
                }
                if(count>0&&count<array.length){
                    float currentValue = array[count];
                    int position = count;
                    for(int j = count - 1; j >= 0; j--){
                        if(array[j] > currentValue){
                            array[j+1] = array[j];
                            position -= 1;
                        }
                        else
                            break;
                    }
                    array[position] = currentValue;
                    yValues.clear();
                    List<Float> yValue = new ArrayList<>();
                    for(int j = 0; j < array.length; j++){
                        yValue.add(array[j]);
                    }
                    yValues.add(yValue);
                    barChartManager1.showBarChart(xValues, yValues.get(0), names.get(0), colors);
                }
            }
        });
    }
}