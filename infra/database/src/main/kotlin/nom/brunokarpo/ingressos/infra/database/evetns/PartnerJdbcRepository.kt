package nom.brunokarpo.ingressos.infra.database.evetns

import nom.brunokarpo.ingressos.domain.events.Partner
import nom.brunokarpo.ingressos.domain.events.repository.PartnerRepository
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class PartnerJdbcRepository(
	private val jdbcTemplate: NamedParameterJdbcTemplate
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
		jdbcTemplate.update(sql, params)
	}

	override fun ofId(id: UUID): Partner? {
		val sql = """
			SELECT id, name, cnpj FROM partner WHERE id = :id
		""".trimIndent()
		val params = mapOf("id" to id)
		return jdbcTemplate.query(sql, params) { rs, _ ->
			val name = rs.getString("name")
			val cpnj = rs.getString("cnpj")

			return@query Partner.create(
				id = id,
				name = name,
				cnpj = cpnj
			)
		}.firstOrNull()
	}
}