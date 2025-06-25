package nom.brunokarpo.ingressos.application.dto

import nom.brunokarpo.ingressos.domain.events.Event
import java.time.ZonedDateTime
import java.util.UUID

data class EventDTO(
	val id: UUID,
	val name: String,
	val description: String,
	val date: ZonedDateTime,
	val partnerId: UUID,
) {

	internal constructor(event: Event) : this(
		id = event.id,
		name = event.name,
		description = event.description,
		date = event.date,
		partnerId = event.partnerId
	)
}