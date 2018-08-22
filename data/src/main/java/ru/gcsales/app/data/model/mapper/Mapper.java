package ru.gcsales.app.data.model.mapper;

import java.util.List;

/**
 * Base mapper interface.
 *
 * @param <R> network response
 * @param <E> db entity
 * @param <D> domain model
 * @author Maxim Surovtsev
 * Created on 8/22/18
 */
public interface Mapper<R, E, D> {

    E responseToEntity(R response);

    List<E> responseToEntity(List<R> responses);

    D entityToDomain(E entity);

    List<D> entityToDomain(List<E> entities);
}
