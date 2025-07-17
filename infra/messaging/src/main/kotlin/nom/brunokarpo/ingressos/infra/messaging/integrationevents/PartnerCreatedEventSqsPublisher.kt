package nom.brunokarpo.ingressos.infra.messaging.integrationevents

import io.awspring.cloud.sqs.operations.SqsTemplate
import nom.brunokarpo.ingressos.domain.events.domainevents.PartnerCreated
import nom.brunokarpo.ingressos.domain.events.integrationevents.PartnerCreatedEventPublisher
import org.springframework.stereotype.Component

@Component
class PartnerCreatedEventSqsPublisher(
	private val sqsTemplate: SqsTemplate
): PartnerCreatedEventPublisher {

	override fun publish(partnerCreated: PartnerCreated) {
		sqsTemplate.send { to -> to.queue("PARTNER_CREATED_QUEUE")
			.payload(partnerCreated)
		}
	}
}