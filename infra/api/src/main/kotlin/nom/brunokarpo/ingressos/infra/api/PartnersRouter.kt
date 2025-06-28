package nom.brunokarpo.ingressos.infra.api

import nom.brunokarpo.ingressos.application.dto.PartnerDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/partners")
interface PartnersRouter {

	@PostMapping
	fun createPartner(
		@RequestBody partnerDTO: PartnerDTO
	): ResponseEntity<PartnerDTO>
}