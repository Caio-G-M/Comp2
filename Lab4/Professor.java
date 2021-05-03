public class Professor extends Pessoa{

    int anoContratacao;

    public Professor(int anoContratacao){
        super("Sem nome");
        this.anoContratacao = anoContratacao;
    }

    public int getAnoContratacao() {
        return anoContratacao;
    }

}
