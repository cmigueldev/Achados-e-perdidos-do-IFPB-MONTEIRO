package br.edu.ifpb.sistema_achados_e_perdidos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.edu.ifpb.sistema_achados_e_perdidos.entity.Item;
import br.edu.ifpb.sistema_achados_e_perdidos.entity.Usuario;
import br.edu.ifpb.sistema_achados_e_perdidos.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/form")
    public String home(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastrarUsuario";
    }

    @PostMapping("/save")
    public String saveUsuario(@ModelAttribute Usuario usuario, Model model) {
        if (usuarioService.existsByMatricula(usuario.getMatricula())) {
            model.addAttribute("mensagemErro", "Usuário com matrícula " + usuario.getMatricula() + " já cadastrado.");
            return "cadastrarUsuario";
        } else {
            usuarioService.save(usuario);
        }
        model.addAttribute("mensagemSucesso", "Usuário com matrícula " + usuario.getMatricula() + " cadastrado com sucesso!");
        return listUsuarios(model);
    }
    
    @GetMapping("/edit/{id}")
    public String editUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.findByIdWithItens(id);
        model.addAttribute("usuario", usuario);
        return "editarUsuario";
    }

    @PostMapping("/edit")
    public String editUsuarioPost(@ModelAttribute Usuario usuario, Model model) {
        if ((!usuarioService.findById(usuario.getId()).getMatricula().equals(usuario.getMatricula()))
                && usuarioService.existsByMatricula(usuario.getMatricula())) {
            model.addAttribute("mensagemErro", "Usuário com matrícula " + usuario.getMatricula() + " já existe.");
            return "editarUsuario";
        } else {
            usuarioService.save(usuario);
        }
        model.addAttribute("mensagemSucesso", "Usuário com matrícula " + usuario.getMatricula() + " atualizado com sucesso!");
        return listUsuarios(model);
    }

    @GetMapping("/list")
    public String listUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "listarUsuarios";
    }

    @GetMapping("/delete/{id}")
    public String deleteUsuario(@PathVariable Long id, Model model) {
        List<Item> itens = usuarioService.findByIdWithItens(id).getItens();
		if(itens != null && !itens.isEmpty()) {
			model.addAttribute("mensagemErro", "Não é possível remover o usuário, pois existem itens associados a ele.");
			model.addAttribute("itens", itens);
			return listUsuarios(model);
		}
		else {
			usuarioService.deleteById(id);
			model.addAttribute("mensagemSucesso", "Usuário removido com sucesso");
            model.addAttribute("itens", itens);
			return listUsuarios(model);
			
		}
    }
}
