package nom.brunokarpo.ingressos.application.events.exceptions

import java.util.UUID

class EventDoesNotExistException(
	val eventId: UUID
): Exception("Event with id $eventId does not exist")