package nom.brunokarpo.ingressos.domain.events.impl

import nom.brunokarpo.ingressos.domain.events.Event
import java.time.ZonedDateTime
import java.util.UUID

internal class EventImpl(
	override val name: String,
	override val description: String,
	override val date: ZonedDateTime,
	override val partnerId: UUID,
	override val id: UUID
) : Event{
}