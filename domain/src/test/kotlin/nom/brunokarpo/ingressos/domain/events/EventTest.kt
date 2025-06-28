package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.events.domainevents.EventCreated
import nom.brunokarpo.ingressos.domain.events.domainevents.SectionAdded
import nom.brunokarpo.ingressos.domain.fixtures.EventFixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime
import java.util.UUID

class EventTest {

	@Test
	fun `should add one section in the event and get the number of spots by section name`() {
		val event = EventFixture.create()

		val sectionName = "Section 1"
		val numberOfSpots = 100

		event.addSection(sectionName = sectionName, numberOfSpots = numberOfSpots)

		val numberOfSpotsInSection = event.getNumberOfSpotsInSection(sectionName)

		assertEquals(numberOfSpots, numberOfSpotsInSection)
	}

	@Test
	fun `should add new section`() {
		val event = EventFixture.create()
		val sectionName = "Section 1"
		val numberOfSpots = 100

		event.addSection(sectionName, numberOfSpots)

		assertEquals(1, event.sections.size)
		assertEquals(sectionName, event.sections.first().name)
	}

	@Test
	fun `should registry event of event creation`() {
		val id = UUID.randomUUID()
		val name = "Event Name"
		val description = "Event Description"
		val date = ZonedDateTime.now()
		val partnerId = UUID.randomUUID()

		val event = Event.create(
			id = id,
			name = name,
			description = description,
			date = date,
			partnerId = partnerId
		)

		event.events.first { it is EventCreated }.apply {
			assertEquals(id, (this as EventCreated).aggregateId)
			assertEquals(name, this.name)
			assertEquals(description, this.description)
			assertEquals(date, this.date)
			assertEquals(partnerId, this.partnerId)
		}
	}

	@Test
	fun `should registry the section added event`() {
		val event = EventFixture.create()
		val sectionName = "Section 1"
		val numberOfSpots = 100

		event.addSection(sectionName, numberOfSpots)

		val sectionAdded = event.events.first { it is SectionAdded } as SectionAdded

		assertEquals(event.id, sectionAdded.aggregateId)
		assertEquals(sectionName, sectionAdded.name)
		assertEquals(numberOfSpots, sectionAdded.numberOfSpots)
	}
}