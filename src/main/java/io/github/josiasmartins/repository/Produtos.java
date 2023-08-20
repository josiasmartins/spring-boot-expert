package io.github.josiasmartins.repository;

import io.github.josiasmartins.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
