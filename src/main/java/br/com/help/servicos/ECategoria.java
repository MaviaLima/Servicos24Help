package br.com.help.servicos;

public enum ECategoria {
	 CONTRUCAO("Construção Civil"),
	   EDUCAÇÃO("Educação"),
	   SAÚDE("Saúde"),
	   INFORMATICA("Informática"),
	   ESTETICABELEZA("Estética e Beleza"),
	   AUTOEMOTOS("Autos e Motos"),
	   EVENTOS("Eventos"),
	   VESTUÁRIO("Vestuário"),
	   SERVIÇOSNOLAR("Serviços no lar"),
	   CULINARIA("Culinária"),
	   SERVICOSGERAIS("Serviços Gerais");
	
	    String text;

	    ECategoria(String t) {
	        this.text = t;
	    }

	    public String getText() {
	        return this.text;
	    }
}
