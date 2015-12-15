package net.dprogrammer.crudbasico_dpnet;

import java.io.Serializable;

/**
 * Created by dprogrammer on 14/12/2015.
 */
public class Contato implements Serializable {

    private static final long serialVersionUID = 348309483948L;

    private long id;
    private String nome, sobrenome, email;

    public Contato(){}

    public Contato(long id, String nome, String sobrenome, String email) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.nome + " " + this.sobrenome;
    }
}
