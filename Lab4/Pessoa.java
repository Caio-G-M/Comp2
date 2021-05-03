public class Pessoa {

    int anoDeNascimento;

    String nome;

    public Pessoa(int anoDeNascimento, String nome) {
        this.anoDeNascimento = anoDeNascimento;
        this.nome = nome;
    }

    public Pessoa(String nome){
        this.nome = nome;
    }

    public Pessoa(int anoDeNascimento){
        this.anoDeNascimento = anoDeNascimento;
    }

    public int getAnoNascimento() {
        return this.anoDeNascimento;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

