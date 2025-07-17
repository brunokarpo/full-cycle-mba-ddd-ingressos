package nom.brunokarpo.ingressos.infra.app.configurations

import nom.brunokarpo.ingressos.application.listeners.PartnerCreatedListener
import nom.brunokarpo.ingressos.application.usecases.AddSectionInEventUseCase
import nom.brunokarpo.ingressos.application.usecases.CreateNewEventUseCase
import nom.brunokarpo.ingressos.application.usecases.CreatePartnerUseCase
import nom.brunokarpo.ingressos.domain.common.AggregateRootPublisher
import nom.brunokarpo.ingressos.domain.events.integrationevents.PartnerCreatedEventPublisher
import nom.brunokarpo.ingressos.domain.events.repository.EventRepository
import nom.brunokarpo.ingressos.domain.events.repository.PartnerRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {

	@Bean
	fun createPartnerUseCase(
		partnerRepository: PartnerRepository,
		aggregateRootPublisher: AggregateRootPublisher
	) = CreatePartnerUseCase(partnerRepository, aggregateRootPublisher)

	@Bean
	fun createNewEventUseCase(
		partnerRepository: PartnerRepository,
		eventRepository: EventRepository,
		aggregateRootPublisher: AggregateRootPublisher
	) = CreateNewEventUseCase(partnerRepository, eventRepository, aggregateRootPublisher)

	@Bean
	fun addSectionInEventUseCase(
		eventRepository: EventRepository,
		aggregateRootPublisher: AggregateRootPublisher
	) = AddSectionInEventUseCase(eventRepository, aggregateRootPublisher)

	@Bean
	fun partnerCreatedListener(
		partnerCreatedEventPublisher: PartnerCreatedEventPublisher
	): PartnerCreatedListener = PartnerCreatedListener(partnerCreatedEventPublisher)
}