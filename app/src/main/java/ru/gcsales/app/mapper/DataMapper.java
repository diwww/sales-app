package ru.gcsales.app.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Maxim Surovtsev
 * Created on 8/5/18
 */
// TODO: move to another package
public abstract class DataMapper<I, O> {

    public abstract O transform(I input);

    /**
     * Transforms a list of entities.
     */
    public List<O> transform(List<I> input) {
        List<O> output;

        if (input != null && !input.isEmpty()) {
            output = new ArrayList<>();
            for (I i : input) {
                output.add(transform(i));
            }
        } else {
            return Collections.emptyList();
        }

        return output;
    }
}
