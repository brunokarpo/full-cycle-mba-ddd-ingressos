package nom.brunokarpo.ingressos.infra.app.eventhandlers

import nom.brunokarpo.ingressos.domain.common.AggregateRootPublisher
import nom.brunokarpo.ingressos.domain.common.DomainEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class AggregateRootApplicationEventPublisher(
	private val applicationEvent: ApplicationEventPublisher
) : AggregateRootPublisher {

	override fun <D : DomainEvent> handleEvent(event: D) {
		applicationEvent.publishEvent(event)
	}
}