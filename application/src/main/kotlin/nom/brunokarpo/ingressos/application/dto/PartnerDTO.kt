package nom.brunokarpo.ingressos.application.dto

import nom.brunokarpo.ingressos.domain.events.Partner
import java.util.UUID

data class PartnerDTO(
	val id: UUID,
	val name: String,
	val cnpj: String
) {
	internal constructor(partner: Partner) : this(
		id = partner.id,
		name = partner.name,
		cnpj = partner.cnpj.value
	)
}