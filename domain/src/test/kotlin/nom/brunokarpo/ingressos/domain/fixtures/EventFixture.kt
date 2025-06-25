package nom.brunokarpo.ingressos.domain.fixtures

import nom.brunokarpo.ingressos.domain.events.Event
import nom.brunokarpo.ingressos.domain.events.factories.EventFactory
import java.time.ZonedDateTime
import java.util.UUID

object EventFixture {

	fun create(): Event {
		return EventFactory.create(
			id = UUID.randomUUID(),
			name = UUID.randomUUID().toString(),
			description = UUID.randomUUID().toString(),
			date = ZonedDateTime.now(),
			partnerId = UUID.randomUUID()
		)
	}
}