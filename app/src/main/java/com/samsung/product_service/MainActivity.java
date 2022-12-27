package com.samsung.product_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.samsung.product_service.model.Product;
import com.samsung.product_service.model.ProductService;

public class MainActivity extends AppCompatActivity {
    private ProductService productService;

    private EditText et_name;
    private EditText et_date;
    private EditText et_count;
    private EditText et_cost;

    private Button button_search;
    private Button button_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productService = new ProductService();

        button_add = findViewById(R.id.button_add);
        button_search = findViewById(R.id.button_search);

        et_name = findViewById(R.id.et_name);
        et_date = findViewById(R.id.et_date);
        et_cost = findViewById(R.id.et_cost);
        et_count = findViewById(R.id.et_count);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (et_name.getText().toString().isEmpty() || et_date.getText().toString().isEmpty()) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    Product product = new Product(
                            et_name.getText().toString(),
                            et_date.getText().toString(),
                            Integer.parseInt(et_count.getText().toString()),
                            Integer.parseInt(et_cost.getText().toString()));
                    Product needed_product = productService.findByName(product.getName());
                    if (needed_product == null) {
                        productService.addProduct(product);
                        Toast.makeText(getApplication(), R.string.add_product_toast, Toast.LENGTH_LONG).show();
                    } else {
                        needed_product.updateInfo(product);
                        Toast.makeText(getApplication(), R.string.update_product_toast, Toast.LENGTH_LONG).show();
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    Toast.makeText(getApplication(), R.string.fill_gaps_toast, Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    Toast.makeText(getApplication(), R.string.error_toast, Toast.LENGTH_LONG).show();
                }
            }
        });

        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("product_service", productService);
                startActivity(intent);
            }
        });
    }
}