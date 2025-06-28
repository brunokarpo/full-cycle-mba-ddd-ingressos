package nom.brunokarpo.ingressos.domain.events.impl

import nom.brunokarpo.ingressos.domain.events.Spot
import java.util.UUID

internal class SpotImpl(
	override val id: UUID,
	override val location: String
) : Spot