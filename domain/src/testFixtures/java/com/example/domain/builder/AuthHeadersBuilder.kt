package com.example.domain.builder

import com.example.domain.auth.model.AuthHeaders

class AuthHeadersBuilder {
	private var usuario: String = "pam.meredy21"
	private var idUsuario: String = "pam.meredy21"
	private var identificacion: String = "987204545"
	private var idCentroServicio: String = "1295"
	private var nombreCentroServicio: String = "PTO/BOGOTA/CUND/COL/OF PRINCIPAL - CRA 30 # 7-45"
	private var idAplicativoOrigen: String = "9"

	fun withUsuario(usuario: String) = apply { this.usuario = usuario }

	fun withIdUsuario(idUsuario: String) = apply { this.idUsuario = idUsuario }

	fun withIdentificacion(identificacion: String) = apply { this.identificacion = identificacion }

	fun withIdCentroServicio(idCentroServicio: String) = apply { this.idCentroServicio = idCentroServicio }

	fun withNombreCentroServicio(nombreCentroServicio: String) = apply { this.nombreCentroServicio = nombreCentroServicio }

	fun withIdAplicativoOrigen(idAplicativoOrigen: String) = apply { this.idAplicativoOrigen = idAplicativoOrigen }

	fun build() =
		AuthHeaders(
			usuario = usuario,
			idUsuario = idUsuario,
			identificacion = identificacion,
			idCentroServicio = idCentroServicio,
			nombreCentroServicio = nombreCentroServicio,
			idAplicativoOrigen = idAplicativoOrigen,
		)
}

fun authHeaders(block: AuthHeadersBuilder.() -> Unit = {}) = AuthHeadersBuilder().apply(block).build()
