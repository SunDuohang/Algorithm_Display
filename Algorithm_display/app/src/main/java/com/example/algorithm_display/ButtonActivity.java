package com.example.algorithm_display;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonActivity extends AppCompatActivity{

    private Button btn_SelSort;
    private Button btn_BubSort;
    private Button btn_InsSort;
    private Button btn_QucikSort;
    private Button btn_test;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_sort);

        btn_SelSort = (Button) findViewById(R.id.btn1_SelSort);
        btn_SelSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ButtonActivity.this, Button_Sel_Activity.class);
                startActivity(intent);
            }
        });
        btn_BubSort = (Button) findViewById(R.id.btn1_BubSort);
        btn_BubSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ButtonActivity.this, Button_Bub_Activity.class);
                startActivity(intent);
            }
        });
        btn_InsSort =(Button)findViewById(R.id.btn1_InsSort);
        btn_InsSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ButtonActivity.this,Button_Ins_Activity.class);
                startActivity(intent);
            }
        });

        btn_QucikSort = (Button)findViewById(R.id.btn1_QuickSort);
        btn_QucikSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ButtonActivity.this,Button_Quick_Activity.class);
                startActivity(intent);
            }
        });

        btn_test = (Button) findViewById(R.id.test);
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ButtonActivity.this,test.class);
                startActivity(intent);
            }
        });
    }
}
