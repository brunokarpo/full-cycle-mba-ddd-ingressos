package nom.brunokarpo.ingressos.infra.messaging.integrationevents

import nom.brunokarpo.ingressos.domain.common.valueobjects.Cnpj
import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.domainevents.PartnerCreated
import nom.brunokarpo.ingressos.infra.messaging.MessagingConfigurationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.testcontainers.shaded.org.awaitility.Awaitility.await
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.SqsClient
import java.time.Duration
import java.util.UUID

class PartnerCreatedEventSqsPublisherTest: MessagingConfigurationTest() {

	@Autowired
	private lateinit var sut: PartnerCreatedEventSqsPublisher

	@Autowired
	private lateinit var sqsAsyncClient: SqsAsyncClient

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
				val messages =
					sqsAsyncClient.receiveMessage { it.queueUrl("http://localhost:4566/000000000000/PARTNER_CREATED_QUEUE") }
				val actual = messages.get().messages()
				assertThat(actual).hasSize(1)
				assertThat(actual[0].body()).contains("<NAME>")
				sqsAsyncClient.deleteMessage { it.queueUrl("http://localhost:4566/000000000000/PARTNER_CREATED_QUEUE").receiptHandle(actual[0].receiptHandle()) }
			}
	}
}