package br.edu.ifpb.sistema_achados_e_perdidos.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifpb.sistema_achados_e_perdidos.entity.Item;
import br.edu.ifpb.sistema_achados_e_perdidos.entity.Usuario;
import br.edu.ifpb.sistema_achados_e_perdidos.service.ItemService;
import br.edu.ifpb.sistema_achados_e_perdidos.service.UsuarioService;


@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;
	@Autowired
	private UsuarioService usuarioService;

	 // --- MODIFICAÇÃO 1: Defina o caminho para salvar as imagens ---
    // Crie esta pasta: src/main/resources/static/images/
    public static String UPLOAD_DIRECTORY = "uploads";



    @GetMapping("/form")
    public String home(Model model) {
		usuarios(model);
        model.addAttribute("item", new Item());
        return "cadastrarItem";
    }

	private void usuarios(Model model) {
		List<Usuario> usuarios = usuarioService.findAll();
		model.addAttribute("usuarios", usuarios);
	}

    // --- MODIFICAÇÃO 2: Método saveItem completamente alterado ---
	@PostMapping("/save")
	public String saveItem(@ModelAttribute Item item, 
                           @RequestParam("image") MultipartFile file,
                           RedirectAttributes redirectAttributes) {

        /* 
        if (item.getId() == null && itemService.existsByCodigo(item.getCodigo())) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Item com código " + item.getCodigo() + " já cadastrado.");
            redirectAttributes.addFlashAttribute("item", item);
            return "redirect:/item/form";
        }
        */

        if (!file.isEmpty()) {
            try {
                // Gera um nome de arquivo único para evitar sobrescrever imagens com o mesmo nome
                String originalFilename = file.getOriginalFilename();
                String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
                
                Path uploadPath = Paths.get(UPLOAD_DIRECTORY);
                // Garante que o diretório de uploads exista
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path fileNameAndPath = uploadPath.resolve(uniqueFilename);
                Files.write(fileNameAndPath, file.getBytes());

                // **MUDANÇA CRÍTICA**: Salva o caminho da URL, não o caminho do sistema
                item.setFotoDoItem("/uploads/" + uniqueFilename);

            } catch (Exception e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("mensagemErro", "Ocorreu um erro ao salvar a imagem.");
                redirectAttributes.addFlashAttribute("item", item);
                return "redirect:/item/form";
            }
        } else if (item.getId() == null) {
            redirectAttributes.addFlashAttribute("mensagemErro", "A foto do item é obrigatória.");
            redirectAttributes.addFlashAttribute("item", item);
            return "redirect:/item/form";
        }

        // Gera e define o código único antes de salvar
        if (item.getId() == null) { // Garante que isso só aconteça na criação de um novo item
            item.setCodigo(UUID.randomUUID().toString());
        }
		
		itemService.save(item);
		redirectAttributes.addFlashAttribute("mensagemSucesso", "Item cadastrado com sucesso!");
		
        // **MUDANÇA CRÍTICA**: Redireciona para a lista para que ela seja recarregada do banco
		return "redirect:/item/list";
	}
/* 
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
		return listItems(model);

	}   
    */

    @GetMapping("/edit/{id}")
	public String editItem(@PathVariable Long id, Model model) {
		usuarios(model);
		Item item = itemService.findById(id);
		model.addAttribute("item", item);
		return "editarItem";
	}

	// O método de edição também precisa ser ajustado para o upload de novas fotos.
    // Por enquanto, vamos focar no cadastro.
    @PostMapping("/edit")
	public String editItemPost(@ModelAttribute Item item, 
                               // O nome do parâmetro deve ser "image" para corresponder ao formulário
                               @RequestParam("image") MultipartFile file,
                               RedirectAttributes redirectAttributes) {
		
        // Verifica se o código foi alterado para um que já existe em outro item
        Item itemOriginal = itemService.findById(item.getId());
        if (!itemOriginal.getCodigo().equals(item.getCodigo()) && itemService.existsByCodigo(item.getCodigo())) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Já existe um item com o código " + item.getCodigo() + ".");
            // Adiciona o item de volta para não perder os dados digitados
            redirectAttributes.addFlashAttribute("item", item);
            return "redirect:/item/edit/" + item.getId();
        }

        // Se um novo arquivo foi enviado, salva a nova imagem
        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
                Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, uniqueFilename);
                Files.write(fileNameAndPath, file.getBytes());
                item.setFotoDoItem("/uploads/" + uniqueFilename);
            } catch (Exception e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("mensagemErro", "Ocorreu um erro ao salvar a nova imagem.");
                return "redirect:/item/edit/" + item.getId();
            }
        } else {
            // Se nenhum arquivo novo foi enviado, mantém a foto antiga
            item.setFotoDoItem(itemOriginal.getFotoDoItem());
        }

        itemService.save(item);

		redirectAttributes.addFlashAttribute("mensagemSucesso", "Item com código " + item.getCodigo() + " atualizado com sucesso!");
		
        // Redireciona para a lista de itens para ver a alteração
		return "redirect:/item/list";
    }




	/* 
    @PostMapping("/edit")
	public String editItemPost(@ModelAttribute Item item, Model model) {
		//// Verifica se o código alterado já existe em outro item, exceto ele mesmo
		if((!itemService.findById(item.getId()).getCodigo().equals(item.getCodigo())) && itemService.existsByCodigo(item.getCodigo())) {
			model.addAttribute("mensagemErro", "Item com código " + item.getCodigo() + " já existe.");
			return "editarItem";
		}else {
		itemService.save(item);
		}

		model.addAttribute("mensagemSucesso", "Item com código " + item.getCodigo() + " atualizado com sucesso!");
		return listItems(model);
    }
	*/


    @GetMapping("/list")
	public String listItems(Model model) {
		model.addAttribute("itens", itemService.findAll());
		return "telaInicial";
	}

    @GetMapping("/delete/{id}")
	public String deleteItem(@PathVariable Long id, Model model) {
		itemService.deleteById(id);
		model.addAttribute("mensagemSucesso",  "Item deletado com sucesso!");
		return listItems(model);
	}
}


