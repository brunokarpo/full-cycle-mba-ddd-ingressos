package nom.brunokarpo.ingressos.infra.api

import nom.brunokarpo.ingressos.application.dto.EventDTO
import nom.brunokarpo.ingressos.application.dto.PartnerDTO
import nom.brunokarpo.ingressos.infra.api.dto.CreateEventDto
import nom.brunokarpo.ingressos.infra.api.dto.CreatePartnerDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/partners")
interface PartnersRouter {

	@PostMapping
	fun createPartner(
		@RequestBody partnerDTO: CreatePartnerDto
	): ResponseEntity<PartnerDTO>

	@PostMapping("/{partnerId}/events")
	fun createEvent(
		@PathVariable(name = "partnerId") partnerId: UUID,
		@RequestBody eventDTO: CreateEventDto
	): ResponseEntity<EventDTO>
}