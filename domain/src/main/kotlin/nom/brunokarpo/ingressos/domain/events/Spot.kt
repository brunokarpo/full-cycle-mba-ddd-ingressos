package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.common.Entity
import java.util.UUID

internal class Spot(
	override val id: UUID,
	val location: String
) : Entity {
	companion object {
		fun create(id: UUID = UUID.randomUUID(), locationName: String): Spot {
			return Spot(id, locationName)
		}
	}
}