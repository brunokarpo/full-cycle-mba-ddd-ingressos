package nom.brunokarpo.ingressos.domain.events.values

import nom.brunokarpo.ingressos.domain.common.valueobjects.EntityAsValue
import nom.brunokarpo.ingressos.domain.events.Section
import java.util.UUID

class SectionValue(
	val id: UUID,
	val name: String,
	val spots: Set<SpotValue>
) : EntityAsValue() {

	internal constructor(section: Section): this(
		id = section.id,
		name = section.name,
		spots = section.spots
	)

	internal fun asEntity(): Section {
		return Section.create(
			id = id,
			name = name
		)
	}
}