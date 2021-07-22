package com.fruitattackwin.bowl;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class FinalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
    }

    public void onClick(View v) {
        Button button = findViewById(v.getId());

        switch (button.getTag().toString()) {

            case "restartGame": {
                startActivity(new Intent(this, MainActivity.class));
                break;
            }

            case "menu": {
                startActivity(new Intent(this, StartActivity.class));
            }


        }
    }
}
