package nom.brunokarpo.ingressos.infra.database.configurations.containers

import org.flywaydb.core.Flyway
import org.testcontainers.containers.JdbcDatabaseContainer
import org.testcontainers.containers.PostgreSQLContainerProvider
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Container

object DatabasePostgresContainer {
	private const val PORT = 5432
	private const val DATABASE_NAME = "ingressos-db"
	private const val USERNAME = "ingressos-app"
	private const val PASSWORD = "ingressos-app"
	private const val DATABASE_VERSION = "17.4-alpine"
	private const val SQL_FILES = "../../scripts/db/migrations/"

	@Container
	val container: JdbcDatabaseContainer<*> =
		PostgreSQLContainerProvider().newInstance(DATABASE_VERSION).apply {
			withExposedPorts(PORT)
			withDatabaseName(DATABASE_NAME)
			withUsername(USERNAME)
			withPassword(PASSWORD)
			withReuse(false)
		}

	fun start() {
		container.apply {
			start()
			waitingFor(Wait.forListeningPort())
		}
	}

	fun migrate() {
		val flyway =
			Flyway
				.configure()
				.dataSource(container.jdbcUrl, container.username, container.password)
				.locations("filesystem:$SQL_FILES")
				.configuration(mapOf("flyway.postgresql.transactional.lock" to "false"))
				.load()

		flyway.migrate()
	}

	val environment: Map<String, String>
		get() {
			return mapOf(
				"spring.datasource.url" to container.jdbcUrl,
				"spring.datasource.username" to container.username,
				"spring.datasource.password" to container.password,
			)
		}
}