package nom.brunokarpo.ingressos.domain.fixtures

import nom.brunokarpo.ingressos.domain.events.Partner

object PartnerFixture {

	fun create(): Partner {
		return Partner.create(name = "Test", cnpj =  "00000000000000")
	}
}