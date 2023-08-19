//package io.github.josiasmartins.model;
//
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "cliente") // permite associar o schema também
//public class Cliente {
//
//    @Id
////    @GeneratedValue(strategy = GenerationType.AUTO) // gera o id automaticamente
//    @Column(name = "id") // serve para fazer as definições das colunas
//    private Integer id;
//    @Column(name = "nome", length = 100)
//    private String nome;
//
//    public Cliente(String nome) {
//        this.nome = nome;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    @Override
//    public String toString() {
//        return "Cliente{" +
//                "id=" + id +
//                ", nome='" + nome + '\'' +
//                '}';
//    }
//}
