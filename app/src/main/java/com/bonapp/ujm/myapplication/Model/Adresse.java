package com.bonapp.ujm.myapplication.Model;

/**
 * Created by maham on 24/02/2018.
 */

public class Adresse {
    public int id;
    public String numero;
    public String type_voie;
    public String intitule;
    public String code_postal;

    public Adresse(){

    }

    public Adresse(String numero, String type_voie, String intitule, String code_postal) {
        this.id = id;
        this.numero = numero;
        this.type_voie = type_voie;
        this.intitule = intitule;
        this.code_postal = code_postal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getType_voie() {
        return type_voie;
    }

    public void setType_voie(String type_voie) {
        this.type_voie = type_voie;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }
}
