package nom.brunokarpo.ingressos.infra.database.configurations

import nom.brunokarpo.ingressos.infra.database.configurations.containers.DatabasePostgresContainer
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext

class ContainersInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
	override fun initialize(applicationContext: ConfigurableApplicationContext) {
		DatabasePostgresContainer.start()
		DatabasePostgresContainer.migrate()
		TestPropertyValues
			.of(DatabasePostgresContainer.environment.map { (k, v) -> "$k=$v" })
			.applyTo(applicationContext.environment)
	}
}