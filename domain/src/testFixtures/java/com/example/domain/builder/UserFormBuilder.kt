package com.example.domain.builder

import com.example.domain.auth.model.UserForm

class UserFormBuilder {
	private var usuario: String = "testuser"
	private var password: String = "testpass"
	private var nomAplicacion: String = "Controller APP"

	fun withUsuario(usuario: String) = apply { this.usuario = usuario }

	fun withPassword(password: String) = apply { this.password = password }

	fun withNomAplicacion(nomAplicacion: String) = apply { this.nomAplicacion = nomAplicacion }

	fun build() =
		UserForm(
			usuario = usuario,
			password = password,
			nomAplicacion = nomAplicacion,
		)
}

fun userForm(block: UserFormBuilder.() -> Unit = {}) = UserFormBuilder().apply(block).build()
