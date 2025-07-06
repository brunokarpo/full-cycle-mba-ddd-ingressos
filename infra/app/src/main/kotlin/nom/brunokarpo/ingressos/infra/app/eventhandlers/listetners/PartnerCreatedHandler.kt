package nom.brunokarpo.ingressos.infra.app.eventhandlers.listetners

import nom.brunokarpo.ingressos.application.listeners.PartnerCreatedListener
import nom.brunokarpo.ingressos.domain.events.domainevents.PartnerCreated
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class PartnerCreatedHandler(
	private val partnerCreatedListener: PartnerCreatedListener
) {

	@EventListener
	fun onPartnerCreated(partnerCreated: PartnerCreated) {
		partnerCreatedListener.onEvent(partnerCreated)
	}
}