package co.edu.unipiloto.ciclodevida;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;
    private String time ="0:00:00";
    private String textoVueltas="";
    private int vueltas=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickRestart(View view) {
        running = false;
        seconds = 0;
        vueltas = 0;
        textoVueltas="";
        final TextView textNumeroDeVueltas = (TextView) findViewById(R.id.txtFieldVueltaUno);
        textNumeroDeVueltas.setText(textoVueltas);
    }

    private void runTimer(){

        final TextView timeView = (TextView) findViewById(R.id.timeView);
        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);

                timeView.setText(time);

                if (running) {
                    seconds++;
                }
                System.out.println(seconds);
                handler.postDelayed(this,1000);
            }
        });
    }

    public void onClickVuelta(View view) {

        final TextView textNumeroDeVueltas = (TextView) findViewById(R.id.txtFieldVueltaUno);
        if(vueltas<10) {
            vueltas++;
            textoVueltas=textoVueltas+"Nuelta N°"+vueltas+" | "+time+"\n";
            textNumeroDeVueltas.setText(textoVueltas);
        }
    }
}