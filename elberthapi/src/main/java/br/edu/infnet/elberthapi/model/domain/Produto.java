package br.edu.infnet.elberthapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "A descrição do produto é obrigatória.")
	private String descricao;

	@NotNull(message = "O valor do produto é obrigatório.")
	@DecimalMin(value = "0.01", message = "O valor do produto deve ser positivo.")
	private Double valor;

	@NotNull(message = "O tipo do produto é obrigatório.")
	@Enumerated(EnumType.STRING)
	private TipoProduto tipoProduto;
	
	@ManyToOne
	@JoinColumn(name = "vendedor_id", nullable = false)
	private Vendedor vendedor;
	
    @Override
    public String toString() {

        return String.format(
            "Produto [ID: %d | Descrição: %s | Valor: R$ %.2f | Tipo: %s]",
            id,
            descricao,
            valor,
            tipoProduto != null ? tipoProduto.getDescricao() : "N/A"
        );
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
}