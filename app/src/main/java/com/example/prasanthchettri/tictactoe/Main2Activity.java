package com.example.prasanthchettri.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    static boolean whichplayer ;
    private Button[][] buttons = new Button[3][3] ;
    private int[] players = new int[2];
    private int roundcount ;
    private String[] character = new String[2] ;
    private TextView[] playerstext = new TextView[2] ;
    private Random cords ;
    private Boolean iswin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        whichplayer = getIntent().getExtras().getInt("Vara") == 1 ? true : false ;
        players[0] = 0 ;
        iswin = false ;
        players[1] = 0 ;
        character[0] = "X" ;
        character[1] = "O";
        roundcount = 0 ;
        cords = new Random();
        playerstext[0] = (TextView) findViewById(R.id.text1) ;
        playerstext[1] = (TextView) findViewById(R.id.text2);
        //-----------------------------
        MainGame mainGame  = new MainGame();
    }


    private class MainGame
    {
        //INITIALIZER
        //-------------------------------------------------------------------------------------------------------------------------------
          MainGame()
        {
            for (int x = 0 ; x < 3 ;x++)
            {
                for (int y = 0 ; y < 3 ; y++)
                {
                    //Listeners for the X0 buttons
                    buttons[x][y] =   findViewById(getResources().getIdentifier("button" + x + y , "id" , getPackageName())) ;
                }
                //Relative layout components
                Button reset = (Button) findViewById(R.id.button) ;
                reset.setOnClickListener(
                        new Button.OnClickListener()
                        {
                            @Override
                            public void onClick(View view) {
                                gamereset();

                            }
                        }
                );
            }
            listeners();
        }
        private void listeners() {
            for (int x = 0 ; x < 3 ; x++)
            {
                for (int y = 0; y < 3; y++) {
                    final int finalX = x;
                    final int finalY = y;
                    buttons[x][y].setOnClickListener(
                            new Button.OnClickListener()
                            {
                                @Override
                                public void onClick(View view) {
                                    if (iswin){
                                        reseter();
                                        iswin = false ;
                                    }
                                    if(((Button) view).getText().equals("")) {

                                        ((Button) view).setText(character[roundcount % 2]);
                                    }
                                    if (checker()){
                                        winner();
                                    }
                                    drawcheck();
                                    roundcount++;
                                    if (whichplayer){
                                        int comx = cords.nextInt(3);
                                        int comy = cords.nextInt(3);
                                        while (buttons[comx][comy].getText().toString() != ""){
                                            comx = cords.nextInt(3);
                                            comy = cords.nextInt(3);
                                        }
                                        buttons[comx][comy].setText("O");
                                        if (checker()){
                                            int playertac =  (roundcount % 2 ) + 1 ;
                                            Toast.makeText(getApplicationContext() , "You Lost !"  , Toast.LENGTH_SHORT).show();
                                            playerstext[playertac - 1].setText("PLAYER " +String.valueOf(playertac) + " : " + Integer.toString(++players[roundcount%2]));
                                            iswin = true ;
                                        }
                                        drawcheck();
                                        roundcount++;

                                    }

                                }
                            }
                    );
                }
            }
        }
        //---------------------------------------------------------------------------------------------------------------------------------
        private void drawcheck()
        {
            if(roundcount >= 9 )
            {
                reseter();
                roundcount = 0 ;
                Toast t = Toast.makeText(getApplicationContext() , "It was a draw" , Toast.LENGTH_SHORT) ;
                t.show();
            }
        }
        private void reseter()
        {
            for (int x = 0 ; x < 3 ;x++) {

                for (int y = 0; y < 3; y++) {
                    buttons[x][y].setText("") ;

                }
            }
            roundcount = 0 ;
        }
        private void gamereset()
        {
            reseter();
            players[1] = 0 ; players[0] = 0 ;
            playerstext[0].setText("PLAYER " + 1  + " : 0") ;
            playerstext[1].setText("PLAYER " + 2  + " : 0") ;

        }
        // -
        private boolean checker()
        {
            String x = character[0] ;
            String o = character[1] ;
            if((buttons[0][0].getText().equals(o) && buttons[1][1].getText().equals(o) && buttons[2][2].getText().equals(o)) || buttons[0][0].getText().equals(x) && buttons[1][1].getText().equals(x) && buttons[2][2].getText().equals(x))
            {
                return true ;
            }
            if((buttons[0][2].getText().equals(o) && buttons[1][1].getText().equals(o) && buttons[2][0].getText().equals(o)) || buttons[0][2].getText().equals(x) && buttons[1][1].getText().equals(x) && buttons[2][0].getText().equals(x))
            {
                return true ;
            }
            for (int l = 0 ; l < 3 ; l++)
            {
                if(buttons[l][0].getText().equals(o) && buttons[l][1].getText().equals(o) && buttons[l][2].getText().equals(o))
                {
                    return true ;
                }
                if(buttons[0][l].getText().equals(o) && buttons[1][l].getText().equals(o) && buttons[2][l].getText().equals(o))
                {
                    return true ;
                }
            }
            for (int l = 0 ;l < 3 ; l++)
            {
                if(buttons[l][0].getText().equals(x) && buttons[l][1].getText().equals(x) && buttons[l][2].getText().equals(x))
                {
                    return true ;
                }
                if(buttons[0][l].getText().equals(x) && buttons[1][l].getText().equals(x) && buttons[2][l].getText().equals(x))
                {
                    return true ;
                }
            }
            return false ;
        }
        void winner()
        {
            int playertac =  (roundcount % 2 ) + 1 ;
            Toast toast = Toast.makeText(getApplicationContext(), "Player " + playertac + " won", Toast.LENGTH_SHORT);
            toast.show() ;
            playerstext[playertac - 1].setText("PLAYER " +playertac + " : " + Integer.toString(++players[roundcount%2]));
            iswin = true;

        }
    }
}

