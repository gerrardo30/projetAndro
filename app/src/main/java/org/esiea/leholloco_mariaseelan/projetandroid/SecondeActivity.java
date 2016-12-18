package org.esiea.leholloco_mariaseelan.projetandroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondeActivity extends AppCompatActivity {

    public EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);
    }

    public void research(View v){
        text=(EditText) findViewById(R.id.text);
        String entree= text.getText().toString();
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+entree)));
    }
}
