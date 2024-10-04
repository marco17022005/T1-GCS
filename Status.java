public enum Status {
    ABERTO("Aberto"), APROVADO("Aprovado"), REPROVADO("Reprovado");

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
        texto += "Status: " + status;
        return texto;        
    }
}