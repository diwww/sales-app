package ru.gcsales.app.presentation.model;

/**
 * Class which indicates that progress bar is inserted
 * into recycler view.
 *
 * @author Maxim Surovtsev
 * Created on 8/18/18
 */
public class ProgressViewModel implements BaseItem {

    public static final int TYPE = 2;

    @Override
    public int getType() {
        return TYPE;
    }
}
