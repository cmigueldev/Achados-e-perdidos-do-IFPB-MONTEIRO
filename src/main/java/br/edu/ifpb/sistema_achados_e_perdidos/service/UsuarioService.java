package br.edu.ifpb.sistema_achados_e_perdidos.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ifpb.sistema_achados_e_perdidos.entity.Usuario;
import br.edu.ifpb.sistema_achados_e_perdidos.repository.UsuarioRepository;

@Service
public class UsuarioService {

    
	@Autowired
	protected UsuarioRepository usuarioRepository;
	
	public Usuario save (Usuario u) {
		return usuarioRepository.save(u);
	}
	
	
	 /** Retorna uma lista com todos os usuários cadastrados.
	 *   @return List<Usuario> - Lista de usuários.
	 */
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	 /** Busca um usuário pelo seu ID.
	 *   @param id - ID do usuário a ser buscado.
	 *   @return Usuario - O usuário encontrado.
	 *   @throws RuntimeException se o usuário não for encontrado.
	 */
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("usuário não encontrado"));
	}
	
	 /** Remove um usuário pelo seu ID.
	 *   @param id - ID do usuário a ser removido.
	 *   @throws RuntimeException se o usuário não for encontrado.
	 */
	public void deleteById(Long id) {
		if (!usuarioRepository.existsById(id)) {
			throw new RuntimeException("usuário não encontrado");
		}
		usuarioRepository.deleteById(id);
	}

	 /** Verifica se já existe um usuário cadastrado com a matrícula informada.
	 *   @param matricula - A matrícula do usuário a ser verificada.
	 *   @return boolean - true se já existir um usuário com a matrícula, false caso contrário.
	 */
	public boolean existsByMatricula(String matricula) {
		return usuarioRepository.existsByMatricula(matricula);
    }

	// retorna um usuario com seus itens associados
	public Usuario findByIdWithItens(Long id) {
		return usuarioRepository.findByIdWithItens(id);
	}
	
}
