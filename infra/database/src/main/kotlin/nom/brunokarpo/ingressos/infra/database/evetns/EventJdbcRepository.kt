package nom.brunokarpo.ingressos.infra.database.evetns

import nom.brunokarpo.ingressos.domain.events.Event
import nom.brunokarpo.ingressos.domain.events.repository.EventRepository
import nom.brunokarpo.ingressos.domain.events.values.SectionValue
import nom.brunokarpo.ingressos.domain.events.values.SpotValue
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class EventJdbcRepository(
	private val jdbcTemplate: NamedParameterJdbcTemplate
) : EventRepository {
	override fun save(aggregate: Event) {
		saveEvent(aggregate)
		saveSections(aggregate.sections, aggregate.id)
	}

	private fun saveEvent(event: Event) {
		val sql = """
				INSERT INTO events (id, name, description, date, partner_id)
				VALUES (:uuid, :name, :description, :date, :partnerId)
			""".trimIndent()
		val params = mapOf(
			"uuid" to event.id,
			"name" to event.name,
			"description" to event.description,
			"date" to event.date.toOffsetDateTime(),
			"partnerId" to event.partnerId
		)
		jdbcTemplate.update(sql, params)
	}

	private fun saveSections(sections: Set<SectionValue>, eventId: UUID) {
		sections.forEach { section ->
			saveSection(section, eventId)
			saveSpots(section.spots, section.id)
		}
	}

	private fun saveSection(
		section: SectionValue,
		eventId: UUID
	) {
		val sql = """
			INSERT INTO sections (id, name, event_id)
			VALUES (:uuid, :name, :eventId)
		""".trimIndent()
		val params = mapOf(
			"uuid" to section.id,
			"name" to section.name,
			"eventId" to eventId
		)
		jdbcTemplate.update(sql, params)
	}

	private fun saveSpots(spots: Set<SpotValue>, sectionId: UUID) {
		spots.forEach { spot -> saveSpot(spot, sectionId) }
	}

	private fun saveSpot(
		spot: SpotValue,
		sectionId: UUID
	) {
		val sql = """
			INSERT INTO spots (id, location, section_id)
			VALUES (:uuid, :location, :sectionId)
		""".trimIndent()
		val params = mapOf(
			"uuid" to spot.id,
			"location" to spot.location,
			"sectionId" to sectionId
		)
		jdbcTemplate.update(sql, params)
	}

	override fun ofId(id: UUID): Event? {
		TODO("Not yet implemented")
	}
}