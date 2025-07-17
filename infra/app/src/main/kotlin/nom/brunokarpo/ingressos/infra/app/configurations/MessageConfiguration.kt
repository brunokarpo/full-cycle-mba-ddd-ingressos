package nom.brunokarpo.ingressos.infra.app.configurations

import nom.brunokarpo.ingressos.infra.messaging.MessagingConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [MessagingConfiguration::class])
class MessageConfiguration {
}