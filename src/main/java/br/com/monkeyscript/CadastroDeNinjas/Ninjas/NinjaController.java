package br.com.monkeyscript.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Essa é minha primeira mensagem nessa rota";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja criado";
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public String mostrarTodosOsNinjas() {
        return "Mostrar Ninjas";
    }

    // Mostrar ninja por Id (READ)
    @GetMapping("/listarID")
    public String mostrarTodosOsNinjasPorId() {
        return "Mostrar Ninjas por ID";
    }


    // Alterar dados dos ninjas (UPDATE)
    @PutMapping ("/alterarID")
    public String alterarNinjaPorId() {
        return "Alterar Ninja por ID";
    }

    // Deletar Ninjas (DELETE)
    @DeleteMapping ("/deletarID")
    public String deletarNinjaPorId() {
        return "Ninja deletado por id";
    }

}
