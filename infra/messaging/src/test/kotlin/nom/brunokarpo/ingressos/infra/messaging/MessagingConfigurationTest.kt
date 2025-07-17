package nom.brunokarpo.ingressos.infra.messaging

import nom.brunokarpo.ingressos.infra.messaging.configurations.ContainersInitializer
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.NONE,
	classes = [MessagingConfiguration::class]
)
@ContextConfiguration(initializers = [ContainersInitializer::class])
class MessagingConfigurationTest {

	@Test
	fun initContexts() {
	}
}