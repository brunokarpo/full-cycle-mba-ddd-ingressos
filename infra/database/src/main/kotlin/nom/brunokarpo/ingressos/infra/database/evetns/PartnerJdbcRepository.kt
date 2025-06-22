package nom.brunokarpo.ingressos.infra.database.evetns

import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.repository.PartnerRepository
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class PartnerJdbcRepository(
	private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) : PartnerRepository {

	override fun save(aggregate: Partner) {
		val sql = """
            INSERT INTO partner(id, name, cnpj)
                VALUES (:uuid, :name, :cnpj)
        """.trimIndent()
		val params = mapOf(
			"uuid" to aggregate.id,
			"name" to aggregate.name,
			"cnpj" to aggregate.cnpj.value
		)
		namedParameterJdbcTemplate.update(sql, params)
	}
}