package ru.gcsales.app.presentation;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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
}
