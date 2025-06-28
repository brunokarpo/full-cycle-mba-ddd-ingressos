package nom.brunokarpo.ingressos.domain.common

internal abstract class AggregateRoot : Entity {
	val events: MutableSet<DomainEvent> = mutableSetOf()

	protected fun recordEvent(event: DomainEvent) {
		this.events.add(event)
	}

	fun clearEvents() {
		this.events.clear()
	}
}