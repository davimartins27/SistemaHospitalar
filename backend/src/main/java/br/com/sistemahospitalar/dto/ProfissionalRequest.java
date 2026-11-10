package br.com.sistemahospitalar.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ProfissionalRequest {
    @NotBlank private String categoria;
    @NotBlank private String nome;
    @NotBlank private String registroProfissional;
    private String telefone;
    @Email private String email;
    private String especialidade;
    private String areaCirurgica;

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getRegistroProfissional() { return registroProfissional; }
    public void setRegistroProfissional(String registroProfissional) { this.registroProfissional = registroProfissional; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public String getAreaCirurgica() { return areaCirurgica; }
    public void setAreaCirurgica(String areaCirurgica) { this.areaCirurgica = areaCirurgica; }
}
