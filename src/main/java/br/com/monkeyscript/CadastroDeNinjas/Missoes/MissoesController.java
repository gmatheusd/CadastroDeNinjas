package br.com.monkeyscript.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MissoesController {

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Essa é minha mensagem na rota de missoes";
    }
}
