public enum Status {
    ABERTO("Aberto"), APROVADO("Aprovado"), REPROVADO("Reprovado"), CONCLUIDO("Concluído");

    private final String status; 
    private Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    @Override
    public String toString() {
        String texto = "";
        texto += "" + status;
        return texto;        
    }
}