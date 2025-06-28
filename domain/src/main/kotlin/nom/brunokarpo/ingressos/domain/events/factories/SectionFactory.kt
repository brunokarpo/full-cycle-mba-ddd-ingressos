package nom.brunokarpo.ingressos.domain.events.factories

import nom.brunokarpo.ingressos.domain.events.Section
import java.util.UUID

internal object SectionFactory {

	fun create(
		id: UUID = UUID.randomUUID(),
		name: String
	): Section {
		return Section(
			id = id,
			name = name
		)
	}

}