package es.iesra.dao

interface IDao<T, K> {
    fun crear(entidad: T): Boolean
    fun obtenerTodos(): List<T>
    fun obtenerPorId(id: K): T?
    fun actualizar(entidad: T): Boolean
    fun eliminar(id: K): Boolean
}