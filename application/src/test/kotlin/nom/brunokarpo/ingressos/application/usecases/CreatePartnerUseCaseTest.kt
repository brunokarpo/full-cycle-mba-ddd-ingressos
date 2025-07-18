package nom.brunokarpo.ingressos.application.usecases

import io.mockk.mockk
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import nom.brunokarpo.ingressos.domain.common.AggregateRootNotifier
import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.repository.PartnerRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CreatePartnerUseCaseTest {

	private lateinit var partnerRepository: PartnerRepository
	private lateinit var aggregateRootNotifier: AggregateRootNotifier

	private lateinit var sut: CreatePartnerUseCase


	@BeforeEach
	fun setUp() {
		partnerRepository = mockk(relaxed = true)
		aggregateRootNotifier = spyk()

		sut = CreatePartnerUseCase(partnerRepository, aggregateRootNotifier)
	}

	@Test
	fun `should create and save new partner`() {
		val name = "Test"
		val cnpj = "00000000000000"

		val partner = sut.createPartner(name, cnpj)

		assertNotNull(partner)
		assertEquals(name, partner.name)
		assertEquals(cnpj, partner.cnpj)

		val slot = slot<Partner>()

		verify { partnerRepository.save(capture(slot)) }

		val captured = slot.captured
		assertNotNull(captured)
		assertNotNull(captured.id)
		assertEquals(name, captured.name)
		assertEquals(cnpj, captured.cnpj.value)
	}

	@Test
	fun `should publish the creation partner event`() {
		val name = "Test"
		val cnpj = "00000000000000"

		sut.createPartner(name, cnpj)

		val slot = slot<Partner>()
		verify(exactly = 1) { aggregateRootNotifier.notify(capture(slot)) }

		val captured = slot.captured
		assertNotNull(captured)
		assertNotNull(captured.id)
		assertEquals(name, captured.name)
		assertEquals(cnpj, captured.cnpj.value)
		assertEquals(1, captured.events.size)
	}
}