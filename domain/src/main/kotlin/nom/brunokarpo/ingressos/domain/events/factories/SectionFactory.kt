package nom.brunokarpo.ingressos.domain.events.factories

import nom.brunokarpo.ingressos.domain.events.Section
import nom.brunokarpo.ingressos.domain.events.impl.SectionImpl
import java.util.UUID

internal object SectionFactory {

	fun create(
		id: UUID = UUID.randomUUID(),
		name: String
	): Section {
		return SectionImpl(
			id = id,
			name = name
		)
	}

}