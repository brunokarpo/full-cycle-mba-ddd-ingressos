package nom.brunokarpo.ingressos.infra.api.controllers

import nom.brunokarpo.ingressos.application.dto.EventDTO
import nom.brunokarpo.ingressos.application.dto.PartnerDTO
import nom.brunokarpo.ingressos.application.events.CreateNewEventUseCase
import nom.brunokarpo.ingressos.application.events.CreatePartnerUseCase
import nom.brunokarpo.ingressos.infra.api.PartnersRouter
import nom.brunokarpo.ingressos.infra.api.dto.CreateEventDto
import nom.brunokarpo.ingressos.infra.api.dto.CreatePartnerDto
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.net.URI
import java.util.UUID

@Component
class PartnersController(
	private val createPartnerUseCase: CreatePartnerUseCase,
	private val createNewEventUseCase: CreateNewEventUseCase
) : PartnersRouter {

	override fun createPartner(partnerDTO: CreatePartnerDto): ResponseEntity<PartnerDTO> {
		val partner = createPartnerUseCase.createPartner(partnerDTO.name, partnerDTO.cnpj)
		return ResponseEntity.created(
			URI.create("/api/v1/partners/${partner.id}")
		).body(partner)
	}

	override fun createEvent(
		partnerId: UUID,
		eventDTO: CreateEventDto
	): ResponseEntity<EventDTO> {
		val event = createNewEventUseCase.execute(partnerId, eventDTO.name, eventDTO.description, eventDTO.date)
		return ResponseEntity.created(
			URI.create("/api/v1/partners/${partnerId}/events/${event.id}")
		).body(event)
	}
}