package nom.brunokarpo.ingressos.infra.api.controllers

import nom.brunokarpo.ingressos.application.events.AddSectionInEventUseCase
import nom.brunokarpo.ingressos.infra.api.EventsRouter
import nom.brunokarpo.ingressos.infra.api.dto.AddEventSectiontDto
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class EventsController(
	private val addEventSectionInEventUseCase: AddSectionInEventUseCase
) : EventsRouter {

	override fun addEventSection(
		eventId: UUID,
		addEventSpotDto: AddEventSectiontDto
	): ResponseEntity<Unit> {
		addEventSectionInEventUseCase.execute(
			eventId = eventId,
			sectionName = addEventSpotDto.name,
			numberOfSpots = addEventSpotDto.numberOfSpots
		)
		return ResponseEntity.ok().build()
	}
}