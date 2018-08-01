package uniba.neoclassifier.gui;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import uniba.neoclassifier.R;
import uniba.neoclassifier.entity.Paziente;

public class UserRegisteredActivity extends AppCompatActivity {
    private static final String PAZIENTE = "uniba.neoclassifier.entity.PAZIENTE";
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static Paziente paziente;
    private static String imageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paziente = (Paziente) getIntent().getSerializableExtra(PAZIENTE);
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
                openCamera();
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
        Intent intent = new Intent(this, HomePageActivity.class);
        intent.putExtra(PAZIENTE, paziente);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back);
        finish();
    }

    private void openCamera() {
        dispatchTakePictureIntent();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES + "/" + paziente.getEmail());
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        imageName = image.getName();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Intent intent = new Intent(this, ScanCompleteActivity.class);
            intent.putExtra(PAZIENTE, paziente);
            intent.putExtra("IMGNAME", imageName);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out);
            finish();
        }
    }
}
