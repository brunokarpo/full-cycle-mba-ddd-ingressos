package nom.brunokarpo.ingressos.domain.common.valueobjects

import nom.brunokarpo.ingressos.domain.common.AggregateRoot
import nom.brunokarpo.ingressos.domain.common.DomainEvent

abstract class AggregateRootPublisher {

	fun publish(aggregateRoot: AggregateRoot) {
		aggregateRoot.events.forEach { event -> this.handleEvent(event) }
	}

	abstract fun handleEvent(event: DomainEvent)

}