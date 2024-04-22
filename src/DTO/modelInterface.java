package DTO;

import java.util.ArrayList;

@SuppressWarnings("unused")
public interface modelInterface<T> {

    public void insert(T t);

    public void delete(int row);

    public void update(T t, int id);

    public T findByID(int id);
}
