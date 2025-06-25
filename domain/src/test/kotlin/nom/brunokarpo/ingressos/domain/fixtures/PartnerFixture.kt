package nom.brunokarpo.ingressos.domain.fixtures

import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.factories.PartnerFactory

object PartnerFixture {

	fun create(): Partner {
		return PartnerFactory.create(name = "Test", cnpj =  "00000000000000")
	}
}