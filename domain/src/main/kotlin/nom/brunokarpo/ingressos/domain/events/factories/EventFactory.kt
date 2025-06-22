package nom.brunokarpo.ingressos.domain.events.factories

import nom.brunokarpo.ingressos.domain.events.Event
import nom.brunokarpo.ingressos.domain.events.commands.CreateEventCommand
import nom.brunokarpo.ingressos.domain.events.impl.EventImpl
import java.util.UUID

object EventFactory {

	fun create(createEventCommand: CreateEventCommand, partnerId: UUID): Event {
		return EventImpl(
			id = UUID.randomUUID(),
			name = createEventCommand.name,
			description = createEventCommand.description,
			date = createEventCommand.date,
			partnerId = partnerId
		)
	}
}