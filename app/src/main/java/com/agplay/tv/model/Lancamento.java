package com.agplay.tv.model;

public class Lancamento {
    private int id, tipo;
    private String titulo, sinopse, genero, duration, diretor, pg, year, rating, bg, footer, trailer, serv1, serv2, link1, link2, child;



    public Lancamento(int id, int tipo, String titulo, String sinopse, String genero, String duration, String diretor, String pg, String year, String rating, String bg, String footer, String trailer, String serv1, String serv2, String link1, String link2, String child) {
        this.id = id;
        this.tipo = tipo;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.genero = genero;
        this.duration = duration;
        this.diretor = diretor;
        this.pg = pg;
        this.year = year;
        this.rating = rating;
        this.bg = bg;
        this.footer = footer;
        this.trailer = trailer;
        this.serv1 = serv1;
        this.serv2 = serv2;
        this.link1 = link1;
        this.link2 = link2;
        this.child = child;
    }

    public int getId() {
        return id;
    }

    public int getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public String getGenero() {
        return genero;
    }

    public String getDuration() {
        return duration;
    }

    public String getDiretor() {
        return diretor;
    }

    public String getPg() {
        return pg;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public String getBg() {
        return bg;
    }

    public String getFooter() {
        return footer;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getServ1() {
        return serv1;
    }

    public String getServ2() {
        return serv2;
    }

    public String getLink1() {
        return link1;
    }

    public String getLink2() {
        return link2;
    }

    public String getChild() {
        return child;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public void setPg(String pg) {
        this.pg = pg;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setServ1(String serv1) {
        this.serv1 = serv1;
    }

    public void setServ2(String serv2) {
        this.serv2 = serv2;
    }

    public void setLink1(String link1) {
        this.link1 = link1;
    }

    public void setLink2(String link2) {
        this.link2 = link2;
    }

    public void setChild(String child) {
        this.child = child;
    }
}

