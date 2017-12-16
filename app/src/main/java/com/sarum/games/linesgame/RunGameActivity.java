package com.sarum.games.linesgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import com.sarum.games.linesgame.ApplicationPreferenceService;
import org.w3c.dom.Text;

public class RunGameActivity extends AppCompatActivity {
    private GameConfig gameConfig;
    private SeekBar seekBarLinesSize;
    private SeekBar seekBarGameSpeed;
    private TextView textViewLinesSize;
    private TextView textViewGameSpeed;
    private TextView textViewPlayer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_game);
        gameConfig = ApplicationPreferenceService.getConfig(this);

        seekBarLinesSize = (SeekBar) findViewById(R.id.seekBarLinesSize);
        seekBarGameSpeed = (SeekBar) findViewById(R.id.seekBarGameSpeed);
        textViewLinesSize = (TextView) findViewById(R.id.textViewLinesSize);
        textViewGameSpeed = (TextView) findViewById(R.id.textViewGameSpeed);
        textViewPlayer1 = (TextView) findViewById(R.id.textViewPlayer1);

        seekBarLinesSize.setProgress(gameConfig.getLinesSize()-1);
        seekBarGameSpeed.setProgress(gameConfig.getGameSpeed()-1);

        textViewLinesSize.setText(String.format("Lines size: %d", gameConfig.getLinesSize()));
        textViewGameSpeed.setText(String.format("Game speed: %d", gameConfig.getGameSpeed()));

        seekBarLinesSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gameConfig.setLinesSize(progress + 1);
                textViewLinesSize.setText(String.format("Lines size: %d", gameConfig.getLinesSize()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarGameSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gameConfig.setGameSpeed(progress + 1);
                textViewGameSpeed.setText(String.format("Game speed: %d", gameConfig.getGameSpeed()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textViewPlayer1.setText(gameConfig.getNick());
    }


    @Override
    public void onStop(){
        super.onStop();
        gameConfig.saveData(this);
    }

    public void buttonStart(View view) {
        Intent intent = new Intent(RunGameActivity.this, GameActivity.class);
        startActivity(intent);
    }
}
