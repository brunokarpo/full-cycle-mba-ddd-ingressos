package nom.brunokarpo.ingressos.application.usecases.exceptions

import java.util.UUID

class EventDoesNotExistException(
	val eventId: UUID
): Exception("Event with id $eventId does not exist")