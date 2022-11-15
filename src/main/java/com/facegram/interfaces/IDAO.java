package com.facegram.interfaces;

import java.util.List;

public interface IDAO<T,K> {

    /**
     * Insertar un objeto en la base de datos
     * @return True o false si se realizó con exito o no
     */
    boolean insert();

    /**
     * Obtiene un objeto de la base de datos
     * @param id Id del objeto a buscar
     * @return Objeto encontrado o null si no existe
     */
    T get(K id);

    /**
     * Obtiene una coleccion de objetos encontrados en la base de datos
     * @return Coleccion de objetos
     */
    List<T> getAll();

    /**
     * Actualiza los datos de un objeto existente en la base de datos
     * @return 1 o 0 si se realizó con exito o no
     */
    int update();

    /**
     * Elimina un objeto de la base de datos
     * @return 1 o 0 si se realizó con exito o no
     */
    int delete();
}
