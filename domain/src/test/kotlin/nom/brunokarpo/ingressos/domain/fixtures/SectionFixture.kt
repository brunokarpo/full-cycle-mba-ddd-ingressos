package nom.brunokarpo.ingressos.domain.fixtures

import nom.brunokarpo.ingressos.domain.events.Section

object SectionFixture {

	internal fun create(): Section {
		return Section
			.create(
				name = "Section Name"
			)
	}
}