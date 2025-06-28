package nom.brunokarpo.ingressos.infra.database.evetns

import nom.brunokarpo.ingressos.domain.events.Event
import nom.brunokarpo.ingressos.domain.events.repository.EventRepository
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class EventJdbcRepository(
	private val jdbcTemplate: NamedParameterJdbcTemplate
) : EventRepository {
	override fun save(aggregate: Event) {
		val sql = """
			INSERT INTO events (id, name, description, date, partner_id)
			VALUES (:uuid, :name, :description, :date, :partnerId)
		""".trimIndent()
		val params = mapOf(
			"uuid" to aggregate.id,
			"name" to aggregate.name,
			"description" to aggregate.description,
			"date" to aggregate.date.toOffsetDateTime(),
			"partnerId" to aggregate.partnerId
		)
		jdbcTemplate.update(sql, params)
	}

	override fun ofId(id: UUID): Event? {
		TODO("Not yet implemented")
	}
}