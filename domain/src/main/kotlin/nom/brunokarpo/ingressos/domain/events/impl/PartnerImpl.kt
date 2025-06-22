package nom.brunokarpo.ingressos.domain.events.impl

import nom.brunokarpo.ingressos.domain.common.valueobjects.Cnpj
import nom.brunokarpo.ingressos.domain.events.Event
import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.commands.CreateEventCommand
import nom.brunokarpo.ingressos.domain.events.factories.EventFactory
import java.util.UUID

internal class PartnerImpl(
	override val id: UUID,
	override val name: String,
	override val cnpj: Cnpj
) : Partner {

	override fun createEvent(createEventCommand: CreateEventCommand): Event {
		val event = EventFactory.create(createEventCommand, this.id)
		return event
	}
}