package com.stefanini.hackathon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlunoDTO {

    private String nome;

    private String matricula;

    private String cpf;

    private String email;

    private Long idTurma;

    public AlunoDTO(String nome, String matricula, String cpf, String email, Long idTurma) {
        this.nome = nome;
        this.matricula = matricula;
        this.cpf = cpf;
        this.email = email;
        this.idTurma = idTurma;
    }
    
}
