package nom.brunokarpo.ingressos.infra.database.events

import io.mockk.every
import io.mockk.mockk
import nom.brunokarpo.ingressos.domain.common.valueobjects.Cnpj
import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.repository.PartnerRepository
import nom.brunokarpo.ingressos.infra.database.DatabaseConfigurationTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
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
}