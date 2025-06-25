package nom.brunokarpo.ingressos.infra.database.events

import io.mockk.every
import io.mockk.mockk
import nom.brunokarpo.ingressos.domain.common.valueobjects.Cnpj
import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.repository.PartnerRepository
import nom.brunokarpo.ingressos.infra.database.DatabaseConfigurationTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.jdbc.Sql
import java.util.UUID

class PartnerJdbcRepositoryTest : DatabaseConfigurationTest() {

	@Autowired
	private lateinit var partnerRepository: PartnerRepository

	@Test
	fun `should save partner`() {
		val partner = mockk<Partner> {
			every { id } returns UUID.randomUUID()
			every { name } returns "Test"
			every { cnpj } returns Cnpj("00000000000000")
		}

		partnerRepository.save(partner)
	}

	@Test
	@Sql(scripts = ["/load-partners.sql"])
	fun `should load partner from database by id`() {
		val partnerId = UUID.fromString("8c4fc406-3e25-4ccd-af77-29ad47ae5e47")
		val partner = partnerRepository.ofId(partnerId)

		partner.apply {
			assertNotNull(this)
			assertEquals("Test partner", name)
			assertEquals(partnerId, id)
			assertEquals(Cnpj("00000000000001"), cnpj)
		}
	}

	@Test
	fun `should return null when there is no partner by id`() {
		val partnerId = UUID.randomUUID()
		val partner = partnerRepository.ofId(partnerId)
		assertNull(partner)
	}
}