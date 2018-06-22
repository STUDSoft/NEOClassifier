package uniba.neoclassifier.gui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import uniba.neoclassifier.R;
import uniba.neoclassifier.entity.Paziente;
import uniba.neoclassifier.utility.SessionManager;

public class CreateUserActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String PAZIENTE = "uniba.neoclassifier.entity.PAZIENTE";
    private SessionManager session;
    private EditText nameField = null;
    private EditText surnameField = null;
    private EditText emailField = null;
    private EditText dateField = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        session = new SessionManager(getApplicationContext());
        TextInputLayout nameWrapper = findViewById(R.id.editText3);
        final TextInputLayout surnameWrapper = findViewById(R.id.editText4);
        final TextInputLayout dateWrapper = findViewById(R.id.editText5);
        final TextInputLayout emailWrapper = findViewById(R.id.editText7);
        final Button createProfile = findViewById(R.id.button);
        Animation animFadeInSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_slide_up);
        Animation animFadeInSlideUp2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_slide_up2);
        Animation animFadeInSlideUp3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_slide_up3);
        Animation scale1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale1);
        final Animation scale2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale2);
        final Animation scale3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale3);
        final Animation scale4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale4);
        final Animation scale5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale5);
        nameField = findViewById(R.id.nameField);
        surnameField = findViewById(R.id.surnameField);
        dateField = findViewById(R.id.dateField);
        emailField = findViewById(R.id.emailField);
        dateField.setKeyListener(null);
        nameField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    validateName();
                }
            }
        });
        surnameField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    validateSurname();
                }
            }
        });
        dateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(CreateUserActivity.this);
                showDatePicker();
            }
        });
        dateField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    hideKeyboard(CreateUserActivity.this);
                    showDatePicker();
                } else {
                    validateDate();
                }
            }
        });
        emailField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    validateEmail();
                }
            }
        });
        emailField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard(CreateUserActivity.this);
                    createProfile.performClick();
                    return true;
                }
                return false;
            }
        });
        dateField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emailField.requestFocus();
                showKeyboard();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createProfile();
            }
        });
        ImageView logo = findViewById(R.id.imageView);
        TextView benvenuti = findViewById(R.id.textView3);
        TextView crea_prof = findViewById(R.id.textView4);
        logo.startAnimation(animFadeInSlideUp);
        benvenuti.startAnimation(animFadeInSlideUp2);
        crea_prof.startAnimation(animFadeInSlideUp3);
        nameWrapper.startAnimation(scale1);
        surnameWrapper.startAnimation(scale2);
        dateWrapper.startAnimation(scale3);
        emailWrapper.startAnimation(scale4);
        createProfile.startAnimation(scale5);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        month = month + 1;
        String currentDate = day + "/" + month + "/" + year;
        dateField = findViewById(R.id.dateField);
        dateField.setText(currentDate);
    }

    private void createProfile() {
        boolean validate = true;
        if (!validateName()) {
            validate = false;
        }
        if (!validateSurname()) {
            validate = false;
        }
        if (!validateDate()) {
            validate = false;
        }
        if (!validateEmail()) {
            validate = false;
        }
        if (validate) {
            Paziente paziente = new Paziente(nameField.getText().toString(), surnameField.getText().toString(),
                    dateField.getText().toString(), emailField.getText().toString());
            if (!session.isLoggedIn()) {
                session.createLoginSession(nameField.getText().toString(), surnameField.getText().toString(), dateField.getText().toString(), emailField.getText().toString());
                Intent intent = new Intent(this, UserRegisteredActivity.class);
                intent.putExtra(PAZIENTE, paziente);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.no_anim);
                finish();

            } else {
                Toast.makeText(this, "Errore nella registrazione", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean validateName() {
        TextInputLayout nameWrapper = findViewById(R.id.editText3);
        String name = nameField.getText().toString();
        if (name.isEmpty()) {
            nameWrapper.setErrorEnabled(true);
            nameWrapper.setError("Inserisci un nome");
            return false;
        } else if (stringContainsNumbers(name)) {
            nameWrapper.setErrorEnabled(true);
            nameWrapper.setError("Il nome contiene numeri");
            return false;
        } else {
            nameWrapper.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateSurname() {
        TextInputLayout surnameWrapper = findViewById(R.id.editText4);
        String surname = surnameField.getText().toString();
        if (surname.isEmpty()) {
            surnameWrapper.setErrorEnabled(true);
            surnameWrapper.setError("Inserisci un cognome");
            return false;
        } else if (stringContainsNumbers(surname)) {
            surnameWrapper.setErrorEnabled(true);
            surnameWrapper.setError("Il cognome contiene numeri");
            return false;
        } else {
            surnameWrapper.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateDate() {
        TextInputLayout dateWrapper = findViewById(R.id.editText5);
        String date = dateField.getText().toString();
        if (date.isEmpty()) {
            dateWrapper.setErrorEnabled(true);
            dateWrapper.setError("Inserisci una data di nascita");
            return false;
        } else if (!dateValidator(date)) {
            dateWrapper.setErrorEnabled(true);
            dateWrapper.setError("Data non valida");
            return false;
        } else {
            dateWrapper.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        TextInputLayout emailWrapper = findViewById(R.id.editText7);
        String email = emailField.getText().toString();
        if (email.isEmpty()) {
            emailWrapper.setErrorEnabled(true);
            emailWrapper.setError("Inserisci una e-mail");
            return false;
        } else if (!emailValidator(email)) {
            emailWrapper.setErrorEnabled(true);
            emailWrapper.setError("L'e-mail inserita non è valida");
            return false;
        } else {
            emailWrapper.setErrorEnabled(false);
            return true;
        }
    }

    private boolean stringContainsNumbers(String s) {
        return Pattern.compile("[0-9]").matcher(s).find();
    }

    private boolean emailValidator(String s) {
        return Patterns.EMAIL_ADDRESS.matcher(s).matches();
    }

    private boolean dateValidator(String s) {
        boolean valid = true;
        SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
        sdfrmt.setLenient(false);
        try {
            java.util.Date insertDate = sdfrmt.parse(s);
            Date today = new Date();
            if (insertDate.compareTo(today) > 0) {
                valid = false;
            } else {
                valid = true;
            }
        } catch (ParseException e) {
            valid = false;
        }
        return valid;
    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private void showDatePicker() {
        DatePickerFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    protected void onPause() {
        hideKeyboard(CreateUserActivity.this);
        super.onPause();
    }
}
