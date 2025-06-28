package nom.brunokarpo.ingressos.domain.common.valueobjects

import nom.brunokarpo.ingressos.domain.common.Entity

interface EntityAsValue<E : Entity> {
	fun asEntity(): E
}