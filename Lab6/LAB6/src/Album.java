import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Album {

    public static final int PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR = 90;

    public static final Image IMAGEM_PADRAO_PARA_POSICAO_VAZIA = null;

    private final Repositorio repositorio;
    private final int quantItensPorPacotinho;

    private List<Colecionavel> colecionaveisColados;  // direct addressing

    private int quantColecionaveisColados;

    // poderíamos fazer novamente direct addressing para as repetidas,
    // mas vamos usar um HashMap aqui só para treinarmos
    private Map<Integer, Integer> contRepetidasByPosicao;

    public Album(Repositorio repositorio, int quantItensPorPacotinho) {
        this.repositorio = repositorio;
        this.quantItensPorPacotinho = quantItensPorPacotinho;

        int tamanhoFisicoDaLista = getTamanho() + 1;
        this.colecionaveisColados = new ArrayList<>(tamanhoFisicoDaLista);
        // inicializa as posições com nulls, para poder acessá-las diretamente
        for (int i = 0; i < tamanhoFisicoDaLista; i++) {
            this.colecionaveisColados.add(null);
        }
        this.quantColecionaveisColados = 0;

        this.contRepetidasByPosicao = new HashMap<>();
    }

    public void receberNovoPacotinho(Pacotinho pacotinho) {
        Colecionavel[] colecionaveisDoPacotinho = pacotinho.getColecionaveisDoPacote();
        if (colecionaveisDoPacotinho.length != this.quantItensPorPacotinho) {

            return;  // melhor ainda: lançaria uma checked exception
        }


        for (Colecionavel col : pacotinho.getColecionaveisDoPacote()) {
            final int posicao = col.getPosicao();
            if (possuiItemColado(posicao)) {
                // adiciona como repetida

                // jeito pior
//                Integer contRepetidas = this.contRepetidasByPosicao.get(posicao);
//                this.contRepetidasByPosicao.put(
//                        posicao, contRepetidas == null ? 1 : contRepetidas + 1);

                // jeito mais elegante: getOrDefault
                int contRepetidas = this.contRepetidasByPosicao.getOrDefault(posicao, 0);
                this.contRepetidasByPosicao.put(posicao, contRepetidas + 1);

            } else {
                // item inédito
                this.colecionaveisColados.set(posicao, col);
                this.quantColecionaveisColados++;
            }
        }
    }

    public Colecionavel getItemColado(int posicao) {
        return this.colecionaveisColados.get(posicao);
    }

    public boolean possuiItemColado(int posicao) {
        if  (posicao > getTamanho() || posicao < 0){
            return false;
        }else if (this.colecionaveisColados.get(posicao) == null) {
            return false;
        }else {
            return true;
        }

    }

    public boolean possuiItemRepetido(int posicao) {
        if (this.contRepetidasByPosicao.get(posicao) == null) {
            return false;
        }
        return true;
    }

    public int getTamanho() {
        return this.repositorio.getTotalColecionaveis();
    }

    public int getQuantColecionaveisColados() {
        return quantColecionaveisColados;
    }

    public int getQuantItensColados() {
//        int contador = 0;
//        for (Figurinha fig : this.figurinhasColadas) {
//            if (fig != null) {
//                contador++;
//            }
//        }
//        return contador;

        // melhor jeito: atributo!
        return this.quantColecionaveisColados;
    }

    public int getQuantItensFaltantes() {
        return getTamanho() - getQuantItensColados();
    }

    public void autoCompletar() {
        if (getQuantColecionaveisColados() < getTamanho()*PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR / 100f) {
            return;
        }else if (getQuantColecionaveisColados() >= getTamanho()*PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR / 100f) {
            this.colecionaveisColados = this.repositorio.getTodosOsColecionaveis();
            this.quantColecionaveisColados = getTamanho();
        }
    }

    private Image getImagem(int posicao) {
        return possuiItemColado(posicao)
                ? this.getItemColado(posicao).getImagem()
                : IMAGEM_PADRAO_PARA_POSICAO_VAZIA;
    }

//    public static void main(String[] args) {
//        ArrayList<Integer> meuArrayList = new ArrayList<>(200);
//
//        // inicializa as posi'c~oes com nulls, para poder acess-las diretamente
//        // inicializa as posições com nulls, para poder acessá-las diretamente
//        for (int i = 0; i < 200; i++) {
//            meuArrayList.add(null);
//        }
//
////        System.out.println(meuArrayList.get(3));
//
//        meuArrayList.add(3, 300000);  // insert com shift right
//
//        for (int numero : meuArrayList) {
//            System.out.println(numero);
//        }
//    }
}