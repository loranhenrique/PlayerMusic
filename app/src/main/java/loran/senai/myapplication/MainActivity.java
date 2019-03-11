package loran.senai.myapplication;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton btPlay, btPause, btStop;

    //Classe AssetFileDescriptor utilizada para buscar algum recurso na pasta asset
    private AssetFileDescriptor arquivo;

    //Classe MediaPlayer que contém os métodos para reprodução de mp3
    private MediaPlayer player = new MediaPlayer();

    //Variável int para controlar quantos segundos já foram tocados da música
    int segundos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btPlay = findViewById(R.id.btPlay);
        btPause = findViewById(R.id.btPause);
        btStop = findViewById(R.id.btStop);

        //Programação dos botões
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //teste se ja não está tocando a musica
                    if (!player.isPlaying()){
                        //busca o arquivo do senna.mp3 na pasta do assets
                        //arquivo = getAssets().openFd("senna.mp3");

                        //Passa o arquivo para a classe mediaPlayer
                        // player.setDataSource(arquivo.getFileDescriptor());
                        player = MediaPlayer.create(getApplicationContext(), R.raw.senna);

                        //inicia a musica
                        // player.prepare();//carrega a musica na memória
                        player.start();
                        Log.d("TAG_IDENTIFICACAO","Start música");
                    }

                }catch (Exception exc){
                    exc.printStackTrace();
                }
            }
        });

        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Primeiro, testar se tem musica tocando ou não
                if(player.isPlaying()){//se a musica estiver tocando
                    //Recupera em quantos segundos a musica está atualmente
                    segundos = player.getCurrentPosition();
                    player.pause();//interrompe a musica

                }else{//se não tiver musica tocando
                    //volta a posição para onde estava armazenado
                    player.seekTo(segundos);
                    player.start();//volta a tocar a musica
                }
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying()) {//se a musica estiver tocando
                    player.pause();//interrompe a reprodução
                    player.stop();//cancela a musica
                    player.reset();//deixa a música disponivel novamente
                }
            }
        });

        Log.d( "TAG_IDENTIFICACAO", "Entrou no método onCreate");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d( "TAG_IDENTIFICACAO", "Entrou no método onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d( "TAG_IDENTIFICACAO", "Entrou no método onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(player != null){
            segundos = player.getCurrentPosition();
            player.pause();
        }
        Log.d( "TAG_IDENTIFICACAO", "Entrou no método onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d( "TAG_IDENTIFICACAO", "Entrou no método onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        player.seekTo(segundos);
        player.start();//volta a tocar a musica

        Log.d( "TAG_IDENTIFICACAO", "Entrou no método onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*if (player != null) {
            player.release();
        }*/
        Log.d( "TAG_IDENTIFICACAO", "Entrou no método onDestroy");
    }
}
