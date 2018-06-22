package uniba.neoclassifier.entity;

import java.io.Serializable;

public class Paziente implements Serializable {
    private String nome;
    private String cognome;
    private String dataDiNascita;
    private String email;
    private String fileName;

    public Paziente() {
        this.nome = "";
        this.cognome = "";
        this.dataDiNascita = "";
        this.email = "";
        this.fileName = "userregistered";
    }

    public Paziente(String nome, String cognome, String dataDiNascita, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.email = email;
        this.fileName = "userregistered";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
