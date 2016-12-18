package org.esiea.leholloco_mariaseelan.projetandroid;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class third_activity extends Activity {
    private MediaPlayer mPlayer = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        Button btn_sound_1up = (Button) findViewById(R.id.btn_sound_1up);
        btn_sound_1up.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                playSound(R.raw.eminemloseyourself);
            }

        });

        Button btn_sound_coin = (Button) findViewById(R.id.btn_sound_coin);
        btn_sound_coin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                playSound(R.raw.eminemloseyourself);
            }

        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
    }

    private void playSound(int resId) {
        if(mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
        mPlayer = MediaPlayer.create(this, resId);
        mPlayer.start();
    }
}
