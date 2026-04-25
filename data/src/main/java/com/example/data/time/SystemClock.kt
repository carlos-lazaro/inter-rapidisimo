package com.example.data.time

import com.example.core.time.Clock
import java.time.Instant
import javax.inject.Inject

class SystemClock
	@Inject
	constructor() : Clock {
		override fun now(): Instant = Instant.now()
	}
