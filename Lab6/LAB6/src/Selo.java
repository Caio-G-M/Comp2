import java.awt.*;

public class Selo implements Colecionavel{

    private final float valorNominal;
    private final Image imagem;
    private final String pais;
    private final int posicao;

    public Selo (int posicao, String pais, float valorNominal, String urlDaImagem) {
        this.pais = pais;
        this.posicao = posicao;
        this.valorNominal = valorNominal;
        this.imagem = obterImagem(urlDaImagem);
    }

    public Selo (int posicao, String urlDaImagem) {
        this.posicao = posicao;
        this.imagem = obterImagem(urlDaImagem);
        this.pais = "pais nao informado";
        this.valorNominal = 1.99f;

    }

    private Image obterImagem(String url) {
        return null;
    }

    float getValorNominal() {
        return this.valorNominal;
    }

    String getPais() {
        return this.pais;
    }

    @Override
    public Image getImagem() {
        return this.imagem;
    }

    @Override
    public int getPosicao() {
        return this.posicao;
    }
}
