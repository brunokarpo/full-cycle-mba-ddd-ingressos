package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.fixtures.SectionFixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SectionTest {

	@Test
	fun `should add spots in section`() {
		val section = SectionFixture.create()

		section.addSpots(10)

		assertEquals(10, section.spots.size)
	}
}