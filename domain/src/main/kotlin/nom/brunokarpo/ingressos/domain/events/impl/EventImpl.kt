package nom.brunokarpo.ingressos.domain.events.impl

import nom.brunokarpo.ingressos.domain.events.Event
import nom.brunokarpo.ingressos.domain.events.Section
import nom.brunokarpo.ingressos.domain.events.factories.SectionFactory
import java.time.ZonedDateTime
import java.util.UUID

internal class EventImpl(
	override val name: String,
	override val description: String,
	override val date: ZonedDateTime,
	override val partnerId: UUID,
	override val id: UUID
) : Event{

	private val sections = mutableListOf<Section>()

	override fun addSection(sectionName: String, numberOfSpots: Int) {
		val section = SectionFactory.create(name = sectionName)
		section.addSpots(numberOfSpots)
		sections.add(section)
	}

	override fun getNumberOfSpotsInSection(sectionName: String): Int {
		return sections.first { section -> section.name == sectionName }.getNumberOfSpots()
	}
}