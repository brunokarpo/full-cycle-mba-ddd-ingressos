package nom.brunokarpo.ingressos.domain.common.valueobjects

import nom.brunokarpo.ingressos.domain.common.Entity

/*
 This interface aims to provide a way of expose the data of Entities without expose the Entities by themselves, to avoid anyone to interact with their domain methods.
 Only Aggregates could expose methods to external use, and only Aggregates can interact with methods of Entities.
 */
interface EntityAsValue<E : Entity> {
	fun asEntity(): E
}