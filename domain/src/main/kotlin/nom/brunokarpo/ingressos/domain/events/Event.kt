package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.common.Aggregate
import java.time.ZonedDateTime
import java.util.UUID

interface Event : Aggregate {
	val name: String
	val description: String
	val date: ZonedDateTime
	val partnerId: UUID

	fun addSection(sectionName: String, numberOfSpots: Int)

	fun getSections(): Set<Section>
}