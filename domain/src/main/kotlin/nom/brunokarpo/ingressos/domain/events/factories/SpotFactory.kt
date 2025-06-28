package nom.brunokarpo.ingressos.domain.events.factories

import nom.brunokarpo.ingressos.domain.events.Spot
import nom.brunokarpo.ingressos.domain.events.impl.SpotImpl
import java.util.UUID

internal object SpotFactory {

	fun create(
		id: UUID = UUID.randomUUID(),
		locationName: String
	): Spot {
		return SpotImpl(
			id = id,
			location = locationName
		)
	}
}