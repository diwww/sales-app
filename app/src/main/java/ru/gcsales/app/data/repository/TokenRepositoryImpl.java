package ru.gcsales.app.data.repository;

import android.content.Context;
import android.content.SharedPreferences;

import ru.gcsales.app.domain.repository.TokenRepository;


/**
 * @author Maxim Surovtsev
 * Created on 8/7/18
 */
public class TokenRepositoryImpl implements TokenRepository {

    private static final String PREFERENCE_FILE_KEY = "ru.gcsales.app.PREFERENCE_FILE_KEY";
    public static final String KEY = "ru.gcsales.app.TOKEN_KEY";

    private Context mContext;

    public TokenRepositoryImpl(Context context) {
        mContext = context;
    }

    public void setToken(String token) {
        SharedPreferences prefs = mContext.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY, token);
        editor.commit();
    }

    public String getToken() {
        SharedPreferences prefs = mContext.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        return prefs.getString(KEY, null);
    }

    public void removeToken() {
        SharedPreferences prefs = mContext.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(KEY);
        editor.commit();
    }
}
