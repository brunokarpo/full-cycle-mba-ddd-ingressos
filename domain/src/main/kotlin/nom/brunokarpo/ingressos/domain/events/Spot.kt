package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.common.Entity

interface Spot : Entity {
	val location: String
}