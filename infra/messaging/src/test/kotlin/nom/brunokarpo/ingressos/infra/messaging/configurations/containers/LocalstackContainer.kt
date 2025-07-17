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
				"AWS_ACCESS_KEY_ID" to localStackContainer.accessKey,
				"AWS_SECRET_ACCESS_KEY" to localStackContainer.secretKey,
				"AWS_REGION" to localStackContainer.region
			)
		}
}