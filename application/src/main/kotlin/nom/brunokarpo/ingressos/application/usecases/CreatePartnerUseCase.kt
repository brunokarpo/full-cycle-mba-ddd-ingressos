package nom.brunokarpo.ingressos.application.usecases

import nom.brunokarpo.ingressos.application.dto.PartnerDTO
import nom.brunokarpo.ingressos.domain.common.AggregateRootNotifier
import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.repository.PartnerRepository

class CreatePartnerUseCase(
	private val partnerRepository: PartnerRepository,
	private val aggregateRootNotifier: AggregateRootNotifier
) {

	fun createPartner(name: String, cnpj: String): PartnerDTO {
		val partner = Partner.create(name = name, cnpj = cnpj)

		partnerRepository.save(partner)
		aggregateRootNotifier.notify(partner)

		return PartnerDTO(partner)
	}
}