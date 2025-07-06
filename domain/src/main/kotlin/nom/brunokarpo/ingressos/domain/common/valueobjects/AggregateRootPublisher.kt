package nom.brunokarpo.ingressos.domain.common.valueobjects

import nom.brunokarpo.ingressos.domain.common.AggregateRoot
import nom.brunokarpo.ingressos.domain.common.DomainEvent

fun interface AggregateRootPublisher {

	fun publish(aggregateRoot: AggregateRoot) {
		aggregateRoot.events.forEach { event -> this.handleEvent(event) }
	}

	fun handleEvent(event: DomainEvent)

}