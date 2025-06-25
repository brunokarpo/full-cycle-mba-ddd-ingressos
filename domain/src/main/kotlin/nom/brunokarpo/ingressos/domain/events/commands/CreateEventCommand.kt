package nom.brunokarpo.ingressos.domain.events.commands

import nom.brunokarpo.ingressos.domain.events.commands.builders.CreateEventCommandBuilder
import java.time.ZonedDateTime

data class CreateEventCommand(
	val name: String,
	val description: String,
	val date: ZonedDateTime
) {
	companion object {
		fun builder(): CreateEventCommandBuilder {
			return CreateEventCommandBuilder()
		}
	}
}
