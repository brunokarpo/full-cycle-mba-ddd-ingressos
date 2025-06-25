package nom.brunokarpo.ingressos.domain.events.factories

import nom.brunokarpo.ingressos.domain.common.valueobjects.Cnpj
import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.impl.PartnerImpl
import java.util.UUID

object PartnerFactory {

	fun create(
		id: UUID = UUID.randomUUID(),
		name: String,
		cnpj: String): Partner {
		return PartnerImpl(
			id = id,
			name = name,
			cnpj = Cnpj(cnpj)
		)
	}
}