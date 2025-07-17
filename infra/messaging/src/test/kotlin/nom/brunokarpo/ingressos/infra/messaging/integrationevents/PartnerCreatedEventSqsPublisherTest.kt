package nom.brunokarpo.ingressos.infra.messaging.integrationevents

import nom.brunokarpo.ingressos.domain.common.valueobjects.Cnpj
import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.domainevents.PartnerCreated
import nom.brunokarpo.ingressos.infra.messaging.MessagingConfigurationTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.testcontainers.shaded.org.awaitility.Awaitility.await
import java.time.Duration
import java.util.UUID

class PartnerCreatedEventSqsPublisherTest: MessagingConfigurationTest() {

	@Autowired
	private lateinit var sut: PartnerCreatedEventSqsPublisher

	@Test
	fun `should publish the partner created event message`() {
		val partnerCreatedEvent = PartnerCreated(
			Partner(
				id = UUID.randomUUID(),
				name = "<NAME>",
				cnpj = Cnpj("00000000000000")
			)
		)

		sut.publish(partnerCreatedEvent)

		await()
			.pollInterval(Duration.ofSeconds(2))
			.atMost(Duration.ofSeconds(10))
			.ignoreExceptions()
			.untilAsserted {
				// TODO: create assertion to validate if message was sent to sqs
			}
	}
}