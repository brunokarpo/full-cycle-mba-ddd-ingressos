package nom.brunokarpo.ingressos.infra.database.events

import nom.brunokarpo.ingressos.domain.events.Event
import nom.brunokarpo.ingressos.domain.events.factories.EventFactory
import nom.brunokarpo.ingressos.domain.events.repository.EventRepository
import nom.brunokarpo.ingressos.infra.database.DatabaseConfigurationTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import java.time.ZonedDateTime
import java.util.UUID

class EventJdbcRepositoryTest: DatabaseConfigurationTest() {

	@Autowired
	private lateinit var eventRepository: EventRepository

	@Test
	@SqlGroup(
		Sql(scripts = ["/load-partners.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
		Sql(scripts = ["/clean-database.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	)
	fun `should save a new event without sections and spots`() {
		val partnerId = UUID.fromString("8c4fc406-3e25-4ccd-af77-29ad47ae5e47")
		val event = createEvent(eventPartnerId = partnerId)

		assertDoesNotThrow {
			eventRepository.save(event)
		}
	}

	@Test
	@SqlGroup(
		Sql(scripts = ["/load-partners.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
		Sql(scripts = ["/clean-database.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	)
	fun `should save event with sections`() {
		val partnerId = UUID.fromString("8c4fc406-3e25-4ccd-af77-29ad47ae5e47")
		val event = createEvent(eventPartnerId = partnerId)
		event.addSection("Section 1", 100)
		event.addSection("Section 2", 50)

		assertDoesNotThrow {
			eventRepository.save(event)
		}
	}

	private fun createEvent(
		eventId: UUID = UUID.randomUUID(),
		eventName: String = "Event Name",
		eventDescription: String = "Event Description",
		eventDate: ZonedDateTime = ZonedDateTime.now(),
		eventPartnerId: UUID = UUID.randomUUID()
	) : Event {
		return EventFactory.create(
			id = eventId,
			name = eventName,
			description = eventDescription,
			date = eventDate,
			partnerId = eventPartnerId
		)
	}


}