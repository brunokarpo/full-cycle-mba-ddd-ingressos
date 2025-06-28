package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.common.Entity
import nom.brunokarpo.ingressos.domain.events.values.SpotValue

interface Section : Entity {
	val name: String
	val spots: Set<SpotValue>

	fun addSpots(numberOfSpots: Int)
	fun addSpots(spotsValue: Set<SpotValue>)
	fun getNumberOfSpots(): Int
}