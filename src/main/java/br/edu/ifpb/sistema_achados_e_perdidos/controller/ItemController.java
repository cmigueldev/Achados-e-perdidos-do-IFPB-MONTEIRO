package br.edu.ifpb.sistema_achados_e_perdidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpb.sistema_achados_e_perdidos.entity.Item;
import br.edu.ifpb.sistema_achados_e_perdidos.service.ItemService;


@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/form")
    public String home(Model model) {
        model.addAttribute("item", new Item());
        return "cadastrarItem";
    }


    @PostMapping("save")
	public String saveItem(@ModelAttribute Item item, Model model) {	
		// Verifica se o item já existe pelo código
		if (itemService.existsByCodigo(item.getCodigo())) {
			model.addAttribute("mensagemErro", "Item com código " + item.getCodigo() + " já cadastrado.");
			return "cadastrarItem";
		} else {			
			itemService.save(item);
		}
		model.addAttribute("mensagemSucesso", "Item com código " + item.getCodigo() + " cadastrado com sucesso!");
		return "cadastrarItem";
	}
    

    @GetMapping("/edit/{id}")
	public String editItem(@PathVariable Long id, Model model) {
		Item item = itemService.findById(id);
		model.addAttribute("item", item);
		return "editarItem";
	}

    @PostMapping("/edit")
	public String editItemPost(@ModelAttribute Item item, Model model) {
		//// Verifica se o código alterado já existe em outro item, exceto ele mesmo
		if((!itemService.findById(item.getId()).getCodigo().equals(item.getCodigo())) && itemService.existsByCodigo(item.getCodigo())) {
			model.addAttribute("mensagemErro", "Item com código " + item.getCodigo() + " já existe.");
			return listItems(model);
		}else {
		itemService.save(item);
		}

		model.addAttribute("mensagemSucesso", "Item com código " + item.getCodigo() + " atualizado com sucesso!");
		return listItems(model);
    }


    @GetMapping("list")
	public String listItems(Model model) {
		model.addAttribute("items", itemService.findAll());
		return "listarItem";
	}


    @GetMapping("/delete/{id}")
	public String deleteItem(@PathVariable Long id, Model model) {
		itemService.deleteById(id);
		model.addAttribute("mensagemSucesso",  "Item deletado com sucesso!");
		return listItems(model);
	}
}


