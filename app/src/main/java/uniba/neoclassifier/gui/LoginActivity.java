package uniba.neoclassifier.gui;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import uniba.neoclassifier.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView registerText = findViewById(R.id.textView2);
        ImageView img = findViewById(R.id.imageView4);
        TextView benvenuti = findViewById(R.id.textView8);
        TextInputLayout mail = findViewById(R.id.editText7);
        TextInputLayout password = findViewById(R.id.editText8);
        Button loginButton = findViewById(R.id.button2);
        Animation scale1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale1_login);
        Animation animFadeInSlideUp2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_slide_up2);
        final Animation scale2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale2);
        final Animation scale3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale3);
        final Animation scale4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale4);
        final Animation scale5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale5);
        String sourceString = "Non hai un account? <u><b>Registrati</b></u>";
        registerText.setText(Html.fromHtml(sourceString));
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, CreateUserActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.no_anim);
                finish();
            }
        });
        img.startAnimation(scale1);
        benvenuti.startAnimation(animFadeInSlideUp2);
        mail.startAnimation(scale2);
        password.startAnimation(scale3);
        loginButton.startAnimation(scale4);
        registerText.startAnimation(scale5);
    }
}
