package com.zoomcreativo.rapidogs2;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;


public class Splash extends Activity {
    private ProgressBar progresito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progresito=(ProgressBar) findViewById(R.id.pbprincipal);
        progresito.setMax((int)2000);
        animacion();
    }

    public void animacion()
    {
        new CountDownTimer(2000,20){
            @Override
            public void onFinish() {
                Intent k = new Intent(Splash.this,MainActivity.class);
                startActivity(k);
                finish();
            }
            @Override
            public void onTick(long millisUntilFinished) {
                progresito.setProgress((int)(2000-millisUntilFinished));
            }
        }.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
