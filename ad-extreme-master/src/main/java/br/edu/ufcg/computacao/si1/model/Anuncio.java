package br.edu.ufcg.computacao.si1.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** Classe para objetos do tipo Anuncio,onde serao contidos, valores e metodos para o mesmo
 * Created by Marcus Oliveira on 08/12/16.
 */
@Entity
@Table(name = "tb_anuncio")
public class Anuncio {
	private static final String[] tipos = new String[] { "movel", "imovel", "emprego" }; //{"movel", "imovel", "emprego", "servico"}

	private final static DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "_id", nullable = false, unique = true)
	private Long _id;

	@Column(name = "titulo", nullable = false)
	private String titulo;

	@Column(name = "data_criacao", nullable = false)
	private Date dataDeCriacao;

	@Column(name = "preco", nullable = false)
	private double preco;

	@Column(name = "nota")
	private String nota;

	@Column(name = "tipo", nullable = false)
	private String tipo;
	
	@Column(name = "dono_anuncio", nullable= false)
	private String dono;
	/**Construtor da classe Anuncio com parametros
	 * 
	 * @param titulo
	 * @param dataDeCriacao
	 * @param preco
	 * @param nota
	 * @param tipo
	 */
	public Anuncio(String titulo, Date dataDeCriacao, double preco, String nota, String tipo) {
		this.titulo = titulo;
		this.dataDeCriacao = dataDeCriacao;
		this.preco = preco;
		this.nota = nota;
		this.tipo = tipo;
		dono = "";
	}
	/**Contrutor da classe Anuncio sem parametros
	 * 
	 */
	public Anuncio() {
		titulo = "";
		dataDeCriacao = new Date();
		preco = 0;
		nota = "";
		tipo = "";
		dono = "";
	}

	/** Metodo que retorna o Id do Anuncio
	 * 
	 * @return Id
	 */
	public Long get_id() {
		return _id;
	}

	/** Metodo que altera o Id do Anuncio para um novo Id passado como parametro
	 * 
	 * @param _id
	 */
	public void set_id(Long _id) {
		this._id = _id;
	}
	/** Metodo que retorna o Titulo do Anuncio
	 * 
	 * @return Titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/** Metodo que altera o titulo do anuncio para outro passado como parametro
	 * 
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**Metodo que retorna a data de criacao de um Anuncio
	 * 
	 * @return data de criacao
	 */
	public String getDataDeCriacao() {
		return DATE_FORMAT.format(dataDeCriacao);
	}
	/** Metodo que altera a data de criacao de um anuncio passando outra data como parametro
	 * 
	 * @param dataDeCriacao
	 */
	public void setDataDeCriacao(Date dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	/**Metodo que retorna o preco de um Anuncio
	 * 
	 * @return preco
	 */
	public double getPreco() {
		return preco;
	}
	/**Metodo que altera o preco do Anuncio por outro passado como parametro
	 * 
	 * @param preco
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}
	/** Metodo que retorna a nota dada a um Anuncio
	 * 
	 * @return Nota
	 */
	public String getNota() {
		return nota;
	}
	/**Metodo que altera a nota de um Anuncio por outra passa como parametro 
	 * 
	 * @param nota
	 */
	public void setNota(String nota) {
		this.nota = nota;
	}
	/**Metodo que retorna o tipo do Usuario
	 * 
	 * @return Tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/** Metodo que altera o tipo de Anuncio para outro passado como parametro
	 * 
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**Metodo que retorna o dono do Anuncio
	 * 
	 * @return Dono
	 */
	public String getDono(){
		return dono;
	}
	/**Metodo que altera o dono do Anuncio para outro passado como parametro
	 * 
	 * @param dono
	 */
	public void setDono(String dono){
		this.dono = dono;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Anuncio))
			return false;

		Anuncio anuncio = (Anuncio) o;

		if (Double.compare(anuncio.getPreco(), getPreco()) != 0)
			return false;
		if (!get_id().equals(anuncio.get_id()))
			return false;
		if (!getTitulo().equals(anuncio.getTitulo()))
			return false;
		if (!getDataDeCriacao().equals(anuncio.getDataDeCriacao()))
			return false;
		if (getNota() != null ? !getNota().equals(anuncio.getNota()) : anuncio.getNota() != null)
			return false;
		return getTipo().equals(anuncio.getTipo());

	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = get_id().hashCode();
		result = 31 * result + getTitulo().hashCode();
		result = 31 * result + getDataDeCriacao().hashCode();
		temp = Double.doubleToLongBits(getPreco());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (getNota() != null ? getNota().hashCode() : 0);
		result = 31 * result + getTipo().hashCode();
		return result;
	}

    @Override
    public String toString() {
        return "Anuncio{" +
                "_id=" + _id +
                ", titulo='" + titulo + '\'' +
                ", dataDeCriacao=" + getDataDeCriacao() +
                ", preco=" + preco +
                ", nota=" + nota +
                ", tipo='" + tipo + '\'' +
                ", dono='" + dono +
                '}';
    }
}
