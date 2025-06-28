package nom.brunokarpo.ingressos.infra.api

import nom.brunokarpo.ingressos.infra.api.dto.AddEventSectiontDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/events")
interface EventsRouter {

	@PostMapping("/{eventId}/sections")
	fun addEventSection(
		@PathVariable("eventId") eventId: UUID,
		@RequestBody addEventSpotDto: AddEventSectiontDto
	): ResponseEntity<Unit>
}