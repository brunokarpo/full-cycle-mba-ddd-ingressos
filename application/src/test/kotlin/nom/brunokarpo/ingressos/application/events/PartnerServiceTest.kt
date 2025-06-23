package nom.brunokarpo.ingressos.application.events

import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.repository.PartnerRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PartnerServiceTest {

	private lateinit var partnerRepository: PartnerRepository

	private lateinit var sut: PartnerService


	@BeforeEach
	fun setUp() {
		partnerRepository = mockk(relaxed = true)

		sut = PartnerService(partnerRepository)
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
}