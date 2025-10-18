package br.edu.ifpb.sistema_achados_e_perdidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.sistema_achados_e_perdidos.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    boolean existsByMatricula(String matricula);

    // retorna um usuario com seus itens associados
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.itens WHERE u.id = :id")
    Usuario findByIdWithItens(@Param("id") Long id);

}
