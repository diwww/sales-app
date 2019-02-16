package ru.gcsales.app.presentation;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import ru.gcsales.app.data.model.remote.ItemResponse;

/**
 * Utility methods class
 *
 * @author Maxim Surovtsev
 * @since 07/01/2019
 */
public class Utils {

    /**
     * Sets the image to the image view via {@link Glide}
     *
     * @param context       context
     * @param imageUrl      url of the image
     * @param placeholderId placeholder drawable resource
     * @param imageView     image view
     */
    public static void setGlideImage(Context context, String imageUrl, @DrawableRes int placeholderId, ImageView imageView) {
        Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions().placeholder(placeholderId))
                .load(imageUrl)
                .into(imageView);
    }

    /**
     * Parses JSON list from input stream
     *
     * @param inputStream input stream with json string
     * @param clazz       {@link Class} class of list elements
     * @param <T>         type of list elements
     * @return parsed JSON list
     */
    public static <T> List<T> parseJsonList(InputStream inputStream, Class<T> clazz) throws IOException {
        String json = jsonFromStream(inputStream);

        Gson gson = new Gson();
        Type type = TypeToken.getParameterized(ArrayList.class, clazz).getType();

        return gson.fromJson(json, type);
    }

    /**
     * Parses JSON from input stream
     *
     * @param inputStream input stream with JSON string
     * @param clazz       {@link Class} class of object
     * @param <T>         type of object
     * @return parsed JSON object
     */
    public static <T extends ItemResponse> T parseJson(InputStream inputStream, Class<T> clazz) throws IOException {
        String json = jsonFromStream(inputStream);

        Gson gson = new Gson();
        Type type = TypeToken.get(clazz).getType();

        return gson.fromJson(json, type);
    }

    private static String jsonFromStream(InputStream inputStream) throws IOException {
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        return new String(buffer, StandardCharsets.UTF_8);
    }
}
