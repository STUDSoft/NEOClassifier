package uniba.neoclassifier.gui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import uniba.neoclassifier.R;
import uniba.neoclassifier.entity.Paziente;
import uniba.neoclassifier.utility.NEOClassifier;

public class ScanCompleteActivity extends AppCompatActivity {
    private static final String PAZIENTE = "uniba.neoclassifier.entity.PAZIENTE";
    private static Paziente paziente;
    private static String imageDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_complete);
        Intent intent = getIntent();
        ImageView photoView = findViewById(R.id.imageView3);
        CardView photoCardView = findViewById(R.id.cardView);
        String imageName = intent.getExtras().getString("IMGNAME");
        paziente = (Paziente) getIntent().getSerializableExtra(PAZIENTE);
        imageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES + "/" + paziente.getEmail()).getAbsolutePath();
        int rotate = 0;
        try {
            ExifInterface exif = new ExifInterface(imageDir + "/" + imageName);
            int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (exifOrientation) {
                case ExifInterface.ORIENTATION_NORMAL:
                    rotate = 0;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                default:
                    break;
            }
            File image = new File(imageDir + "/" + imageName);
            if (image.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
                Matrix matrix = new Matrix();
                matrix.postRotate(rotate);
                myBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(),
                        myBitmap.getHeight(), matrix, true);
                photoView.setAlpha((float) 0.7);
                photoView.setImageBitmap(myBitmap);
                Animation zoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_slide_up4);
                ProgressBar progressBar = findViewById(R.id.progressBar);
                FloatingActionButton homeButton = findViewById(R.id.floatingActionButton5);
                FloatingActionButton newScanButton = findViewById(R.id.floatingActionButton6);
                FloatingActionButton saveHome = findViewById(R.id.floatingActionButton7);
                Animation rise2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_slide_up5);
                Animation rise3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_slide_up6);
                Animation zoom1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_round3);
                Animation zoom2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_round4);
                TextView text1 = findViewById(R.id.textView6);
                TextView text2 = findViewById(R.id.textView7);
                progressBar.setVisibility(ProgressBar.VISIBLE);
                photoCardView.startAnimation(zoom);
                boolean result = NEOClassifier.classifyNeo(myBitmap);
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                homeButton.hide();
                newScanButton.hide();
                saveHome.hide();
                photoView.setAlpha((float) 1);
                if (result) {
                    photoView.setImageDrawable(getResources().getDrawable(R.drawable.safe));
                    text1.setText("Il neo analizzato risulta sano");
                    text2.setText("Puoi andare alla home o effettuare un'altra scansione");
                    homeButton.show();
                    newScanButton.show();
                    text1.startAnimation(rise2);
                    text2.startAnimation(rise3);
                    homeButton.startAnimation(zoom1);
                    newScanButton.startAnimation(zoom2);
                } else {
                    photoView.setImageDrawable(getResources().getDrawable(R.drawable.warning));
                    text1.setText("Il neo analizzato risulta pericoloso");
                    text2.setText("Si consiglia di salvare la scansione ed inviarla ad un medico");
                    saveHome.show();
                    newScanButton.show();
                    text1.startAnimation(rise2);
                    text2.startAnimation(rise3);
                    saveHome.startAnimation(zoom1);
                    newScanButton.startAnimation(zoom2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




