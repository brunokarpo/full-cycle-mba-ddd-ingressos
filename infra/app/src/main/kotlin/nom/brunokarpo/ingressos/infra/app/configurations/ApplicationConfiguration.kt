package nom.brunokarpo.ingressos.infra.app.configurations

import nom.brunokarpo.ingressos.application.usecases.AddSectionInEventUseCase
import nom.brunokarpo.ingressos.application.usecases.CreateNewEventUseCase
import nom.brunokarpo.ingressos.application.usecases.CreatePartnerUseCase
import nom.brunokarpo.ingressos.domain.events.repository.EventRepository
import nom.brunokarpo.ingressos.domain.events.repository.PartnerRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {

	@Bean
	fun createPartnerUseCase(
		partnerRepository: PartnerRepository
	) = CreatePartnerUseCase(partnerRepository)

	@Bean
	fun createNewEventUseCase(
		partnerRepository: PartnerRepository,
		eventRepository: EventRepository
	) = CreateNewEventUseCase(partnerRepository, eventRepository)

	@Bean
	fun addSectionInEventUseCase(
		eventRepository: EventRepository
	) = AddSectionInEventUseCase(eventRepository)
}