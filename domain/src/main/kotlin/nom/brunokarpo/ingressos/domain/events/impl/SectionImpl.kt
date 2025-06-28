package nom.brunokarpo.ingressos.domain.events.impl

import nom.brunokarpo.ingressos.domain.events.Section
import nom.brunokarpo.ingressos.domain.events.Spot
import nom.brunokarpo.ingressos.domain.events.factories.SpotFactory
import java.util.UUID

internal class SectionImpl(
	override val id: UUID,
	override val name: String
) : Section {

	private val spots = mutableSetOf<Spot>()

	override fun addSpots(numberOfSpots: Int) {
		(1..numberOfSpots).forEach { i ->
			spots.add(SpotFactory.create(locationName = (spots.size + 1).toString()))
		}
	}

	override fun getNumberOfSpots(): Int {
		return spots.size
	}
}