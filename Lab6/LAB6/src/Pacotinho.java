import java.util.Random;

public class Pacotinho {

    public Colecionavel[] colecionaveisDoPacote;

    public Repositorio repositorio;

    public Pacotinho(Repositorio repo, int[] posicoesDesejadas) {
        this.repositorio = repo;
        int colecionaveisPorPacote = 0;
        for (int i : posicoesDesejadas) {
            colecionaveisPorPacote++;
        }
        Colecionavel[] array = new Colecionavel[colecionaveisPorPacote];
        int contador = 0;
        for (int i : posicoesDesejadas) {
            array[contador] = this.repositorio.getColecionavelDoRepositorio(i);
            contador++;
        }
        this.colecionaveisDoPacote = array;
    }

    /**
     * Produz um pacotinho com quantColecionaveis sorteadas aleatoriamente
     * dentre aqueles presentes no repositório informado.
     *
     * @param repo o repositório desejado
     * @param quantColecionaveis a quantidade de colecionaveis a constar no pacotinho
     */
    public Pacotinho(Repositorio repo, int quantColecionaveis) {
        this.repositorio = repo;
        Colecionavel[] array = new Colecionavel[quantColecionaveis];
        int contador = 0;

        while (contador < quantColecionaveis) {
            array[contador] = this.repositorio.getColecionavelDoRepositorio(new Random().nextInt(this.repositorio.getTotalColecionaveis() - 1) + 1);
            contador++;
        }
        this.colecionaveisDoPacote = array;

    }

    public Colecionavel[] getColecionaveisDoPacote() {
        return this.colecionaveisDoPacote;
    }
}