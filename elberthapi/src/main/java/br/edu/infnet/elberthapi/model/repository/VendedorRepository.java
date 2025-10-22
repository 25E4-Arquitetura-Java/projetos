package br.edu.infnet.elberthapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.elberthapi.model.domain.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

}
