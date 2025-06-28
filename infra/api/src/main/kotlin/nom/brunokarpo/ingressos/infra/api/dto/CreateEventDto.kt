package nom.brunokarpo.ingressos.infra.api.dto

import java.time.ZonedDateTime

data class CreateEventDto(
	val name: String,
	val description: String,
	val date: ZonedDateTime
)
