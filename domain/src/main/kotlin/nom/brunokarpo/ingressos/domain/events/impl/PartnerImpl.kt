package nom.brunokarpo.ingressos.domain.events.impl

import nom.brunokarpo.ingressos.domain.common.valueobjects.Cnpj
import nom.brunokarpo.ingressos.domain.events.Partner
import java.util.UUID

internal class PartnerImpl(
	override val id: UUID,
	override val name: String,
	override val cnpj: Cnpj
) : Partner {
}