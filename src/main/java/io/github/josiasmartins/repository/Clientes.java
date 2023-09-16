package io.github.josiasmartins.repository;

import io.github.josiasmartins.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface Clientes extends JpaRepository<Cliente, Integer> {



    // select c from Cliente c where c.nome like :nome
//    List<Cliente> findByNomeLike(String cliente); // queryMethods: faz a declaracao, numa convensão, o spring transforma em query;

    @Query(value = " select * from Cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

    // retorna apenas um registro por nome
    Cliente findOneByNome(String nome);

    @Query(" delete from Cliente c where c.nome =:nome ")
    @Modifying
    void deleteByNome(String nome);

    boolean existsByNome(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id =:id ") // jpql
    Cliente findClienteFetchPedidos(@Param("id") Integer id);


}
