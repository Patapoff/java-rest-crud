/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.dbos;

import java.util.Objects;

/**
 *
 * @author u17197
 */
public class Aluno {
    
    private String RA;
    private String Nome;
    private String EmailAluno;

    public Aluno(String RA, String Nome, String EmailAluno) {
        this.RA = RA;
        this.Nome = Nome;
        this.EmailAluno = EmailAluno;
    }

    public String getRA() {
        return RA;
    }

    public String getNome() {
        return Nome;
    }

    public String getEmailAluno() {
        return EmailAluno;
    }

    public void setRA(String RA) {
        this.RA = RA;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public void setEmailAluno(String Email) {
        this.EmailAluno = Email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.RA);
        hash = 53 * hash + Objects.hashCode(this.Nome);
        hash = 53 * hash + Objects.hashCode(this.EmailAluno);
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
        if (!Objects.equals(this.Nome, other.Nome)) {
            return false;
        }
        if (!Objects.equals(this.EmailAluno, other.EmailAluno)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aluno{" + "RA=" + RA + ", Nome=" + Nome + ", Email=" + EmailAluno + '}';
    }
    
    
    
}
