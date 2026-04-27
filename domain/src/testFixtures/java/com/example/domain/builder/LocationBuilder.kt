package com.example.domain.builder

import com.example.domain.location.model.Location

class LocationBuilder {
	private var idLocalidad: String = "1"
	private var nombre: String? = "Test Location"
	private var nombreCorto: String? = "TL"
	private var nombreZona: String? = "Zone 1"
	private var codigoPostal: String? = "110001"
	private var nombreCompleto: String? = "Test Location Completo"
	private var abreviacionCiudad: String? = "TC"

	fun withIdLocalidad(idLocalidad: String) = apply { this.idLocalidad = idLocalidad }

	fun withNombre(nombre: String?) = apply { this.nombre = nombre }

	fun withNombreCorto(nombreCorto: String?) = apply { this.nombreCorto = nombreCorto }

	fun withNombreZona(nombreZona: String?) = apply { this.nombreZona = nombreZona }

	fun withCodigoPostal(codigoPostal: String?) = apply { this.codigoPostal = codigoPostal }

	fun withNombreCompleto(nombreCompleto: String?) = apply { this.nombreCompleto = nombreCompleto }

	fun withAbreviacionCiudad(abreviacionCiudad: String?) = apply { this.abreviacionCiudad = abreviacionCiudad }

	fun build() =
		Location(
			idLocalidad = idLocalidad,
			nombre = nombre,
			nombreCorto = nombreCorto,
			nombreZona = nombreZona,
			codigoPostal = codigoPostal,
			nombreCompleto = nombreCompleto,
			abreviacionCiudad = abreviacionCiudad,
		)
}

fun location(block: LocationBuilder.() -> Unit = {}) = LocationBuilder().apply(block).build()
