package com.samsung.product_service;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.samsung.product_service.model.Product;
import com.samsung.product_service.model.ProductService;

public class SearchActivity extends AppCompatActivity {
    private ProductService productService;

    private Button button_search;
    private EditText et_search;
    private TextView tv_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        productService = (ProductService) getIntent().getSerializableExtra("product_service");

        button_search = findViewById(R.id.button_search);
        et_search = findViewById(R.id.product_name);
        tv_info = findViewById(R.id.product_info);

        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = productService.findByName(et_search.getText().toString());
                if (product == null) {
                    tv_info.setText(R.string.product_no_info);
                }
                else {
                    String s = "Название: " + product.getName() + "\n" +
                            "Дата поступления: " + product.getDate() + "\n" +
                            "Количество: " + product.getCount() + "\n" +
                            "Стоимость за единицу: " + product.getCost();
                    tv_info.setText(s);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
