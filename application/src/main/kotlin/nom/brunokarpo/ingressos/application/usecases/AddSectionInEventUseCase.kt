package nom.brunokarpo.ingressos.application.usecases

import nom.brunokarpo.ingressos.application.usecases.exceptions.EventDoesNotExistException
import nom.brunokarpo.ingressos.domain.common.AggregateRootPublisher
import nom.brunokarpo.ingressos.domain.events.repository.EventRepository
import java.util.UUID

class AddSectionInEventUseCase(
	private val eventRepository: EventRepository,
	private val aggregateRootPublisher: AggregateRootPublisher
) {

	fun execute(eventId: UUID, sectionName: String, numberOfSpots: Int) {
		val event = eventRepository.ofId(eventId) ?: throw EventDoesNotExistException(eventId)
		event.addSection(sectionName, numberOfSpots)
		eventRepository.save(event)
		aggregateRootPublisher.publish(event)
	}
}