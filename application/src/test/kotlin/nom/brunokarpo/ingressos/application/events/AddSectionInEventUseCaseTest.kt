package nom.brunokarpo.ingressos.application.events

import io.mockk.Ordering
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import nom.brunokarpo.ingressos.application.events.exceptions.EventDoesNotExistException
import nom.brunokarpo.ingressos.domain.events.Event
import nom.brunokarpo.ingressos.domain.events.repository.EventRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class AddSectionInEventUseCaseTest {

	private lateinit var eventRepositoryMock: EventRepository

	private lateinit var sut: AddSectionInEventUseCase

	@BeforeEach
	fun setUp() {
		eventRepositoryMock = mockk(relaxed = true)

		sut = AddSectionInEventUseCase(eventRepositoryMock)
	}

	@Test
	fun `should create section in existent event`() {
		val eventId = UUID.randomUUID()
		val sectionName = "Section 1"
		val numberOfSpots = 100

		val eventMock = mockk<Event>(relaxed = true)
		every { eventRepositoryMock.ofId(eventId) } returns eventMock

		sut.execute(eventId, sectionName, numberOfSpots)

		verify(ordering = Ordering.ORDERED) {
			eventRepositoryMock.ofId(eventId)
			eventMock.addSection(sectionName, numberOfSpots)
			eventRepositoryMock.save(eventMock)
		}
	}

	@Test
	fun `should throw EventDoesNotExistException when does not exists event with id`() {
		val eventId = UUID.randomUUID()
		val sectionName = "Section 1"
		val numberOfSpots = 100

		every { eventRepositoryMock.ofId(eventId) } returns null

		val exception =  assertThrows<EventDoesNotExistException> {
			sut.execute(eventId, sectionName, numberOfSpots)
		}

		assertEquals(eventId, exception.eventId)

		verify(exactly = 0) {
			eventRepositoryMock.save(any())
		}
	}
}