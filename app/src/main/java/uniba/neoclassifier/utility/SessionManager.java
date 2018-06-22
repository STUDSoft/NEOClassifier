package uniba.neoclassifier.utility;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import uniba.neoclassifier.gui.CreateUserActivity;

public class SessionManager {

    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Sharedpref file name
    private static final String PREF_NAME = "userLogged";

    // All Shared Preferences Keys
    private static final String IS_LOGGED = "IsLoggedIn";

    public static final String NAME = " name";
    public static final String SURNAME = "surname";
    public static final String EMAIL = "email";
    public static final String BORN_DATE = "bornDate";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String name, String surname, String bornDate, String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGGED, true);

        editor.putString(NAME, name);
        editor.putString(SURNAME, surname);
        editor.putString(EMAIL,email);
        editor.putString(BORN_DATE,bornDate);

        // commit changes
        editor.commit();
    }

    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        user.put(NAME, pref.getString(NAME, null));
        user.put(SURNAME, pref.getString(SURNAME, null));
        user.put(BORN_DATE, pref.getString(BORN_DATE, null));
        user.put(EMAIL, pref.getString(EMAIL, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        editor.putBoolean(IS_LOGGED, false);
        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, CreateUserActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        //_context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGGED, false);
    }
}
