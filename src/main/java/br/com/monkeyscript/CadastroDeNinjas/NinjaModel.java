package br.com.monkeyscript.CadastroDeNinjas;

import jakarta.persistence.*;

// Entity ele transforma uma classe em uma entidade do BD
// JPA  = Java Persistence API
@Entity
@Table(name = "tb_cadastro")
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private int indade;

    public NinjaModel() {
    }

    public NinjaModel(String nome, String email, int indade) {
        this.nome = nome;
        this.email = email;
        this.indade = indade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIndade() {
        return indade;
    }

    public void setIndade(int indade) {
        this.indade = indade;
    }
}
