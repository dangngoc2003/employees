package com.example.springinit;

import java.util.List;

public interface ICrudService<E> {
    List<E> findAll();
    E findById(Integer id);
    void save(E e);
    void update(E e);
    void  deleteById(Integer id);

}
