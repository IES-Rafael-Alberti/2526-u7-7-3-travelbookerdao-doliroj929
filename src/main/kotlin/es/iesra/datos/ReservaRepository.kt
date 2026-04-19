package es.iesra.datos

import es.iesra.dao.IDao
import es.iesra.dominio.*

class ReservaRepository(
    private val daoVuelo: IDao<ReservaVuelo, Int>,
    private val daoHotel: IDao<ReservaHotel, Int>
) : IReservaRepository {

    override fun agregar(reserva: Reserva): Boolean {
        return when (reserva) {
            is ReservaVuelo -> daoVuelo.crear(reserva)
            is ReservaHotel -> daoHotel.crear(reserva)
            else -> false
        }
    }

    override fun obtenerTodas(): List<Reserva> {
        // Combina los datos de ambos ficheros CSV
        return daoVuelo.obtenerTodos() + daoHotel.obtenerTodos()
    }
}