package nom.brunokarpo.ingressos.application.events.exceptions

import java.util.UUID

class PartnerDoesNotExistsException(partnerId: UUID) : Exception(
	"Partner with id $partnerId doesn't exist"
)
