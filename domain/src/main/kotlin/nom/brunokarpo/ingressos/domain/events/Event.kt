package nom.brunokarpo.ingressos.domain.events

import nom.brunokarpo.ingressos.domain.common.AggregateRoot
import nom.brunokarpo.ingressos.domain.events.factories.SectionFactory
import nom.brunokarpo.ingressos.domain.events.values.SectionValue
import java.time.ZonedDateTime
import java.util.UUID

class Event(
	override val id: UUID,
	val name: String,
	val description: String,
	val date: ZonedDateTime,
	val partnerId: UUID,
) : AggregateRoot() {

	private val mutableSections = mutableListOf<Section>()
	val sections: Set<SectionValue>
		get() = mutableSections.map { section -> SectionValue(section) }.toSet()

	fun addSection(sectionName: String, numberOfSpots: Int) {
		val section = SectionFactory.create(name = sectionName)
		section.addSpots(numberOfSpots)
		mutableSections.add(section)
	}

	fun addSection(sectionValue: SectionValue) {
		val section = sectionValue.asEntity()
		section.addSpots(sectionValue.spots)
		mutableSections.add(section)
	}

	fun getNumberOfSpotsInSection(sectionName: String): Int {
		return mutableSections.first { section -> section.name == sectionName }.getNumberOfSpots()
	}
}