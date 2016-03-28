package com.bruyako.server.repository;

import org.hibernate.SessionFactory;
import java.util.List;

/**
 * Created by brunyatko on 24.03.16.
 */
public abstract class BaseRepository <T> {

    protected SessionFactory sessionFactory;

    public abstract T getById(int id);

    public abstract List<T> getAll();

}
