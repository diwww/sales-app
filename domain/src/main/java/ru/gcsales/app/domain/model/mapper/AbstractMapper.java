package ru.gcsales.app.domain.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract mapper.
 *
 * @param <I> input data type
 * @param <O> output data type
 * @param <P> optional params type
 * @author Maxim Surovtsev
 * Created on 8/28/18
 */
public abstract class AbstractMapper<I, O, P> {

    /**
     * Transforms a single object
     *
     * @param input  input object
     * @param params optional params
     * @return transformed object
     */
    public abstract O transform(I input, P params);

    /**
     * Transforms a list of objects.
     *
     * @param inputList list of input objects
     * @param params    optional params
     * @return transformed list
     */
    public List<O> transform(List<I> inputList, P params) {
        List<O> outputList;

        if (inputList != null && !inputList.isEmpty()) {
            outputList = new ArrayList<>();
            for (I i : inputList) {
                outputList.add(transform(i, params));
            }
        } else {
            return Collections.emptyList();
        }

        return outputList;
    }
}
