package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.fixtures.EventFixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull

class EventTest {

	@Test
	fun `should add one section in the event`() {
		val event = EventFixture.create()

		val sectionName = "Section 1"
		val numberOfSpots = 100

		event.addSection(sectionName = sectionName, numberOfSpots = numberOfSpots)

		val section = event.getSections().first()

		section.apply {
			assertNotNull(section)
			assertEquals(sectionName, name)
		}
	}
}