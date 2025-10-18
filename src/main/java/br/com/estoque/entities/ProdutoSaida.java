package br.com.estoque.entities;

import java.time.LocalDate;
import java.util.UUID;

import br.com.estoque.enums.RuasEmObra;
import br.com.estoque.enums.SetorDestino;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_saida_produto")
public class ProdutoSaida {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private LocalDate dataSaida;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SetorDestino destino;
  
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private RuasEmObra ruasEmObra;
    
    @Column(nullable = true)
    private String observacao;
    
    
	public RuasEmObra getRuasEmObra() {
		return ruasEmObra;
	}

	public void setRuasEmObra(RuasEmObra ruasEmObra) {
		this.ruasEmObra = ruasEmObra;
	}

	public SetorDestino getDestino() {
		return destino;
	}

	public void setDestino(SetorDestino destino) {
		this.destino = destino;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}



}
