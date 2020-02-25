package com.example.pp_lab2_struct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ProxyActivity extends AppCompatActivity {

    //---------------------------------
    public interface ISubject
    {
        void Request();
    }
    //---------------------------------
    class RealSubject implements ISubject
    {
        public void Request()
        {
            tv.setText(tv.getText()+"\n"+"RealSubject: Handling Request.");
            //Console.WriteLine("RealSubject: Handling Request.");
        }
    }
    //---------------------------------
    class Proxy implements ISubject
    {
        private RealSubject _realSubject;

        public Proxy(RealSubject realSubject)
        {
            this._realSubject = realSubject;
        }
        public void Request()
        {
            if (this.CheckAccess())
            {
                this._realSubject = new RealSubject();
                this._realSubject.Request();

                this.LogAccess();
            }
        }

        public boolean CheckAccess()
        {
            tv.setText(tv.getText()+"\n"+"Proxy: Checking access prior to firing a real request.");
            return true;
        }

        public void LogAccess()
        {
            tv.setText(tv.getText()+"\n"+"Proxy: Logging the time of request.");
        }
    }
    //---------------------------------
    public class Client
    {
        public void ClientCode(ISubject subject)
        {
            subject.Request();

        }
    }
    //---------------------------------

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);
        tv=(TextView)findViewById(R.id.tv);
    }

    public void create(View view){
        Client client = new Client();
        tv.setText(tv.getText()+"\n"+"Client: Executing the client code with a real subject:");
        RealSubject realSubject = new RealSubject();
        client.ClientCode(realSubject);
        tv.setText(tv.getText()+"\n"+"Client: Executing the same client code with a proxy:");
        Proxy proxy = new Proxy(realSubject);
        client.ClientCode(proxy);
    }

    public void clear(View view){
        tv.setText("");
    }
}
