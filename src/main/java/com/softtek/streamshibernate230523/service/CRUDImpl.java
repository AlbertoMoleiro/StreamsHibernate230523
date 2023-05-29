package com.softtek.streamshibernate230523.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class CRUDImpl<T,ID> {
    protected abstract JpaRepository<T, ID> getRepo();
    public  T registrar(T entity) throws Exception {
        return getRepo().save(entity);
    };
    public T modificar(T entity) throws Exception{
        return getRepo().save(entity);
    };

    public List<T> listar() throws Exception{
        return getRepo().findAll();
    };
    public T listarPorId(ID id) throws Exception{
        return getRepo().findById(id).orElse(null);
    };
    public void eliminar(T entity) throws Exception{
        getRepo().delete(entity);
    };
}
