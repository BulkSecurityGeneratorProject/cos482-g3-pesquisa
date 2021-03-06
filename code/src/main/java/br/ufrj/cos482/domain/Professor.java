package br.ufrj.cos482.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Professor.
 */
@Entity
@Table(name = "professor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "matricula", nullable = false)
    private String matricula;

    @NotNull
    @Column(name = "link_lattes", nullable = false)
    private String linkLattes;

    @Column(name = "programa")
    private String programa;

    @Column(name = "linha_de_pesquisa")
    private String linhaDePesquisa;

    @Column(name = "areas_de_interesse")
    private String areasDeInteresse;

    @OneToMany(mappedBy = "orientador")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Aluno> alunos = new HashSet<>();

    @OneToMany(mappedBy = "organizadorProfessor")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Seminario> seminarios = new HashSet<>();

    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ParticipacaoBanca> participacaoBancas = new HashSet<>();

    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Reuniao> reuniaos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "professor_copublicacao",
               joinColumns = @JoinColumn(name="professors_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="copublicacaos_id", referencedColumnName="id"))
    private Set<Publicacao> copublicacaos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "professor_coorientando",
               joinColumns = @JoinColumn(name="professors_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="coorientandos_id", referencedColumnName="id"))
    private Set<Aluno> coorientandos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Professor nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public Professor matricula(String matricula) {
        this.matricula = matricula;
        return this;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getLinkLattes() {
        return linkLattes;
    }

    public Professor linkLattes(String linkLattes) {
        this.linkLattes = linkLattes;
        return this;
    }

    public void setLinkLattes(String linkLattes) {
        this.linkLattes = linkLattes;
    }

    public String getPrograma() {
        return programa;
    }

    public Professor programa(String programa) {
        this.programa = programa;
        return this;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getLinhaDePesquisa() {
        return linhaDePesquisa;
    }

    public Professor linhaDePesquisa(String linhaDePesquisa) {
        this.linhaDePesquisa = linhaDePesquisa;
        return this;
    }

    public void setLinhaDePesquisa(String linhaDePesquisa) {
        this.linhaDePesquisa = linhaDePesquisa;
    }

    public String getAreasDeInteresse() {
        return areasDeInteresse;
    }

    public Professor areasDeInteresse(String areasDeInteresse) {
        this.areasDeInteresse = areasDeInteresse;
        return this;
    }

    public void setAreasDeInteresse(String areasDeInteresse) {
        this.areasDeInteresse = areasDeInteresse;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public Professor alunos(Set<Aluno> alunos) {
        this.alunos = alunos;
        return this;
    }

    public Professor addAluno(Aluno aluno) {
        this.alunos.add(aluno);
        aluno.setOrientador(this);
        return this;
    }

    public Professor removeAluno(Aluno aluno) {
        this.alunos.remove(aluno);
        aluno.setOrientador(null);
        return this;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Set<Seminario> getSeminarios() {
        return seminarios;
    }

    public Professor seminarios(Set<Seminario> seminarios) {
        this.seminarios = seminarios;
        return this;
    }

    public Professor addSeminario(Seminario seminario) {
        this.seminarios.add(seminario);
        seminario.setOrganizadorProfessor(this);
        return this;
    }

    public Professor removeSeminario(Seminario seminario) {
        this.seminarios.remove(seminario);
        seminario.setOrganizadorProfessor(null);
        return this;
    }

    public void setSeminarios(Set<Seminario> seminarios) {
        this.seminarios = seminarios;
    }

    public Set<ParticipacaoBanca> getParticipacaoBancas() {
        return participacaoBancas;
    }

    public Professor participacaoBancas(Set<ParticipacaoBanca> participacaoBancas) {
        this.participacaoBancas = participacaoBancas;
        return this;
    }

    public Professor addParticipacaoBanca(ParticipacaoBanca participacaoBanca) {
        this.participacaoBancas.add(participacaoBanca);
        participacaoBanca.setProfessor(this);
        return this;
    }

    public Professor removeParticipacaoBanca(ParticipacaoBanca participacaoBanca) {
        this.participacaoBancas.remove(participacaoBanca);
        participacaoBanca.setProfessor(null);
        return this;
    }

    public void setParticipacaoBancas(Set<ParticipacaoBanca> participacaoBancas) {
        this.participacaoBancas = participacaoBancas;
    }

    public Set<Reuniao> getReuniaos() {
        return reuniaos;
    }

    public Professor reuniaos(Set<Reuniao> reuniaos) {
        this.reuniaos = reuniaos;
        return this;
    }

    public Professor addReuniao(Reuniao reuniao) {
        this.reuniaos.add(reuniao);
        reuniao.setProfessor(this);
        return this;
    }

    public Professor removeReuniao(Reuniao reuniao) {
        this.reuniaos.remove(reuniao);
        reuniao.setProfessor(null);
        return this;
    }

    public void setReuniaos(Set<Reuniao> reuniaos) {
        this.reuniaos = reuniaos;
    }

    public Set<Publicacao> getCopublicacaos() {
        return copublicacaos;
    }

    public Professor copublicacaos(Set<Publicacao> publicacaos) {
        this.copublicacaos = publicacaos;
        return this;
    }

    public Professor addCopublicacao(Publicacao publicacao) {
        this.copublicacaos.add(publicacao);
        publicacao.getCoautorProfessors().add(this);
        return this;
    }

    public Professor removeCopublicacao(Publicacao publicacao) {
        this.copublicacaos.remove(publicacao);
        publicacao.getCoautorProfessors().remove(this);
        return this;
    }

    public void setCopublicacaos(Set<Publicacao> publicacaos) {
        this.copublicacaos = publicacaos;
    }

    public Set<Aluno> getCoorientandos() {
        return coorientandos;
    }

    public Professor coorientandos(Set<Aluno> alunos) {
        this.coorientandos = alunos;
        return this;
    }

    public Professor addCoorientando(Aluno aluno) {
        this.coorientandos.add(aluno);
        aluno.getCoorientadors().add(this);
        return this;
    }

    public Professor removeCoorientando(Aluno aluno) {
        this.coorientandos.remove(aluno);
        aluno.getCoorientadors().remove(this);
        return this;
    }

    public void setCoorientandos(Set<Aluno> alunos) {
        this.coorientandos = alunos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Professor professor = (Professor) o;
        if (professor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), professor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Professor{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", matricula='" + getMatricula() + "'" +
            ", linkLattes='" + getLinkLattes() + "'" +
            ", programa='" + getPrograma() + "'" +
            ", linhaDePesquisa='" + getLinhaDePesquisa() + "'" +
            ", areasDeInteresse='" + getAreasDeInteresse() + "'" +
            "}";
    }
}
