package br.edu.ifpb.sistema_achados_e_perdidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ifpb.sistema_achados_e_perdidos.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

    boolean existsByCodigo(String codigo);

    // verificar se existe item para um usuario
	boolean existsByUsuarioId(Long usuarioId);
}
