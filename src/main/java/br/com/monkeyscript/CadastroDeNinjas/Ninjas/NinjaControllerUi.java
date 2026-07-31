package br.com.monkeyscript.CadastroDeNinjas.Ninjas;

import br.com.monkeyscript.CadastroDeNinjas.Missoes.MissoesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("ninjas/ui")
public class NinjaControllerUi {

    private final NinjaService ninjaService;
    private final MissoesService missoesService;

    public NinjaControllerUi(NinjaService ninjaService, MissoesService missoesService) {
        this.ninjaService = ninjaService;
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas =  ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas";
    }

    @GetMapping("/deletar/{id}")
    public String deletarNinjaPorId(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/listar/{id}")
    public String listarNinjasPorId(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja !=null) {
            model.addAttribute("ninja", ninja);
            return "detalhesNinja";
        } else {
            model.addAttribute("mensagem", "Ninja não encontrado");
            return "listarNinjas";
        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarNinja(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        model.addAttribute("missoes", missoesService.listarMissoes());
        return "adicionarNinja";
    }

    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute NinjaDTO ninja, RedirectAttributes redirectAttributes) {
        ninjaService.criarNinja(ninja);
        redirectAttributes.addFlashAttribute("mensagem", "Ninja Cadastrado!");
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/alterar/{id}")
    public String mostrarFormularioAlterar(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            model.addAttribute("missoes", missoesService.listarMissoes());
            return "alterarNinja";
        }
        return "redirect:/ninjas/ui/listar";
    }

    @PostMapping("/alterar/{id}")
    public String alterarNinja(
            @PathVariable Long id,
            @ModelAttribute NinjaDTO ninjaAtualizado,
            RedirectAttributes redirectAttributes) {
        ninjaService.alterarNinjaPorId(id, ninjaAtualizado);
        redirectAttributes.addFlashAttribute(
                "mensagem",
                "Ninja alterado com sucesso!"
        );
        return "redirect:/ninjas/ui/listar";
    }

}
