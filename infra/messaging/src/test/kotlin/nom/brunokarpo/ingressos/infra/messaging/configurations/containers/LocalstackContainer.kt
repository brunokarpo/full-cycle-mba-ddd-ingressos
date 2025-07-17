package nom.brunokarpo.ingressos.infra.messaging.configurations.containers

import org.testcontainers.containers.localstack.LocalStackContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.utility.DockerImageName

object LocalstackContainer {


	private val LOCALSTACK_IMAGE: DockerImageName = DockerImageName.parse("localstack/localstack:4.6.0")

	@Container
	val localStackContainer: LocalStackContainer = LocalStackContainer(LOCALSTACK_IMAGE)
		.withServices(LocalStackContainer.Service.SQS)
		.withEnv("LOCALSTACK_HOST", "localhost")
		.withExposedPorts(4566)
		.withReuse(false)


	fun start() {
		localStackContainer.apply {
			start()
			createQueues()
		}
	}

	fun createQueues() {
		localStackContainer.execInContainer("awslocal", "sqs", "create-queue", "--queue-name", "PARTNER_CREATED_QUEUE")
	}

	val environment: Map<String, String>
		get() {
			return mapOf(
				"spring.cloud.aws.credentials.access-key" to localStackContainer.accessKey,
				"spring.cloud.aws.credentials.secret-key" to localStackContainer.secretKey,
				"spring.cloud.aws.credentials.profile.name" to "test",
				"spring.cloud.aws.region.static" to localStackContainer.region,
				"spring.cloud.aws.sqs.endpoint" to localStackContainer.getEndpointOverride(LocalStackContainer.Service.SQS).toString(),
				"logging.level.io.awspring.cloud" to "debug"
			)
		}
}