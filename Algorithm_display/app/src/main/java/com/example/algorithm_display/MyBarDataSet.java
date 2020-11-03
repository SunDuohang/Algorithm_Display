package com.example.algorithm_display;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;
//在BarChartManager中进行调用
//在这里对getColor进行重写，主要是控制待换元素进行单独进行颜色显示
public class MyBarDataSet extends BarDataSet {
    float target = 0;
    public MyBarDataSet(List<BarEntry> yVals, String label) {
        super(yVals, label);
    }
    public MyBarDataSet(List<BarEntry> yVals, String label, float target1) {
        super(yVals,label);
        target = target1;
    }

    @Override
    public int getColor(int index) {
        //根据getEntryForXIndex(index).getVal()获得Y值,然后去对比,判断
        //我这1000 4000是根据自己的需求写的,可以随便设,判断条件if根据自己需求
        if(getEntryForIndex(index).getY() == target)
            return mColors.get(0);
        else // greater or equal than 100 red
            return mColors.get(1);
    }
    /*public int getColor(int index) {
        //根据getEntryForXIndex(index).getVal()获得Y值,然后去对比,判断
        //我这1000 4000是根据自己的需求写的,可以随便设,判断条件if根据自己需求
        if(getEntryForIndex(index).getY() > 5)
            return mColors.get(0);
        else if(getEntryForIndex(index).getY() > 10)
            return mColors.get(1);
        else // greater or equal than 100 red
            return mColors.get(2);
    }*/

}
