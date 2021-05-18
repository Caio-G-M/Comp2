import java.util.ArrayList;
import java.util.List;

public class Repositorio {

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";

    public List<Colecionavel> todosOsColecionaveis;

    public Repositorio(String sufixoUrlImagens, int quantColecionaveis) {
        todosOsColecionaveis = new ArrayList<>(quantColecionaveis + 1);
        todosOsColecionaveis.add(null);
        for (int i = 1; i <= quantColecionaveis; i++) {
            Figurinha fig = new Figurinha(i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
            todosOsColecionaveis.add(fig);
        }
    }

    public int getTotalColecionaveis() {
        return this.todosOsColecionaveis.size() - 1;
    }

    public List<Colecionavel> getTodosOsColecionaveis() {
        return todosOsColecionaveis;
    }

    public Colecionavel getColecionavelDoRepositorio(int posicao) {
        return this.todosOsColecionaveis.get(posicao);
    }

}
