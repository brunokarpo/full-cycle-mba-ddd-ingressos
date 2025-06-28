package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.fixtures.EventFixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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
}