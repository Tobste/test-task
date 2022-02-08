package com.test.task.dao;

import java.util.List;

public interface Dao<T, S> {

    List<T> findAllSortReverseTitle();

    List<S> findAllGroupAuthor();

    int save(T book);
}
