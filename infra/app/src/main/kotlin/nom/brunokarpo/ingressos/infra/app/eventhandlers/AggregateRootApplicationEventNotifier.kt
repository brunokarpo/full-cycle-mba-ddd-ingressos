package nom.brunokarpo.ingressos.infra.app.eventhandlers

import nom.brunokarpo.ingressos.domain.common.AggregateRootNotifier
import nom.brunokarpo.ingressos.domain.common.DomainEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class AggregateRootApplicationEventNotifier(
	private val applicationEvent: ApplicationEventPublisher
) : AggregateRootNotifier {

	override fun <D : DomainEvent> handleEvent(event: D) {
		applicationEvent.publishEvent(event)
	}
}