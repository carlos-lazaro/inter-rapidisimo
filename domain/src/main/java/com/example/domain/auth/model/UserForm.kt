package com.example.domain.auth.model

data class UserForm(
	val usuario: String,
	val password: String,
	val nomAplicacion: String = "Controller APP",
)
