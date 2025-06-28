package nom.brunokarpo.ingressos.domain.events.factories

import nom.brunokarpo.ingressos.domain.events.Spot
import java.util.UUID

internal object SpotFactory {

	fun create(
		id: UUID = UUID.randomUUID(),
		locationName: String
	): Spot {
		return Spot(
			id = id,
			location = locationName
		)
	}
}