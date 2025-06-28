package nom.brunokarpo.ingressos.domain.common

import java.time.ZonedDateTime
import java.util.UUID

interface DomainEvent {
	val aggregateId: UUID
	val version: Long
	val occurredOn: ZonedDateTime
}