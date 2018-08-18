package com.example.prasanthchettri.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    static int playerwhich ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent ins = new Intent(MainActivity.this , Main2Activity.class ) ;

        setContentView(R.layout.activity_main);
        Button buttons[] = {findViewById(R.id.COMPUTER) , findViewById(R.id.PLAYER)};
        buttons[0].setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        ins.putExtra("Vara" , 1);
                        startActivity(ins);
                    }
                }
        );
        buttons[1].setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        ins.putExtra("Vara" , 2);
                        startActivity(ins);
                    }
                }
        );
    }
}
