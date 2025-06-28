package nom.brunokarpo.ingressos.domain.common

import java.util.UUID

interface Repository<A: AggregateRoot> {
	fun save(aggregate: A)
	fun ofId(id: UUID): A?
}