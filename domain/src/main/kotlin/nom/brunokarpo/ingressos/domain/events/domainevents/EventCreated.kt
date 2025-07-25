package nom.brunokarpo.ingressos.domain.events.domainevents

import nom.brunokarpo.ingressos.domain.common.DomainEvent
import nom.brunokarpo.ingressos.domain.events.Event
import java.time.ZonedDateTime
import java.util.UUID

class EventCreated(event: Event) : DomainEvent {

	override val aggregateId: UUID = event.id
	override val version: Long = 1L
	override val occurredOn: ZonedDateTime = ZonedDateTime.now()
	val name = event.name
	val description = event.description
	val date = event.date
	val partnerId = event.partnerId
}
