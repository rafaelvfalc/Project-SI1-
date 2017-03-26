package br.edu.ufcg.computacao.si1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.authority.AuthorityUtils;

@Entity(name = "Usuario")
@Table(name = "tb_usuario")
public class Usuario extends org.springframework.security.core.userdetails.User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nome;
    @Column(unique = true)
    private String email;
    @Column
    private String senha;
    @Column
    private String role;
    @Column
    private double saldo;
    @Column
    private String anuncios;

    public Usuario() {
        super("default", "default", AuthorityUtils.createAuthorityList("USER"));
    }
    
    /**
     * 
     * @param nome
     * @param email
     * @param senha
     * @param role
     * @param saldo / foi alterado, pois originalmente o saldo não era um atributo do usuario
     * @param anuncios / ID dos anuncios que o usuario cadastrou
     */
    // alteracao, usuario nao sera passado com saldo, sera 0 ao criar o usuario @filipe
    public Usuario(String nome, String email, String senha, String role) {

        super(email, senha, AuthorityUtils.createAuthorityList(role));

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.saldo = 0.0;
        this.anuncios = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String novoRole) {
        this.role = novoRole;
    }
    
    public double getSaldo() {
    	return saldo;
    }
    
    public void setSaldo(double novoSaldo) {
    	this.saldo = novoSaldo;
    }
    
    public String getAnuncios() {
    	return anuncios;
    }
    
    public void addAnuncios(String idDoAnuncio) {
    	anuncios = anuncios + " " + idDoAnuncio;
    }
    
    //Criei metodo debitar para ser discutido @Filipe
    public void debitarSaldo(double debito) {
    	if(this.saldo >= debito ){
    		this.saldo = this.saldo - debito;
    	}
    }

    //Criei metodo creditar para ser discutido @Filipe
    public void creditarSaldo(double credito) {
    	this.saldo = this.saldo + credito;
    }
    
}
