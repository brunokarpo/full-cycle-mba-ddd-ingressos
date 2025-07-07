package nom.brunokarpo.ingressos.domain.events.integrationevents

import nom.brunokarpo.ingressos.domain.events.domainevents.PartnerCreated

interface PartnerCreatedEventPublisher {

	fun publish(partnerCreated: PartnerCreated)
}