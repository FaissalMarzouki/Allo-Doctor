package com.example.rahhal;

public class PatientHelper {


    private  String name="null",email="null",numero="null",cin="null",adress="null",civilite="null",age="null",rapport="null";

    public PatientHelper(String name, String email, String age, String numero, String cin, String adress, String civilite,String rapport) {
        this.name = name;
        this.email = email;
        this.numero = numero;
        this.cin = cin;
        this.age=age;
        this.adress = adress;
        this.civilite = civilite;
        this.rapport = rapport;

    }

    public PatientHelper() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
