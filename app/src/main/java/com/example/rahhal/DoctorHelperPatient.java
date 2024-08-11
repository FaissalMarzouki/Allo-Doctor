package com.example.rahhal;

public class DoctorHelperPatient {

    private String name,email,numero,cin,adress,civilite,age,rapport;


    public DoctorHelperPatient(){

    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public DoctorHelperPatient(String name, String email, String age, String numero, String cin, String adress, String civilite, String rapport) {
        this.name = name;
        this.email = email;
        this.numero = numero;
        this.cin = cin;
        this.age=age;
        this.adress = adress;
        this.civilite = civilite;
        this.rapport=rapport;

    }


    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getName() {
        return name;
    }



    public String getEmail() {
        return email;
    }

    public String getNumero() {
        return numero;
    }

    public String getCin() {
        return cin;
    }

    public String getAdress() {
        return adress;
    }

    public String getCivilite() {
        return civilite;
    }


}
