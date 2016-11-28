package org.esiea.leholloco_mariaseelan.projetandroid;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;




/**
 * Created by Gerard on 15/11/2016.
 */
public class BierUpdate extends BroadcastReceiver {

    RecyclerView rv_bier ;
    MainActivity m;

    public BierUpdate(RecyclerView rv_bier, MainActivity m){

        this.rv_bier=rv_bier;
        this.m=m;
    }


    @Override
    public void onReceive(Context ctx, Intent i) {
        Log.d("TAG", i.getAction());

        BiersAdapter b=(BiersAdapter) rv_bier.getAdapter();
        b.setNewBiere(m.getBiersFromFile());
       /* NotificationCompat.Builder n= (NotificationCompat.Builder) new NotificationCompat.Builder(ctx).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("ma notif").setContentText(getString(R.string.button_state));
        NotificationManager nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0,n.build());*/
    }
}
