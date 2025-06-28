package nom.brunokarpo.ingressos.domain.events.impl

import nom.brunokarpo.ingressos.domain.events.Section
import nom.brunokarpo.ingressos.domain.events.Spot
import nom.brunokarpo.ingressos.domain.events.factories.SpotFactory
import nom.brunokarpo.ingressos.domain.events.values.SpotValue
import java.util.UUID

internal class SectionImpl(
	override val id: UUID,
	override val name: String
) : Section {

	override val spots: Set<SpotValue>
		get() = mutableSpots.map { spot -> SpotValue(spot.id, spot.location) }.toSet()

	private val mutableSpots = mutableSetOf<Spot>()

	override fun addSpots(numberOfSpots: Int) {
		(1..numberOfSpots).forEach { i ->
			mutableSpots.add(SpotFactory.create(locationName = (mutableSpots.size + 1).toString()))
		}
	}

	override fun addSpots(spotsValue: Set<SpotValue>) {
		spotsValue.forEach { spotValue ->
			mutableSpots.add(
				spotValue.asEntity()
			)
		}
	}

	override fun getNumberOfSpots(): Int {
		return mutableSpots.size
	}
}