package com.example.pp_lab2_struct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class DecoratorActivity extends AppCompatActivity {

    //-------------------------------------
    interface Coffee
    {
        Integer getCost();
        String getDescription();
    }

    class SimpleCoffee implements Coffee
    {
        public Integer getCost()
        {
            return 10;
        }

        public String getDescription()
        {
            return "Простой кофе";
        }
    }
    //-------------------------------------
    class MilkCoffee implements Coffee
    {
        protected Coffee coffee;

        public MilkCoffee(Coffee coffee)
        {
            this.coffee = coffee;
        }

        public Integer getCost()
        {
            return this.coffee.getCost() + 2;
        }

        public String getDescription()
        {
            return this.coffee.getDescription()+ ", молоко";
        }
    }

    class WhipCoffee implements Coffee
    {
        protected Coffee coffee;

        public  WhipCoffee(Coffee coffee)
        {
            this.coffee = coffee;
        }

        public Integer getCost()
        {
            return this.coffee.getCost() + 5;
        }

        public String getDescription()
        {
            return this.coffee.getDescription() + ", сливки";
        }
    }

    class VanillaCoffee implements Coffee
    {
        protected Coffee coffee;

        public VanillaCoffee(Coffee coffee)
        {
            this.coffee = coffee;
        }

        public Integer getCost()
        {
            return this.coffee.getCost() + 3;
        }

        public String getDescription()
        {
            return this.coffee.getDescription()+ ", ваниль";
        }
    }
    //-------------------------------------
    //-------------------------------------
    //-------------------------------------

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decorator);
        tv=(TextView)findViewById(R.id.tv);
    }

    public void create(View view){
        SimpleCoffee someCoffee = new SimpleCoffee();
        someCoffee.getCost(); // 10
        someCoffee.getDescription();

        MilkCoffee someCoffee1 = new MilkCoffee(someCoffee);
        someCoffee1.getCost(); // 12
        someCoffee1.getDescription();

        WhipCoffee someCoffee2 = new WhipCoffee(someCoffee1);
        someCoffee2.getCost(); // 17
        someCoffee2.getDescription();

        VanillaCoffee someCoffee3 = new VanillaCoffee(someCoffee2);
        someCoffee3.getCost(); // 20
        someCoffee3.getDescription();
        tv.setText(tv.getText()+"\n"+
                someCoffee.getDescription()+"\n"+
                someCoffee1.getDescription()+"\n"+
                someCoffee2.getDescription()+"\n"+
                someCoffee3.getDescription());

    }

    public void clear(View view){
        tv.setText("");
    }

}
