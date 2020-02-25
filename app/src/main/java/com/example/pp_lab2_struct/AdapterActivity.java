package com.example.pp_lab2_struct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AdapterActivity extends AppCompatActivity {

    //----------------------------------
    public class RoundHole {
        private double radius;

        public RoundHole(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }

        public boolean fits(RoundPeg peg) {
            boolean result;
            result = (this.getRadius() >= peg.getRadius());
            return result;
        }
    }
    //----------------------------------
    public class RoundPeg {
        private double radius;

        public RoundPeg() {}

        public RoundPeg(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }
    }
    //----------------------------------
    public class SquarePeg {
        private double width;

        public SquarePeg(double width) {
            this.width = width;
        }

        public double getWidth() {
            return width;
        }

        public double getSquare() {
            double result;
            result = Math.pow(this.width, 2);
            return result;
        }
    }
    //----------------------------------
    public class SquarePegAdapter extends RoundPeg {
        private SquarePeg peg;

        public SquarePegAdapter(SquarePeg peg) {
            this.peg = peg;
        }

        @Override
        public double getRadius() {
            double result;
            // calculate min radius
            result = (Math.sqrt(Math.pow((peg.getWidth() / 2), 2) * 2));
            return result;
        }
    }
    //----------------------------------

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        tv=(TextView)findViewById(R.id.tv);
    }

    public void create(View view){
        RoundHole hole = new RoundHole(5);
        RoundPeg rpeg = new RoundPeg(5);
        if (hole.fits(rpeg)) {
            tv.setText(tv.getText()+"\n"+"Round peg r5 fits round hole r5.");
        }

        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(20);
        // hole.fits(smallSqPeg); // no compile

        // adapter
        SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter largeSqPegAdapter = new SquarePegAdapter(largeSqPeg);
        if (hole.fits(smallSqPegAdapter)) {
            tv.setText(tv.getText()+"\n"+"Square peg w2 fits round hole r5.");
        }
        if (!hole.fits(largeSqPegAdapter)) {
            tv.setText(tv.getText()+"\n"+"Square peg w20 does not fit into round hole r5.");
        }
    }

    public void clear(View view){
        tv.setText("");
    }

}
