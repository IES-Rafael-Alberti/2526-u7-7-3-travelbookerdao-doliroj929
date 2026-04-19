package es.iesra.dao

import java.io.File
import java.io.IOException

class ReservaDaoFichero<T>(
    private val rutaArchivo: String,
    private val cabecera: String,
    private val obtenerId: (T) -> Int,
    private val deObjetoATexto: (T) -> String,
    private val deTextoAObjeto: (String) -> T?
) : IDao<T, Int> {

    private val archivo = File(rutaArchivo)

    override fun crear(entidad: T): Boolean = try {
        archivo.appendText(deObjetoATexto(entidad) + "\n")
        true
    } catch (e: IOException) { false}

    override fun obtenerTodos(): List<T> = try {
        if (!archivo.exists()) emptyList()
        else archivo.readLines().drop(1).mapNotNull { deTextoAObjeto(it) }
    } catch (e: IOException) { emptyList() }

    override fun obtenerPorId(id: Int): T? = obtenerTodos().find { obtenerId(it) == id }

    override fun eliminar(id: Int): Boolean {
        val lista = obtenerTodos()
        val filtrados = lista.filter { obtenerId(it) != id }
        return if (lista.size != filtrados.size) sobrescribir(filtrados) else false
    }

    override fun actualizar(entidad: T): Boolean {
        val lista = obtenerTodos().toMutableList()
        val index = lista.indexOfFirst { obtenerId(it) == obtenerId(entidad) }
        return if (index != -1) {
            lista[index] = entidad
            sobrescribir(lista)
        } else false
    }

    private fun sobrescribir(lista: List<T>): Boolean = try {
        val contenido = cabecera + "\n" + lista.joinToString("\n") { deObjetoATexto(it) } + "\n"
        archivo.writeText(contenido)
        true
    } catch (e: IOException) { false }
}