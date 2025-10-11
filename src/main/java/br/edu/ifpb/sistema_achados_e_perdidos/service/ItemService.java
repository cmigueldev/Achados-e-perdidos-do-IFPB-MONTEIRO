package br.edu.ifpb.sistema_achados_e_perdidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.sistema_achados_e_perdidos.entity.Item;
import br.edu.ifpb.sistema_achados_e_perdidos.repository.ItemRepository;

public class ItemService {

	@Autowired
	protected ItemRepository itemRepository;
	
	public Item save (Item i) {
		return itemRepository.save(i);
	}
	
	
	 /** Retorna uma lista com todos os itens cadastrados.
	 *   @return List<Item> - Lista de itens.
	 */
	public List<Item> findAll() {
		return itemRepository.findAll();
	}
	
	 /** Busca um item pelo seu ID.
	 *   @param id - ID do item a ser buscado.
	 *   @return Item - O item encontrado.
	 *   @throws RuntimeException se o item não for encontrado.
	 */
	public Item findById(Long id) {
		return itemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("item não encontrado"));
	}
	
	 /** Remove um item pelo seu ID.
	 *   @param id - ID do item a ser removido.
	 *   @throws RuntimeException se o item não for encontrado.
	 */
	public void deleteById(Long id) {
		if (!itemRepository.existsById(id)) {
			throw new RuntimeException("item não encontrado");
		}
		itemRepository.deleteById(id);
	}

	 /** Verifica se já existe um item cadastrado com o código informado.
	 *   @param codigo - O código do item a ser verificado.
	 *   @return boolean - true se já existir um item com o código, false caso contrário.
	 */
	public boolean existsByCodigo(String codigo) {
		return itemRepository.existsByCodigo(codigo);
}


}
