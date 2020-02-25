package com.example.pp_lab2_struct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class FlyweightActivity extends AppCompatActivity {

    //----------------------
    public abstract class EnglishCharacter {

        protected char symbol;

        protected int width;

        protected int height;

        public abstract void printCharacter();

    }

    public class CharacterA extends EnglishCharacter {

        public CharacterA(){
            symbol = 'A';
            width = 10;
            height = 20;
        }

        @Override
        public void printCharacter() {
            tv.setText(tv.getText()+"\n"+"Simbol = " + symbol + " Width = " + width + " Height = " + height);
            //System.out.println("Simbol = " + symbol + " Width = " + width + " Height = " + height);
        }

    }

    public class CharacterB extends EnglishCharacter {

        public CharacterB(){
            symbol = 'B';
            width = 20;
            height = 30;
        }

        @Override
        public void printCharacter() {
            tv.setText(tv.getText()+"\n"+"Simbol = " + symbol + " Width = " + width + " Height = " + height);
            //System.out.println("Simbol = " + symbol + " Width = " + width + " Height = " + height);
        }

    }

    public class CharacterC extends EnglishCharacter {

        public CharacterC(){
            symbol = 'C';
            width = 40;
            height = 50;
        }

        @Override
        public void printCharacter() {
            tv.setText(tv.getText()+"\n"+"Simbol = " + symbol + " Width = " + width + " Height = " + height);
            //System.out.println("Simbol = " + symbol + " Width = " + width + " Height = " + height);
        }

    }

    public class FlyweightFactory {

        private HashMap<Integer, EnglishCharacter> characters = new HashMap();

        public EnglishCharacter getCharacter(int characterCode){
            EnglishCharacter character = characters.get(new Integer(characterCode));
            if (character == null){
                switch (characterCode){
                    case 1 : {
                        character = new CharacterA();
                        break;
                    }
                    case 2 : {
                        character = new CharacterB();
                        break;
                    }
                    case 3 : {
                        character = new CharacterC();
                        break;
                    }
                }
                characters.put(characterCode, character);
            }
            return character;
        }

    }
    //----------------------
    //----------------------
    //----------------------

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flyweight);
        tv=(TextView)findViewById(R.id.tv);
    }

    public void create(View view){
        FlyweightFactory factory = new FlyweightFactory();

        int [] characterCodes = {1,2,3};
        for (int nextCode : characterCodes){
            EnglishCharacter character = factory.getCharacter(nextCode);
            character.printCharacter();
        }
    }

    public void clear(View view){
        tv.setText("");
    }

}
