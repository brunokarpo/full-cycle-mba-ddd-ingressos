package nom.brunokarpo.ingressos.domain.common

abstract class AggregateRoot : Entity {
	val events: MutableList<DomainEvent> = mutableListOf()

	protected fun recordEvent(event: DomainEvent) {
		this.events.add(event)
	}
}