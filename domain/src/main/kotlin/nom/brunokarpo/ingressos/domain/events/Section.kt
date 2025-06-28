package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.common.Entity

interface Section : Entity {
	val name: String

	fun addSpots(numberOfSpots: Int)
	fun getNumberOfSpots(): Int
}