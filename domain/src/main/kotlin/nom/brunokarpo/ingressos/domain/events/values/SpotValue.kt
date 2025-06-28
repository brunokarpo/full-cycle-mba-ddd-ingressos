package nom.brunokarpo.ingressos.domain.events.values

import nom.brunokarpo.ingressos.domain.common.valueobjects.EntityAsValue
import nom.brunokarpo.ingressos.domain.events.Spot
import nom.brunokarpo.ingressos.domain.events.factories.SpotFactory
import java.util.UUID

class SpotValue (
	val id: UUID,
	val location: String
): EntityAsValue() {
	internal fun asEntity(): Spot {
		return SpotFactory.create(
			id = id,
			locationName = location
		)
	}
}