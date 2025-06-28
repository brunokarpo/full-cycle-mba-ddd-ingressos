package nom.brunokarpo.ingressos.domain.events.factories

import nom.brunokarpo.ingressos.domain.events.Spot
import nom.brunokarpo.ingressos.domain.events.impl.SpotImpl
import java.util.UUID

internal object SpotFactory {

	fun create(locationName: String): Spot {
		return SpotImpl(
			id = UUID.randomUUID(),
			location = locationName
		)
	}
}