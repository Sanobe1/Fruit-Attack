package com.ancientegypt.bowl;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
    }

    public void onClick(View v) {
        Button button  = findViewById(v.getId());

        switch (button.getTag().toString()) {

            case "start" : {
                startActivity(new Intent(this, MainActivity.class));
                break;
            }

            case "policy": {
                startActivity(new Intent(this, PrivacyPolicyActivity.class));
            }

            case "rules": {
                startActivity(new Intent(this, RulesActivity.class));
                break;
            }

            case "exit": {
                finish();
                break;
            }
            default:
                break;
        }
    }
}

