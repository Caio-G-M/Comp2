public class TamanhoMaximoExcedidoException extends Exception{

    private int tamanho;


    public TamanhoMaximoExcedidoException(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getTamanhoTexto() {
        return tamanho;
    }
}


