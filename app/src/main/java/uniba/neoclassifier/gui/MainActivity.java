package uniba.neoclassifier.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

import uniba.neoclassifier.R;
import uniba.neoclassifier.entity.Paziente;
import uniba.neoclassifier.utility.SessionManager;


public class MainActivity extends AppCompatActivity {

    private static final String PAZIENTE = "uniba.neoclassifier.entity.PAZIENTE";
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(getApplicationContext());
        if(session.isLoggedIn()){
            Intent i = new Intent(MainActivity.this, HomePageActivity.class);
            HashMap<String, String> user = session.getUserDetails();
            Paziente paziente = new Paziente(user.get(session.NAME), user.get(session.SURNAME), user.get(session.BORN_DATE), user.get(session.EMAIL));
            i.putExtra(PAZIENTE, paziente);
            this.startActivity(i);
            finish();
        }else{
            Intent i = new Intent(MainActivity.this, CreateUserActivity.class);
            this.startActivity(i);
            finish();
        }
    }
}
