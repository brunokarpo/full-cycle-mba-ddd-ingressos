package nom.brunokarpo.ingressos.infra.messaging.configurations

import nom.brunokarpo.ingressos.infra.messaging.configurations.containers.LocalstackContainer
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext

class ContainersInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
	override fun initialize(applicationContext: ConfigurableApplicationContext) {
		LocalstackContainer.start()
		TestPropertyValues
			.of(LocalstackContainer.environment.map { (k, v) -> "$k=$v" })
			.applyTo(applicationContext.environment)
	}
}