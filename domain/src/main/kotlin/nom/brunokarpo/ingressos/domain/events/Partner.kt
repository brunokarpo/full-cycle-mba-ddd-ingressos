package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.common.Aggregate
import nom.brunokarpo.ingressos.domain.common.valueobjects.Cnpj

interface Partner : Aggregate {
	val name: String
	val cnpj: Cnpj
}