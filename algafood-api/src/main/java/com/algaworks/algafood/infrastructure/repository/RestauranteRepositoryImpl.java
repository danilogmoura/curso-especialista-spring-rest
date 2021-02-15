package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        var jpql = new StringBuilder("SELECT r FROM Restaurante r WHERE 0 = 0 ");

        var parametros = new HashMap<String, Object>();

        if (StringUtils.hasLength(nome)) {
            jpql.append("AND r.nome LIKE :nome ");
            parametros.put("nome", "%" + nome + "%");
        }

        if (taxaFreteInicial != null) {
            jpql.append("AND r.taxaFrete >= :taxaFreteInicial ");
            parametros.put("taxaFreteInicial", taxaFreteInicial);
        }

        if (taxaFreteFinal != null) {
            jpql.append("AND r.taxaFrete <= :taxaFreteFinal ");
            parametros.put("taxaFreteFinal", taxaFreteFinal);
        }

        TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
        parametros.forEach((key, value) -> query.setParameter(key, value));

        return query.getResultList();
    }
}
