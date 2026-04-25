package com.example.core.time

import java.time.Instant

interface Clock {
	fun now(): Instant
}
