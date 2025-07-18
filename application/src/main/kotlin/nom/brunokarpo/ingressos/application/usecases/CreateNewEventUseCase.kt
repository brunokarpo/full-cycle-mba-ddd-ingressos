package nom.brunokarpo.ingressos.application.usecases

import nom.brunokarpo.ingressos.application.dto.EventDTO
import nom.brunokarpo.ingressos.application.usecases.exceptions.PartnerDoesNotExistsException
import nom.brunokarpo.ingressos.domain.common.AggregateRootNotifier
import nom.brunokarpo.ingressos.domain.events.commands.CreateEventCommand
import nom.brunokarpo.ingressos.domain.events.repository.EventRepository
import nom.brunokarpo.ingressos.domain.events.repository.PartnerRepository
import java.time.ZonedDateTime
import java.util.UUID

class CreateNewEventUseCase(
	private val partnerRepository: PartnerRepository,
	private val eventRepository: EventRepository,
	private val aggregateRootNotifier: AggregateRootNotifier
) {
	fun execute(partnerId: UUID, eventName: String, eventDescription: String, eventDate: ZonedDateTime): EventDTO {

		val partner = partnerRepository.ofId(partnerId) ?: throw PartnerDoesNotExistsException(partnerId)

		val createEventCommand = CreateEventCommand.builder()
			.withName(eventName)
			.withDescription(eventDescription)
			.withDate(eventDate)
			.build()

		val event = partner.createEvent(
			createEventCommand
		)

		eventRepository.save(event)
		aggregateRootNotifier.notify(event)

		return EventDTO(event)
	}
}