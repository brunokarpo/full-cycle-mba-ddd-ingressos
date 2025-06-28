package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.common.AggregateRoot
import nom.brunokarpo.ingressos.domain.common.valueobjects.Cnpj
import nom.brunokarpo.ingressos.domain.events.commands.CreateEventCommand
import nom.brunokarpo.ingressos.domain.events.domainevents.PartnerCreatedEvent
import nom.brunokarpo.ingressos.domain.events.factories.EventFactory
import java.util.UUID

class Partner(
	override val id: UUID,
	val name: String,
	val cnpj: Cnpj
) : AggregateRoot() {

	companion object {
		fun create(id: UUID = UUID.randomUUID(), name: String, cnpj: String): Partner {
			val partnerImpl = Partner(id, name, Cnpj(cnpj))
			val event = PartnerCreatedEvent(partnerImpl)
			partnerImpl.recordEvent(event)
			return partnerImpl
		}
	}

	fun createEvent(createEventCommand: CreateEventCommand): Event {
		val event = EventFactory.create(createEventCommand, this.id)
		return event
	}
}
