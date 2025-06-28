package nom.brunokarpo.ingressos.domain.events.values

import nom.brunokarpo.ingressos.domain.common.valueobjects.EntityAsValue
import nom.brunokarpo.ingressos.domain.events.Spot
import java.util.UUID

class SpotValue (
	val id: UUID,
	val location: String
): EntityAsValue() {
	internal fun asEntity(): Spot {
		return Spot.create(
			id = id,
			locationName = location
		)
	}
}