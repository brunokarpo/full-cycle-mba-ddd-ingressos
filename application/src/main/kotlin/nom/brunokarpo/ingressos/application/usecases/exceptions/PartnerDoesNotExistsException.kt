package nom.brunokarpo.ingressos.application.usecases.exceptions

import java.util.UUID

class PartnerDoesNotExistsException(partnerId: UUID) : Exception(
	"Partner with id $partnerId doesn't exist"
)
