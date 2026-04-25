package com.example.domain.fake

import com.example.core.time.Clock
import java.time.Instant

class FakeClock(
	private var current: Instant = Instant.parse("2000-01-01T12:00:00Z"),
) : Clock {
	override fun now(): Instant = current

	fun setTime(time: Instant) {
		current = time
	}

	fun advanceTime(millis: Long) {
		current = current.plusMillis(millis)
	}
}
