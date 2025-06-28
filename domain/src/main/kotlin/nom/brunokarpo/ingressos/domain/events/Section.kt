package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.common.Entity
import nom.brunokarpo.ingressos.domain.events.factories.SpotFactory
import nom.brunokarpo.ingressos.domain.events.values.SpotValue
import java.util.UUID

internal class Section(
	override val id: UUID,
	val name: String,
) : Entity {

	private val mutableSpots = mutableSetOf<Spot>()

	val spots: Set<SpotValue>
		get() = mutableSpots.map { spot -> SpotValue(spot.id, spot.location) }.toSet()

	fun addSpots(numberOfSpots: Int) {
		(1..numberOfSpots).forEach { i ->
			mutableSpots.add(SpotFactory.create(locationName = (mutableSpots.size + 1).toString()))
		}
	}

	fun addSpots(spotsValue: Set<SpotValue>) {
		spotsValue.forEach { spotValue ->
			mutableSpots.add(
				spotValue.asEntity()
			)
		}
	}

	fun getNumberOfSpots(): Int {
		return mutableSpots.size
	}
}