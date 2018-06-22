package uniba.neoclassifier.gui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import uniba.neoclassifier.R;
import uniba.neoclassifier.entity.Paziente;

public class UserRegisteredActivity extends AppCompatActivity {
    private static final String PAZIENTE = "uniba.neoclassifier.entity.PAZIENTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registered);
        ImageView img = findViewById(R.id.imageView2);
        TextView text1 = findViewById(R.id.textView);
        TextView text2 = findViewById(R.id.textView5);
        FloatingActionButton newScan = findViewById(R.id.floatingActionButton);
        FloatingActionButton goHome = findViewById(R.id.floatingActionButton2);
        Animation zoomDim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_from_dimension);
        Animation down1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_slide_down);
        Animation down2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_slide_down2);
        Animation zoom1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_round1);
        Animation zoom2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_round2);
        img.startAnimation(zoomDim);
        text1.startAnimation(down1);
        text2.startAnimation(down2);
        newScan.startAnimation(zoom1);
        goHome.startAnimation(zoom2);
        newScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome();
            }
        });
    }

    private void goHome() {
        Paziente paziente = (Paziente) getIntent().getParcelableExtra(PAZIENTE);
        Intent intent = new Intent(this, HomePageActivity.class);
        intent.putExtra(PAZIENTE, paziente);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back);
        finish();
    }
}
