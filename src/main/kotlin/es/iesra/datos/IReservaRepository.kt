package es.iesra.datos

import es.iesra.dominio.Reserva

interface IReservaRepository {
    fun agregar(reserva: Reserva): Boolean
    fun obtenerTodas(): List<Reserva>
}