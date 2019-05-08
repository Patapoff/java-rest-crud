/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.Objects;

/**
 *
 * @author u17197
 */
public class Aluno {
    
    private String RA;
    private String nome;
    private String emailAluno;

    public Aluno(String RA, String Nome, String EmailAluno) {
        this.RA = RA;
        this.nome = Nome;
        this.emailAluno = EmailAluno;
    }

    public String getRA() {
        return RA;
    }

    public String getNome() {
        return nome;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }

    public void setNome(String Nome) {
        this.nome = Nome;
    }

    public void setEmailAluno(String Email) {
        this.emailAluno = Email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.RA);
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.emailAluno);
        return hash;
    }
    
    public Aluno()
    {}

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aluno other = (Aluno) obj;
        if (!Objects.equals(this.RA, other.RA)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.emailAluno, other.emailAluno)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aluno{" + "RA=" + RA + ", Nome=" + nome + ", Email=" + emailAluno + '}';
    } 
    
}
