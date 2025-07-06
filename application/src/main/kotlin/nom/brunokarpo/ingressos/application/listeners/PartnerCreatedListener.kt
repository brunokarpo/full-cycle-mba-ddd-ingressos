package nom.brunokarpo.ingressos.application.listeners

import nom.brunokarpo.ingressos.domain.common.DomainEventListener
import nom.brunokarpo.ingressos.domain.events.domainevents.PartnerCreated

class PartnerCreatedListener: DomainEventListener<PartnerCreated> {

	override fun onEvent(event: PartnerCreated) {
		println("O partner ${event.name} foi criado! $event")
	}
}