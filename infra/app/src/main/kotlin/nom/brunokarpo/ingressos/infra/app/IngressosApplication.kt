package nom.brunokarpo.ingressos.infra.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IngressosApplication

fun main(args: Array<String>) {
    runApplication<IngressosApplication>(*args)
}