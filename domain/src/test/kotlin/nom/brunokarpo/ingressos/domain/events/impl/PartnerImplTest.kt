package nom.brunokarpo.ingressos.domain.events.impl

import nom.brunokarpo.ingressos.domain.events.commands.CreateEventCommand
import nom.brunokarpo.ingressos.domain.fixtures.PartnerFixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime

class PartnerImplTest {

	@Test
	fun `should create an new event`() {
		val name = "Test"
		val description = "Test description"
		val date = ZonedDateTime.now()

		val createEventCommand = CreateEventCommand.builder()
			.withName(name)
			.withDescription(description)
			.withDate(date)
			.build()

		val partner = PartnerFixture.create()

		val event = partner.createEvent(createEventCommand)

		assertEquals(name, event.name)
		assertEquals(description, event.description)
		assertEquals(date, event.date)
		assertNotNull(event.id)
		assertEquals(partner.id, event.partnerId)
	}
}