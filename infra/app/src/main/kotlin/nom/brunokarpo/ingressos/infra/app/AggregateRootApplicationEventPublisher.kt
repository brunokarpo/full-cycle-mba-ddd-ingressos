package nom.brunokarpo.ingressos.infra.app

import nom.brunokarpo.ingressos.domain.common.DomainEvent
import nom.brunokarpo.ingressos.domain.common.AggregateRootPublisher
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class AggregateRootApplicationEventPublisher(
	private val applicationEvent: ApplicationEventPublisher
) : AggregateRootPublisher {

	override fun handleEvent(event: DomainEvent) {
		applicationEvent.publishEvent(event)
	}
}