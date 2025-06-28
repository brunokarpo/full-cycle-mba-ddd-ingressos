package nom.brunokarpo.ingressos.domain.events.impl

import nom.brunokarpo.ingressos.domain.events.Event
import nom.brunokarpo.ingressos.domain.events.Section
import nom.brunokarpo.ingressos.domain.events.factories.SectionFactory
import nom.brunokarpo.ingressos.domain.events.values.SectionValue
import java.time.ZonedDateTime
import java.util.UUID

internal class EventImpl(
	override val name: String,
	override val description: String,
	override val date: ZonedDateTime,
	override val partnerId: UUID,
	override val id: UUID
) : Event{

	override val sections: Set<SectionValue>
		get() {
			return mutableSections.map { section -> SectionValue(section) }.toSet()
		}

	private val mutableSections = mutableListOf<Section>()

	override fun addSection(sectionName: String, numberOfSpots: Int) {
		val section = SectionFactory.create(name = sectionName)
		section.addSpots(numberOfSpots)
		mutableSections.add(section)
	}

	override fun addSection(sectionValue: SectionValue) {
		val section = sectionValue.asEntity()
		section.addSpots(sectionValue.spots)
		mutableSections.add(section)
	}

	override fun getNumberOfSpotsInSection(sectionName: String): Int {
		return mutableSections.first { section -> section.name == sectionName }.getNumberOfSpots()
	}
}