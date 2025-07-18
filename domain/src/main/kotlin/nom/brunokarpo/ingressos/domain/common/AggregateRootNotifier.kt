package nom.brunokarpo.ingressos.domain.common

interface AggregateRootNotifier {

	fun notify(aggregateRoot: AggregateRoot) {
		aggregateRoot.events.forEach { event -> this.handleEvent(event) }
	}

	fun <D: DomainEvent> handleEvent(event: D)

}