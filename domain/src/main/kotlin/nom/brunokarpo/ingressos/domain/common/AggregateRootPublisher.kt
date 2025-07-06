package nom.brunokarpo.ingressos.domain.common

fun interface AggregateRootPublisher {

	fun publish(aggregateRoot: AggregateRoot) {
		aggregateRoot.events.forEach { event -> this.handleEvent(event) }
	}

	fun handleEvent(event: DomainEvent)

}