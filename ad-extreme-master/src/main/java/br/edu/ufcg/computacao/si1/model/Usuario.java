package br.edu.ufcg.computacao.si1.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.authority.AuthorityUtils;

/**Classe para Objetos do Tipo Usuario, onde serao contidos, valores e metodos para o mesmo
 * 
 * @author Project SI
 *  
 *
 */

@Entity(name = "Usuario")
@Table(name = "tb_usuario")
public class Usuario extends org.springframework.security.core.userdetails.User {
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
	private String favoritos;
    @Column
    private String anuncios;
    
    public Usuario() {
        super("default", "default", AuthorityUtils.createAuthorityList("USER"));
    }
    
    
    /**
     * Construtor da classe usuario passando os parametros abaixo
     * @param nome - Nome do Usuario
     * @param email - Email do Usuario
     * @param senha - Senha do Usuario
     * @param role - Atributo responsavel pela informação de qual o tipo de usuario está logado,pessoa física ou jurídica.
     */
    public Usuario(String nome, String email, String senha, String role) {

        super(email, senha, AuthorityUtils.createAuthorityList(role));
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.saldo = 0.0;
        this.anuncios = "";
		favoritos = "";
	}
    /**
     * 
     * @return - Identificador do Usuario
     */
	public Long getId() {
		return id;
	}
	/**Muda o ID do Usuario para um novo ID passado como parametro
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**Metodo para retorno do nome do Usuario 
	 * 
	 * @return
	 */
	public String getNome() {
		return nome;
	}
	/**Metodo para alterar o nome do Usuario para outro nome passado como parametro
	 * 
	 * @param novoNome
	 */
	public void setNome(String novoNome) {
		this.nome = novoNome;
	}
	/** Metodo para retorno do email do usuario
	 * 
	 * @return email do usuario
	 */
	public String getEmail() {
		return email;
	}
	/** Metodo para alterar o Email do Usuario para outro email passado como parametro
	 * 
	 * @param email - novo email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/** Metodo para retorno da senha do Usuario
	 * 
	 * @return - senha do Usuario
	 */
	public String getSenha() {
		return senha;
	}
	/** Metodo para alterar a senha do Usuario para outra senha passada como parametro
	 * 
	 * @param senha - nova senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	/**Metodo para retorno do role do Usuario
	 * 
	 * @return role do usuario
	 */
	public String getRole() {
		return role;
	}
	/** Metodo que altera o role do Usuario para outro passado como parametro
	 * 
	 * @param novoRole
	 */
	public void setRole(String novoRole) {
		this.role = novoRole;
	}
	/** Metodo para retorno do saldo do Usuario
	 * 
	 * @return saldo do Usuario
	 */
	public double getSaldo() {
		return saldo;
	}
	/** Metodo que altera o saldo do Usuario para outro saldo passado como parametro
	 * @param novo Saldo do Usuario
	 */
	public void setSaldo(double novoSaldo) {
		this.saldo = novoSaldo;
	}
	
	public String toString() {
		return String.format("%s{Nome=%s, Contato='%s'}", role, nome, email);
	}
	/** Metodo que retorna um campo que o Usuario favoritou dentro de uma colecao de favoritos
	 * 
	 * @return campo favorito de um Usuario
	 */
	public Collection<String> getFavoritos() {
		String[] favoritos = this.favoritos.split(", ");
		ArrayList<String> retorno = new ArrayList<String>();
		for(String email: favoritos){
			retorno.add(email);
		}
		return retorno;
	}
	/** Metodo que altera um favorito do Usuario para outro passado como parametro
	 * 
	 * @param favoritos
	 */
	public void setFavoritos(String favoritos) {
		this.favoritos = favoritos;
	}
	/** Metodo que adiciona um novo favorito dentro de um colecao de favoritos
	 * 
	 * @param emailDoFavoritado
	 */
	public void addFavorito(String emailDoFavoritado) {
		if(favoritos.isEmpty()){
			favoritos = emailDoFavoritado;
		} else {
			favoritos += ", "+emailDoFavoritado;
		}
	}
	/** Metodo responsável por excluir um favorito dentro de uma colecao
	 * 
	 * @param emailDoFavoritado - email do Usuario que é proprietario do favorito
	 */
	public void removeFavorito(String emailDoFavoritado) {
		String[] listaFavoritos = favoritos.split(", ");

		this.favoritos = "";
		for(String favorito: listaFavoritos){
			if(!favorito.equals(emailDoFavoritado)){
				addFavorito(favorito);				
			}
		}
	}
	/** Metodo que retorna os anuncios que o Usuario escolheu dentro de uma colecao de Anuncios
	 * 
	 * @return
	 */
    public Collection<String> getAnuncios(){
    	String[] listaAnuncios = anuncios.split(", ");
    	ArrayList<String> retorno = new ArrayList<String>();
    	for(String idAnuncio: listaAnuncios){
    		retorno.add(idAnuncio);
    	}
    	return retorno;
    }
    /** Metodo que adciona um novo anuncio
     * 
     * @param idDoAnuncio - Id do anuncio a ser adicionado
     */
    public void addAnuncio(String idDoAnuncio){
    	if(anuncios.isEmpty()){
    		anuncios += idDoAnuncio;
    	} else {
    		anuncios += ", "+idDoAnuncio;
    	}
    }
    /** Metodo que remove um anuncio passado com parametro
     * 
     * @param idDoAnuncio - Id do anuncio a ser removido
     */
    public void removeAnuncio(String idDoAnuncio){
    	String[] listaAnuncios  = anuncios.split(", ");
    	anuncios ="";
    	for(String anuncio: listaAnuncios){
    		if(!anuncio.equals(idDoAnuncio)){
    			addAnuncio(anuncio);
    		}
    	}
    }
    /** Metodo que desconta o saldo do Usuario
     * 
     * @param debito
     */
    public void debitarSaldo(double debito) {
    		this.saldo = this.saldo - debito;
    }
    /**Metodo que credita o saldo do Usuario
     * 
     * @param credito
     */
    public void creditarSaldo(double credito) {
    	this.saldo = this.saldo + credito;
    }
}
