package nom.brunokarpo.ingressos.domain.events.values

import nom.brunokarpo.ingressos.domain.common.valueobjects.EntityAsValue
import nom.brunokarpo.ingressos.domain.events.Section
import nom.brunokarpo.ingressos.domain.events.factories.SectionFactory
import java.util.UUID

class SectionValue(
	val id: UUID,
	val name: String,
	val spots: Set<SpotValue>
) : EntityAsValue<Section> {

	constructor(section: Section): this(
		id = section.id,
		name = section.name,
		spots = section.spots
	)

	override fun asEntity(): Section {
		return SectionFactory.create(
			id = id,
			name = name
		)
	}
}