package org.esiea.leholloco_mariaseelan.projetandroid;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    public static final String BIERS_UPDATE = "com.octip.cours.inf4042_11.BIERS_UPDATE" ;
    public DatePickerDialog dpd = null;
    public RecyclerView rv_bieres;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_bieres=(RecyclerView) findViewById(R.id.rv_bieres);
        final TextView tv_hw=(TextView)findViewById(R.id.tv_hello_world);
        final TextView btn_hw=(TextView)findViewById(R.id.btn_hello_world);
        DatePickerDialog.OnDateSetListener odsl = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            btn_hw.setText(dayOfMonth+"/"+month+"/"+year);
            }
        };
        dpd = new DatePickerDialog(this, odsl , 2015, 11, 30);
        IntentFilter intentFilter=new IntentFilter(BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(rv_bieres,this),intentFilter);


        rv_bieres.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        rv_bieres.setAdapter(new BiersAdapter(getBiersFromFile()));




    }


    public void fonction ( View v){
        Toast.makeText(getApplicationContext(),getString(R.string.msg), Toast.LENGTH_LONG).show();
        //dpd.show();
        NotificationCompat.Builder n= (NotificationCompat.Builder) new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("ma notif").setContentText(getString(R.string.button_state));
        NotificationManager nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0,n.build());

    }

    public void fct (View v){
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Londre")));

       // Intent i=new Intent(this,SecondeActivity.class);
       // startActivity(i);
        GetBiersServices.startActionBiers(this);
    }

    public JSONArray getBiersFromFile(){
        try{
            InputStream is=new FileInputStream(getCacheDir()+"/"+"bieres.json");
            byte[] buffer=new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer,"UTF-8"));
        }catch (IOException e){
            e.printStackTrace();
           return new JSONArray();
        }catch (JSONException e){
            e.printStackTrace();
            return new JSONArray();
        }
    }

}
