package br.com.fiap.cervejaria.entity;

import br.com.fiap.cervejaria.dto.CreateCervejaDTO;
import br.com.fiap.cervejaria.dto.Tipo;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.auditing.config.AuditingConfiguration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "TB_CERVEJA")
@EntityListeners(AuditingEntityListener.class)
public class Cerveja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String marca;

    @Column
    private Double teorAlcoolico;

    @Column
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column
    private BigDecimal preco;

    @Column
    private ZonedDateTime dataLancamento;

    @Column(name="data_criacao")
    @CreatedDate
    private Date dataCriacao;

    @Column(name="data_atualizacao")
    @LastModifiedDate
    private Date dataAlteracao;

    public Cerveja() {
    }

    public Cerveja(CreateCervejaDTO createCervejaDTO) {
        this.marca = createCervejaDTO.getMarca();
        this.preco = createCervejaDTO.getPreco();
        this.dataLancamento = createCervejaDTO.getDataLancamento();
        this.teorAlcoolico = createCervejaDTO.getTeorAlcoolico();
        this.tipo = createCervejaDTO.getTipo();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getTeorAlcoolico() {
        return teorAlcoolico;
    }

    public void setTeorAlcoolico(Double teorAlcoolico) {
        this.teorAlcoolico = teorAlcoolico;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public ZonedDateTime getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(ZonedDateTime dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
