package nom.brunokarpo.ingressos.application.events

import nom.brunokarpo.ingressos.application.commons.UseCase
import nom.brunokarpo.ingressos.application.events.exceptions.EventDoesNotExistException
import nom.brunokarpo.ingressos.domain.events.Event
import nom.brunokarpo.ingressos.domain.events.repository.EventRepository
import java.util.UUID

class AddSectionInEventUseCase(
	private val eventRepository: EventRepository
): UseCase<Event> {

	fun execute(eventId: UUID, sectionName: String, numberOfSpots: Int) {
		val event = eventRepository.ofId(eventId) ?: throw EventDoesNotExistException(eventId)
		event.addSection(sectionName, numberOfSpots)
		eventRepository.save(event)
	}
}