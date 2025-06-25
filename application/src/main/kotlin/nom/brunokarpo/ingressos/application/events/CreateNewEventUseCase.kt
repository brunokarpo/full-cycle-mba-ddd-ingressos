package nom.brunokarpo.ingressos.application.events

import nom.brunokarpo.ingressos.application.commons.UseCase
import nom.brunokarpo.ingressos.application.dto.EventDTO
import nom.brunokarpo.ingressos.application.events.exceptions.PartnerDoesNotExistsException
import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.commands.CreateEventCommand
import nom.brunokarpo.ingressos.domain.events.repository.PartnerRepository
import java.time.ZonedDateTime
import java.util.UUID

class CreateNewEventUseCase(
	private val partnerRepository: PartnerRepository,
) : UseCase<Partner> {
	fun execute(partnerId: UUID, eventName: String, eventDescription: String, eventDate: ZonedDateTime): EventDTO {

		val partner = partnerRepository.ofId(partnerId) ?: throw PartnerDoesNotExistsException(partnerId)

		val createEventCommand = CreateEventCommand(
			name = eventName,
			description = eventDescription,
			date = eventDate,
		)

		val event = partner.createEvent(
			createEventCommand
		)

		return EventDTO(event)
	}
}