package nom.brunokarpo.ingressos.infra.app.configurations

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["nom.brunokarpo.ingressos.infra.api"] )
class ApiConfiguration