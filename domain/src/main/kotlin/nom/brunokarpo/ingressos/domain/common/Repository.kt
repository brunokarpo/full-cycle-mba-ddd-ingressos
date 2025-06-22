package nom.brunokarpo.ingressos.domain.common

import java.util.UUID

interface Repository<A: Aggregate> {
	fun save(aggregate: A)
}