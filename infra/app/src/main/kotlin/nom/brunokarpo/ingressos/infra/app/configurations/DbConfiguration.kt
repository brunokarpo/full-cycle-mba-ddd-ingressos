package nom.brunokarpo.ingressos.infra.app.configurations

import nom.brunokarpo.ingressos.infra.database.DatabaseConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [DatabaseConfiguration::class])
class DbConfiguration