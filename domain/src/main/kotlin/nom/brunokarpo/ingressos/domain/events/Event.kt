package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.common.AggregateRoot
import nom.brunokarpo.ingressos.domain.events.values.SectionValue
import java.time.ZonedDateTime
import java.util.UUID

interface Event : AggregateRoot {
	val name: String
	val description: String
	val date: ZonedDateTime
	val partnerId: UUID
	val sections: Set<SectionValue>

	fun addSection(sectionName: String, numberOfSpots: Int)
	fun addSection(sectionValue: SectionValue)
	fun getNumberOfSpotsInSection(sectionName: String): Int
}