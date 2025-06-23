package nom.brunokarpo.ingressos.application.events

import nom.brunokarpo.ingressos.application.commons.AggregateService
import nom.brunokarpo.ingressos.application.dto.PartnerDTO
import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.factories.PartnerFactory
import nom.brunokarpo.ingressos.domain.events.repository.PartnerRepository

class PartnerService(
	private val partnerRepository: PartnerRepository
) : AggregateService<Partner> {

	fun createPartner(name: String, cnpj: String): PartnerDTO {
		val partner = PartnerFactory.create(name, cnpj)

		partnerRepository.save(partner)

		return PartnerDTO(partner)
	}
}