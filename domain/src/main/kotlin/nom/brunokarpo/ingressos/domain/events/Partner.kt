package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.common.AggregateRoot
import nom.brunokarpo.ingressos.domain.common.valueobjects.Cnpj
import nom.brunokarpo.ingressos.domain.events.commands.CreateEventCommand
import nom.brunokarpo.ingressos.domain.events.domainevents.PartnerCreated
import java.util.UUID

class Partner(
	override val id: UUID,
	val name: String,
	val cnpj: Cnpj
) : AggregateRoot() {

	companion object {
		fun create(id: UUID = UUID.randomUUID(), name: String, cnpj: String): Partner {
			val partner = Partner(id, name, Cnpj(cnpj))
			val event = PartnerCreated(partner)
			partner.recordEvent(event)
			return partner
		}
	}

	fun createEvent(createEventCommand: CreateEventCommand): Event {
		val event = Event.create(createEventCommand, this.id)
		return event
	}
}
