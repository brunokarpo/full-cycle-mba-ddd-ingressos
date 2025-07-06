package nom.brunokarpo.ingressos.application.usecases

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import nom.brunokarpo.ingressos.application.dto.EventDTO
import nom.brunokarpo.ingressos.application.usecases.exceptions.PartnerDoesNotExistsException
import nom.brunokarpo.ingressos.domain.common.valueobjects.AggregateRootPublisher
import nom.brunokarpo.ingressos.domain.events.Event
import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.domainevents.EventCreated
import nom.brunokarpo.ingressos.domain.events.repository.EventRepository
import nom.brunokarpo.ingressos.domain.events.repository.PartnerRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.ZonedDateTime
import java.util.UUID

class CreateNewEventUseCaseTest {

	private companion object {
		val EVENT_ID: UUID = UUID.randomUUID()
		const val EVENT_NAME: String = "eventName"
		const val EVENT_DESCRIPTION: String = "eventDescription"
		val EVENT_DATE: ZonedDateTime = ZonedDateTime.now()
		val PARTNER_ID: UUID = UUID.randomUUID()
	}

	private lateinit var event: Event

	private lateinit var partnerRepository: PartnerRepository
	private lateinit var eventRepository: EventRepository
	private lateinit var aggregateRootPublisher: AggregateRootPublisher

	private lateinit var sut: CreateNewEventUseCase
	private lateinit var partner: Partner

	@BeforeEach
	fun setUp() {
		event = mockk(relaxed = true) {
			every { id } returns EVENT_ID
			every { name } returns EVENT_NAME
			every { description } returns EVENT_DESCRIPTION
			every { date } returns EVENT_DATE
			every { partnerId } returns PARTNER_ID
		}
		partner = mockk(relaxed = true) {
			every { createEvent(any()) } returns event
		}
		partnerRepository = mockk(relaxed = true) {
			every { ofId(any()) } returns partner
		}
		eventRepository = mockk(relaxed = true)

		aggregateRootPublisher = spyk()

		sut = CreateNewEventUseCase(partnerRepository, eventRepository, aggregateRootPublisher)
	}

	@Test
	fun `should create a new event`() {
		val eventDTO: EventDTO = sut.execute(PARTNER_ID, EVENT_NAME, EVENT_DESCRIPTION, EVENT_DATE)

		assertEquals(EVENT_ID, eventDTO.id)
		assertEquals(EVENT_NAME, eventDTO.name)
		assertEquals(EVENT_DESCRIPTION, eventDTO.description)
		assertEquals(EVENT_DATE, eventDTO.date)
	}

	@Test
	fun `should load partner to create a new event`() {
		sut.execute(PARTNER_ID, EVENT_NAME, EVENT_DESCRIPTION, EVENT_DATE)

		verify { partner.createEvent(any()) }
	}

	@Test
	fun `should not create new event if partner does not exists`() {
		every {
			partnerRepository.ofId(PARTNER_ID)
		} returns null

		assertThrows<PartnerDoesNotExistsException> {
			sut.execute(PARTNER_ID, EVENT_NAME, EVENT_DESCRIPTION, EVENT_DATE)
		}

		verify(exactly = 0) { partner.createEvent(any()) }
	}

	@Test
	fun `should save the new event created`() {
		sut.execute(PARTNER_ID, EVENT_NAME, EVENT_DESCRIPTION, EVENT_DATE)

		verify { eventRepository.save(event) }
	}

	@Test
	fun `should publish EventCreated event when event is created`() {
		every { event.events } returns mutableListOf(EventCreated(event))

		sut.execute(PARTNER_ID, EVENT_NAME, EVENT_DESCRIPTION, EVENT_DATE)

		val slot = slot<EventCreated>()

		verify(exactly = 1) { aggregateRootPublisher.handleEvent(capture(slot)) }

		val captured = slot.captured
		assertNotNull(captured)
		assertEquals(EVENT_ID, captured.aggregateId)
		assertEquals(EVENT_NAME, captured.name)
		assertEquals(EVENT_DESCRIPTION, captured.description)
		assertEquals(EVENT_DATE, captured.date)
		assertEquals(PARTNER_ID, captured.partnerId)
		assertEquals(1L, captured.version)
	}
}