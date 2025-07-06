package nom.brunokarpo.ingressos.application.usecases

import nom.brunokarpo.ingressos.application.dto.PartnerDTO
import nom.brunokarpo.ingressos.domain.common.valueobjects.AggregateRootPublisher
import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.repository.PartnerRepository

class CreatePartnerUseCase(
	private val partnerRepository: PartnerRepository,
	private val aggregateRootPublisher: AggregateRootPublisher
) {

	fun createPartner(name: String, cnpj: String): PartnerDTO {
		val partner = Partner.create(name = name, cnpj = cnpj)

		partnerRepository.save(partner)
		aggregateRootPublisher.publish(partner)

		return PartnerDTO(partner)
	}
}