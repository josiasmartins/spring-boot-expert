package io.github.josiasmartins.repository;

import io.github.josiasmartins.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
    List<Cliente> findByNomeLike(String cliente); // queryMethods: faz a declaracao, numa convensão, o spring transforma em query;

    List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

    // retorna apenas um registro por nome
    Cliente findOneByNome(String nome);

    boolean existsByNome(String nome);

//    private static String INSERT = "insert into cliente (nome) values (?)";
//    private static String SELECTED_ALL = "SELECT * FROM CLIENTE";
//    private static String UPDATE = "update cliente set nome = ? where id = ? ";
//    private static String DELETE = "delete from cliente where id = ? ";

//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    private EntityManager entityManager; // faz todas as iteracções nas bases de dados efaz as operações
//
//    @Transactional
//    public void deletar(Cliente cliente) {
////        deletar(cliente.getId());
//        if (!entityManager.contains(cliente)) {
//            cliente = entityManager.merge(cliente);
//        }
//        entityManager.remove(cliente);
//    }
//
//    @Transactional
//    public void deletar(Integer id) {
////        jdbcTemplate.update(DELETE, new Object[]{id});
//        Cliente cliente = entityManager.find(Cliente.class, id);
//        deletar(cliente);
//
//
//    }
//
//    @Transactional(readOnly = true)
//    public List<Cliente> buscarPorNome(String nome) {
////        return jdbcTemplate.query(
////                SELECTED_ALL.concat(" where nome like ? "),
////                new Object[]{"%" + nome + "%"},
////                this.obterClienteMapper()
////        );
//        String jpgl = " select c from Cliente c where c.nome like :nome ";
//        TypedQuery<Cliente> query = entityManager.createQuery(jpgl, Cliente.class);
//        query.setParameter("nome", "%" + nome + "%");
//        return query.getResultList();
//    }
//
//    private RowMapper<Cliente> obterClienteMapper() {
//        return new RowMapper<Cliente>() {
//            @Override
//            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
//                Integer id = resultSet.getInt("id");
//                String nome = resultSet.getString("nome");
//                return new Cliente(id, nome);
//            }
//        };
//    }
//
//    @Transactional
//    public Cliente salvar(Cliente cliente) {
////        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
//        this.entityManager.persist(cliente);
//        return cliente;
//    }
//
//    @Transactional
//    public Cliente atualizar(Cliente cliente) {
////        jdbcTemplate.update(UPDATE, new Object[]{
////                cliente.getNome(), cliente.getId() });
//        entityManager.merge(cliente);
//        return cliente;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Cliente> obterTodos() {
////        return jdbcTemplate.query(SELECTED_ALL, new RowMapper<Cliente>() {
////            @Override
////            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
////                Integer id = resultSet.getInt("id");
////                String nome = resultSet.getString("nome");
////                return new Cliente(id, nome);
////            }
////        });
//
//        return entityManager
//                .createQuery("from Cliente", Cliente.class)
//                .getResultList();
//    }

}
