package nom.brunokarpo.ingressos.application.listeners

import nom.brunokarpo.ingressos.domain.common.DomainEventListener
import nom.brunokarpo.ingressos.domain.events.domainevents.PartnerCreated
import nom.brunokarpo.ingressos.domain.events.integrationevents.PartnerCreatedEventPublisher

class PartnerCreatedListener(
	private val partnerCreatedEventPublisher: PartnerCreatedEventPublisher
): DomainEventListener<PartnerCreated> {

	override fun onEvent(event: PartnerCreated) {
		partnerCreatedEventPublisher.publish(event)
	}
}