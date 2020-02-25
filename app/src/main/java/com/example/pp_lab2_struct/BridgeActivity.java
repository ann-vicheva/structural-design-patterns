package com.example.pp_lab2_struct;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BridgeActivity extends AppCompatActivity {

    //-------------------------------
    public interface Device {
        boolean isEnabled();

        void enable();

        void disable();

        int getVolume();

        void setVolume(int percent);

        int getChannel();

        void setChannel(int channel);

        void printStatus();
    }
    //-------------------------------
    public class Radio implements Device {
        private boolean on = false;
        private int volume = 30;
        private int channel = 1;

        @Override
        public boolean isEnabled() {
            return on;
        }

        @Override
        public void enable() {
            on = true;
        }

        @Override
        public void disable() {
            on = false;
        }

        @Override
        public int getVolume() {
            return volume;
        }

        @Override
        public void setVolume(int volume) {
            if (volume > 100) {
                this.volume = 100;
            } else if (volume < 0) {
                this.volume = 0;
            } else {
                this.volume = volume;
            }
        }

        @Override
        public int getChannel() {
            return channel;
        }

        @Override
        public void setChannel(int channel) {
            this.channel = channel;
        }

        @Override
        public void printStatus() {
            tv.setText(tv.getText()+"\n"+"--------------------"+"\n"+
            "I'm radio."+" I'm " + (on ? "enabled" : "disabled")+
            " Current volume is " + volume + "%"+" Current channel is " + channel);
        }
    }
    //-------------------------------
    public class Tv implements Device {
        private boolean on = false;
        private int volume = 30;
        private int channel = 1;

        @Override
        public boolean isEnabled() {
            return on;
        }

        @Override
        public void enable() {
            on = true;
        }

        @Override
        public void disable() {
            on = false;
        }

        @Override
        public int getVolume() {
            return volume;
        }

        @Override
        public void setVolume(int volume) {
            if (volume > 100) {
                this.volume = 100;
            } else if (volume < 0) {
                this.volume = 0;
            } else {
                this.volume = volume;
            }
        }

        @Override
        public int getChannel() {
            return channel;
        }

        @Override
        public void setChannel(int channel) {
            this.channel = channel;
        }

        @Override
        public void printStatus() {
            tv.setText(tv.getText()+"\n"+"--------------------"+"\n"+
                            "I'm TV set."+" I'm " + (on ? "enabled" : "disabled")+
                            " Current volume is " + volume + "%"+" Current channel is " + channel+"\n");
        }
    }
    //-------------------------------
    public interface Remote {
        void power();

        void volumeDown();

        void volumeUp();

        void channelDown();

        void channelUp();
    }
    //-------------------------------
    public static class BasicRemote implements Remote {
        protected Device device;

        public BasicRemote() {}

        public BasicRemote(Device device) {
            this.device = device;
        }

        @Override
        public void power() {

            //System.out.println("Remote: power toggle");
            if (device.isEnabled()) {
                device.disable();
            } else {
                device.enable();
            }
        }

        @Override
        public void volumeDown() {
            //System.out.println("Remote: volume down");
            device.setVolume(device.getVolume() - 10);
        }

        @Override
        public void volumeUp() {
            //System.out.println("Remote: volume up");
            device.setVolume(device.getVolume() + 10);
        }

        @Override
        public void channelDown() {
            //System.out.println("Remote: channel down");
            device.setChannel(device.getChannel() - 1);
        }

        @Override
        public void channelUp() {
            //System.out.println("Remote: channel up");
            device.setChannel(device.getChannel() + 1);
        }
    }
    //-------------------------------
    public static class AdvancedRemote extends BasicRemote {

        public AdvancedRemote(Device device) {
            super.device = device;
        }

        public void mute() {
            //System.out.println("Remote: mute");
            device.setVolume(0);
        }
    }
    //-------------------------------
    public static void testDevice(Device device) {
        //System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        device.printStatus();

        //System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.power();
        advancedRemote.mute();
        device.printStatus();
    }
    //-------------------------------
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge);
        tv=(TextView)findViewById(R.id.tv);
    }

    public void create(View view){
        testDevice(new Tv());
        testDevice(new Radio());
    }

    public void clear(View view){
        tv.setText("");
    }

}
