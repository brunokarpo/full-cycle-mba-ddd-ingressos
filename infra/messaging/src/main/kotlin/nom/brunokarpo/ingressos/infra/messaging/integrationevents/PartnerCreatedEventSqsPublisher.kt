package nom.brunokarpo.ingressos.infra.messaging.integrationevents

import nom.brunokarpo.ingressos.domain.events.domainevents.PartnerCreated
import nom.brunokarpo.ingressos.domain.events.integrationevents.PartnerCreatedEventPublisher
import org.springframework.stereotype.Component

@Component
class PartnerCreatedEventSqsPublisher(): PartnerCreatedEventPublisher {

	override fun publish(partnerCreated: PartnerCreated) {
		TODO("Not yet implemented")
	}
}