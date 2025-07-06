package nom.brunokarpo.ingressos.domain.common

interface AggregateRootPublisher {

	fun publish(aggregateRoot: AggregateRoot) {
		aggregateRoot.events.forEach { event -> this.handleEvent(event) }
	}

	fun <D: DomainEvent> handleEvent(event: D)

}