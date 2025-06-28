package nom.brunokarpo.ingressos.application.events

import nom.brunokarpo.ingressos.application.commons.UseCase
import nom.brunokarpo.ingressos.application.dto.PartnerDTO
import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.factories.PartnerFactory
import nom.brunokarpo.ingressos.domain.events.repository.PartnerRepository

class CreatePartnerUseCase(
	private val partnerRepository: PartnerRepository
) : UseCase<Partner> {

	fun createPartner(name: String, cnpj: String): PartnerDTO {
		val partner = PartnerFactory.create(name = name, cnpj = cnpj)

		partnerRepository.save(partner)

		return PartnerDTO(partner)
	}
}