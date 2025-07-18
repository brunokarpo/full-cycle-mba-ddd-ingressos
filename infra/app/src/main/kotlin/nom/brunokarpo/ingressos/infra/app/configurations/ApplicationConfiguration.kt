package nom.brunokarpo.ingressos.infra.app.configurations

import nom.brunokarpo.ingressos.application.listeners.PartnerCreatedListener
import nom.brunokarpo.ingressos.application.usecases.AddSectionInEventUseCase
import nom.brunokarpo.ingressos.application.usecases.CreateNewEventUseCase
import nom.brunokarpo.ingressos.application.usecases.CreatePartnerUseCase
import nom.brunokarpo.ingressos.domain.common.AggregateRootNotifier
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
		aggregateRootNotifier: AggregateRootNotifier
	) = CreatePartnerUseCase(partnerRepository, aggregateRootNotifier)

	@Bean
	fun createNewEventUseCase(
		partnerRepository: PartnerRepository,
		eventRepository: EventRepository,
		aggregateRootNotifier: AggregateRootNotifier
	) = CreateNewEventUseCase(partnerRepository, eventRepository, aggregateRootNotifier)

	@Bean
	fun addSectionInEventUseCase(
		eventRepository: EventRepository,
		aggregateRootNotifier: AggregateRootNotifier
	) = AddSectionInEventUseCase(eventRepository, aggregateRootNotifier)

	@Bean
	fun partnerCreatedListener(
		partnerCreatedEventPublisher: PartnerCreatedEventPublisher
	): PartnerCreatedListener = PartnerCreatedListener(partnerCreatedEventPublisher)
}