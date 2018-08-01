package uniba.neoclassifier.utility;

import android.graphics.Bitmap;

import java.util.Random;

public class NEOClassifier {
    public static boolean classifyNeo(Bitmap photo) {
        Random rand = new Random();
        return rand.nextBoolean();
    }
}
