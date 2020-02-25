package com.example.pp_lab2_struct;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void adapter(View view){
        Intent intent = new Intent(MainActivity.this, AdapterActivity.class);
        startActivity(intent);
    }

    public void decorator(View view){
        Intent intent = new Intent(MainActivity.this, DecoratorActivity.class);
        startActivity(intent);
    }

    public void proxy(View view){
        Intent intent = new Intent(MainActivity.this, ProxyActivity.class);
        startActivity(intent);
    }

    public void composite(View view){
        Intent intent = new Intent(MainActivity.this, CompositeActivity.class);
        startActivity(intent);
    }

    public void bridge(View view){
        Intent intent = new Intent(MainActivity.this, BridgeActivity.class);
        startActivity(intent);
    }

    public void flyweight(View view){
        Intent intent = new Intent(MainActivity.this, FlyweightActivity.class);
        startActivity(intent);
    }

    public void facade(View view){
        Intent intent = new Intent(MainActivity.this, FacadeActivity.class);
        startActivity(intent);
    }
}
