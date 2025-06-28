package nom.brunokarpo.ingressos.domain.fixtures

import nom.brunokarpo.ingressos.domain.events.Section
import nom.brunokarpo.ingressos.domain.events.factories.SectionFactory

object SectionFixture {

	internal fun create(): Section {
		return SectionFactory
			.create(
				name = "Section Name"
			)
	}
}