package nom.brunokarpo.ingressos.domain.common

interface DomainEventListener<in D: DomainEvent> {

	fun onEvent(event: D)
}