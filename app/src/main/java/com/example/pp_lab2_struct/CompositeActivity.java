package com.example.pp_lab2_struct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class CompositeActivity extends AppCompatActivity {

    //--------------------------------------
    public interface Shape {

    }
    //--------------------------------------
    abstract class BaseShape implements Shape {

        BaseShape() {}
    }
    //--------------------------------------
    public class Dot extends BaseShape {
        public Dot() {
            tv.setText(tv.getText()+"\n"+"create dot");
        }
    }
    //--------------------------------------
    public class Circle extends BaseShape {
        public Circle() {
            tv.setText(tv.getText()+"\n"+"create circle");
        }
    }
    //--------------------------------------
    public class Rectangle extends BaseShape {
        public Rectangle() {
            tv.setText(tv.getText()+"\n"+"create rectangle");
        }
    }
    //--------------------------------------
    public class CompoundShape extends BaseShape {
        public CompoundShape(Shape... components) {
            tv.setText(tv.getText()+"\n"+"create complex figure");
        }

    }


    //--------------------------------------


    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composite);
        tv=(TextView)findViewById(R.id.tv);
    }

    public void create(View view){
        CompoundShape compoundShape = new CompoundShape(new Dot(),
                new Circle(), new Rectangle());
    }

    public void clear(View view){
        tv.setText("");
    }

}
