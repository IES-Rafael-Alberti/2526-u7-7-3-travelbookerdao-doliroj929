package es.iesra.servicio

import es.iesra.datos.IReservaRepository
import es.iesra.dominio.*

class ReservaService(private val repositorio: IReservaRepository) : IReservaService {

    override fun crearReservaVuelo(descripcion: String, origen: String, destino: String, horaVuelo: String) {
        try {
            val reservaVuelo = ReservaVuelo.creaInstancia(descripcion, origen, destino, horaVuelo)
            repositorio.agregar(reservaVuelo)
        } catch (e: Exception) {
            println("Error al crear vuelo: ${e.message}")
        }
    }

    override fun crearReservaHotel(descripcion: String, ubicacion: String, numeroNoches: Int) {
        try {
            val reservaHotel = ReservaHotel.creaInstancia(descripcion, ubicacion, numeroNoches)
            repositorio.agregar(reservaHotel)
        } catch (e: Exception) {
            println("Error al crear hotel: ${e.message}")
        }
    }

    override fun listarReservas(): List<Reserva> = repositorio.obtenerTodas()
}