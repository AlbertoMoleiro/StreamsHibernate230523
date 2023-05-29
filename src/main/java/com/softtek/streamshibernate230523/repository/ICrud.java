package com.softtek.streamshibernate230523.repository;

import java.util.List;

public interface ICrud<T,ID> {
    T registrar(T entity) throws Exception;
    T modificar(T entity) throws Exception;
    List<T> listar() throws Exception;
    T listarPorId(ID id) throws Exception;
    void eliminar(T id) throws Exception;

}
