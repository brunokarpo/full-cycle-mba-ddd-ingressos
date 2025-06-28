package nom.brunokarpo.ingressos.infra.api.rest

import nom.brunokarpo.ingressos.application.dto.PartnerDTO
import nom.brunokarpo.ingressos.application.events.CreatePartnerUseCase
import nom.brunokarpo.ingressos.infra.api.PartnersRouter
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.net.URI

@Component
class PartnersRestApi(
	private val createPartnerUseCase: CreatePartnerUseCase
) : PartnersRouter {

	override fun createPartner(partnerDTO: PartnerDTO): ResponseEntity<PartnerDTO> {
		val partner = createPartnerUseCase.createPartner(partnerDTO.name, partnerDTO.cnpj)
		return ResponseEntity.created(
			URI.create("/api/v1/partners/${partner.id}")
		).body(partner)
	}
}