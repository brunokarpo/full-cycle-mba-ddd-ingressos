package nom.brunokarpo.ingressos.infra.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@EnableAutoConfiguration
@ComponentScan
@Configuration
class MessagingConfiguration {

	@Bean
	fun objectMapper(): ObjectMapper {
		val mapper = ObjectMapper()
		mapper.registerModule(JavaTimeModule())
		return mapper
	}
}