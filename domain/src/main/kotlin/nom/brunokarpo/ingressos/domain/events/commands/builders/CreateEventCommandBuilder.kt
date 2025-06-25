package nom.brunokarpo.ingressos.domain.events.commands.builders

import nom.brunokarpo.ingressos.domain.events.commands.CreateEventCommand
import java.time.ZonedDateTime

class CreateEventCommandBuilder {

	private lateinit var name: String
	private lateinit var description: String
	private lateinit var date: ZonedDateTime

	fun withName(name: String): CreateEventCommandBuilder {
		this.name = name
		return this
	}

	fun withDescription(description: String): CreateEventCommandBuilder {
		this.description = description
		return this
	}

	fun withDate(date: ZonedDateTime): CreateEventCommandBuilder {
		this.date = date
		return this
	}

	fun build(): CreateEventCommand {
		return CreateEventCommand(name, description, date)
	}

}