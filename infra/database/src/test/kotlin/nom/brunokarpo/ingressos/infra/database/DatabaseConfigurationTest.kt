package nom.brunokarpo.ingressos.infra.database

import nom.brunokarpo.ingressos.infra.database.configurations.ContainersInitializer
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql

@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.NONE,
	classes = [DatabaseConfiguration::class]
)
@ContextConfiguration(initializers = [ContainersInitializer::class])
@Sql(scripts = ["/clean-database.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class DatabaseConfigurationTest {

	@Test
	fun initContexts() {
	}
}