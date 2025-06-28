package nom.brunokarpo.ingressos.domain.events.domainevents

import nom.brunokarpo.ingressos.domain.common.DomainEvent
import java.time.ZonedDateTime
import java.util.UUID

class SectionAdded(eventId: UUID, sectionName: String, numberOfSpot: Int) : DomainEvent {
	override val aggregateId: UUID = eventId
	override val version: Long = 1L
	override val occurredOn: ZonedDateTime = ZonedDateTime.now()
	val name = sectionName
	val numberOfSpots = numberOfSpot
}