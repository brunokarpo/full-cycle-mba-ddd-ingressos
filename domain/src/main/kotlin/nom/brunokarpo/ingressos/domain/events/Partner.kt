package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.common.Aggregate
import nom.brunokarpo.ingressos.domain.common.valueobjects.Cnpj
import nom.brunokarpo.ingressos.domain.events.commands.CreateEventCommand

interface Partner : Aggregate {
	val name: String
	val cnpj: Cnpj

	fun createEvent(createEventCommand: CreateEventCommand): Event
}