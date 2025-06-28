package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.events.commands.CreateEventCommand
import nom.brunokarpo.ingressos.domain.events.domainevents.PartnerCreatedEvent
import nom.brunokarpo.ingressos.domain.fixtures.PartnerFixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime

class PartnerTest {

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

	@Test
	fun `should create a partner and register partner created event`() {
		val partnerName = "Test"
		val partnerCnpj = "00000000000000"
		val partner: Partner = Partner.create(name = partnerName, cnpj = partnerCnpj)

		val event = partner.events.first()

		(event as PartnerCreatedEvent).apply {
			assertNotNull(this)
			assertEquals(partner.id, event.aggregateId)
			assertEquals(partnerName, event.name)
			assertEquals(partnerCnpj, event.cnpj)
			assertEquals(1, event.version)
		}
	}
}