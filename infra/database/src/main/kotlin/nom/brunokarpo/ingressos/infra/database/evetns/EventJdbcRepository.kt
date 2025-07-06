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
				ON CONFLICT (id) DO UPDATE SET name = :name, description = :description, date = :date, partner_id = :partnerId, updated_at = now()
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
			ON CONFLICT (id) DO UPDATE SET name = :name, event_id = :eventId, updated_at = now()
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
			ON CONFLICT (id) DO UPDATE SET location = :location, section_id = :sectionId, updated_at = now()
		""".trimIndent()
		val params = mapOf(
			"uuid" to spot.id,
			"location" to spot.location,
			"sectionId" to sectionId
		)
		jdbcTemplate.update(sql, params)
	}

	override fun ofId(id: UUID): Event? {
		return loadEvent(id)?.let { event ->
			loadSections(event.id).let { sections ->
				sections.forEach { section -> event.addSection(section) }
			}
			event
		}
	}

	private fun loadEvent(eventId: UUID): Event? {
		val sql = """
			SELECT id, name, description, date, partner_id FROM events WHERE id = :id
		""".trimIndent()
		val params = mapOf("id" to eventId)
		return jdbcTemplate.query(sql, params) { rs, _ ->
			Event(
				id = rs.getString("id").let { UUID.fromString(it) },
				name = rs.getString("name"),
				description = rs.getString("description"),
				date = rs.getTimestamp("date").toInstant().atZone(java.time.ZoneId.of("UTC")),
				partnerId = rs.getObject("partner_id", UUID::class.java)
			)
		}.first()
	}

	private fun loadSections(eventId: UUID): Set<SectionValue> {
		val sql = """
			SELECT id, name, event_id FROM sections s
			WHERE event_id = :eventId
		""".trimIndent()
		val params = mapOf("eventId" to eventId)
		return jdbcTemplate.query(sql, params) { rs, _ ->
			SectionValue(
				id = rs.getString("id").let { UUID.fromString(it) },
				name = rs.getString("name"),
				spots = loadSpot(rs.getString("id").let { UUID.fromString(it) })
			)
		}.toSet()
	}

	private fun loadSpot(sectionId: UUID): Set<SpotValue> {
		val sql = """
			SELECT id, location, section_id FROM spots s
			WHERE section_id = :sectionId
		""".trimIndent()
		val params = mapOf("sectionId" to sectionId)
		return jdbcTemplate.query(sql, params) { rs, _ ->
			SpotValue(
				id = rs.getString("id").let { UUID.fromString(it) },
				location = rs.getString("location")
			)
		}.toSet()
	}
}