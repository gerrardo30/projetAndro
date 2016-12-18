package org.esiea.leholloco_mariaseelan.projetandroid;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    public Button clique;
    public Button newbutton;
    public Button londres;
    public Button retour;
    public Button music;
    public int i=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_bieres=(RecyclerView) findViewById(R.id.rv_bieres);
        clique=(Button) findViewById(R.id.clique);
        londres=(Button) findViewById(R.id.londres);
        retour=(Button) findViewById(R.id.retour);
        newbutton=(Button) findViewById(R.id.newbutton);
        music=(Button) findViewById(R.id.music);
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
                .setContentTitle(getString(R.string.notif)).setContentText(getString(R.string.button_state_1));
        NotificationManager nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0,n.build());

        RelativeLayout activitym ;
        activitym = (RelativeLayout) findViewById(R.id.activity_main);

        if(i==0) {

            activitym.setBackgroundColor(Color.GRAY);
            i = 1;
        }else{
            activitym.setBackgroundColor(Color.WHITE);
            i=0;
        }



    }

    public void fct (View v){


        GetBiersServices.startActionBiers(this);
        if(rv_bieres.getVisibility()==View.INVISIBLE) {
            rv_bieres.setVisibility(View.VISIBLE);
            clique.setVisibility(View.INVISIBLE);
            newbutton.setVisibility(View.INVISIBLE);
            londres.setVisibility(View.INVISIBLE);
            retour.setVisibility(View.VISIBLE);
            music.setVisibility(View.INVISIBLE);
            NotificationCompat.Builder n1 = (NotificationCompat.Builder) new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("téléchargement terminé").setContentText(getString(R.string.button_state));
            NotificationManager nm1 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            nm1.notify(1, n1.build());

        }else{
            rv_bieres.setVisibility(View.INVISIBLE);
            clique.setVisibility(View.VISIBLE);
            newbutton.setVisibility(View.VISIBLE);
            londres.setVisibility(View.VISIBLE);
            retour.setVisibility(View.INVISIBLE);
            music.setVisibility(View.VISIBLE);
        }



    }

    public void parametres(View v){

        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Londres")));

       Intent i=new Intent(this,SecondeActivity.class);
        startActivity(i);

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


    public void mus(View v){

        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Londres")));

        Intent i=new Intent(this,third_activity.class);
        startActivity(i);

    }

}


