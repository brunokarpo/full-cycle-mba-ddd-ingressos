package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.common.AggregateRoot
import nom.brunokarpo.ingressos.domain.common.valueobjects.Cnpj
import nom.brunokarpo.ingressos.domain.events.commands.CreateEventCommand

interface Partner : AggregateRoot {
	val name: String
	val cnpj: Cnpj

	fun createEvent(createEventCommand: CreateEventCommand): Event
}