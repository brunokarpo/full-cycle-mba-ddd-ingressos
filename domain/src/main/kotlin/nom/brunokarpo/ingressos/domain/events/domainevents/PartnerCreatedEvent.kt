package nom.brunokarpo.ingressos.domain.events.domainevents

import nom.brunokarpo.ingressos.domain.common.DomainEvent
import nom.brunokarpo.ingressos.domain.events.Partner
import java.time.ZonedDateTime

class PartnerCreatedEvent(
	partner: Partner
) : DomainEvent {

	override val aggregateId = partner.id
	override val version = 1L
	override val occurredOn: ZonedDateTime = ZonedDateTime.now()
	val name = partner.name
	val cnpj = partner.cnpj.value
}