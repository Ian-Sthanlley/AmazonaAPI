package br.com.amazona.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long codigo;
	private String nome;
	private String descricao;
	private String dataEntrada;
	private String dataSaida;
	private String anotacaoSaida;
	private Double valorPago;
	private Double valorAvista;
	private Double valor5Vezes;
	private Double valor10Vezes;
	private Estado estado;
	private EstadoVenda estadoVenda;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private Set<GastoAdicional> GastosAdicionais = new HashSet<>();

	public Produto() {

	}

	public Produto(Long id, Long codigo, String nome, String descricao, String dataEntrada, String dataSaida,
			String anotacaoSaida, Double valorPago, Double valorAvista, Double valor5Vezes, Double valor10Vezes,
			Estado estado, EstadoVenda estadoVenda, Set<GastoAdicional> gastosAdicionais) {
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.anotacaoSaida = anotacaoSaida;
		this.valorPago = valorPago;
		this.valorAvista = valorAvista;
		this.valor5Vezes = valor5Vezes;
		this.valor10Vezes = valor10Vezes;
		this.estado = estado;
		this.estadoVenda = estadoVenda;
		GastosAdicionais = gastosAdicionais;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getAnotacaoSaida() {
		return anotacaoSaida;
	}

	public void setAnotacaoSaida(String anotacaoSaida) {
		this.anotacaoSaida = anotacaoSaida;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public Double getValorAvista() {
		return valorAvista;
	}

	public void setValorAvista(Double valorAvista) {
		this.valorAvista = valorAvista;
	}

	public Double getValor5Vezes() {
		return valor5Vezes;
	}

	public void setValor5Vezes(Double valor5Vezes) {
		this.valor5Vezes = valor5Vezes;
	}

	public Double getValor10Vezes() {
		return valor10Vezes;
	}

	public void setValor10Vezes(Double valor10Vezes) {
		this.valor10Vezes = valor10Vezes;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public EstadoVenda getEstadoVenda() {
		return estadoVenda;
	}

	public void setEstadoVenda(EstadoVenda estadoVenda) {
		this.estadoVenda = estadoVenda;
	}

	public Set<GastoAdicional> getGastosAdicionais() {
		return GastosAdicionais;
	}

	public void setGastosAdicionais(Set<GastoAdicional> gastosAdicionais) {
		GastosAdicionais = gastosAdicionais;
	}

}
