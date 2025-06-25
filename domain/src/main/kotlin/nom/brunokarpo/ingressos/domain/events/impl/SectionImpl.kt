package nom.brunokarpo.ingressos.domain.events.impl

import nom.brunokarpo.ingressos.domain.events.Section
import java.util.UUID

internal class SectionImpl(
	override val id: UUID,
	override val name: String
) : Section