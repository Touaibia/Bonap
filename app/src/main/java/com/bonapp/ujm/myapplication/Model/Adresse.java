package com.bonapp.ujm.myapplication.Model;

/**
 * Created by maham on 24/02/2018.
 */

public class Adresse {
    public long id;
    public String numero;
    public String type_voie;
    public String intitule;
    public int code_postal;
    public long id_retau;

    public Adresse(){

    }

    public Adresse(String numero, String type_voie, String intitule, int code_postal, long idr) {
        this.id = id;
        this.numero = numero;
        this.type_voie = type_voie;
        this.intitule = intitule;
        this.code_postal = code_postal;
        this.id_retau = idr;
    }

    public Adresse(long id,String numero, String type_voie, String intitule, int code_postal) {
        this.id = id;
        this.numero = numero;
        this.type_voie = type_voie;
        this.intitule = intitule;
        this.code_postal = code_postal;
    }

    public long getId() {
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

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public long getId_retau() {
        return id_retau;
    }

    public void setId_retau(long id_retau) {
        this.id_retau = id_retau;
    }
}
