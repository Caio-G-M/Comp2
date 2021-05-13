import java.util.Collections;
import java.util.HashMap;

public class LetraMaisFrequente {

    static void encontrarCaracterMaisFrequente(String string) {

        HashMap<Character, Integer> ocorrenciasByLetra = new HashMap<>();
        char charMaisOcorrente = ' ';
        int maisOcorrencias = 1;

        for (int i = 0; i < string.length(); i++) {
            char caracter = string.charAt(i);

            if (ocorrenciasByLetra.containsKey(caracter)) {
                ocorrenciasByLetra.put(caracter, ocorrenciasByLetra.get(caracter) + 1);
                if((ocorrenciasByLetra.get(caracter) + 1) > maisOcorrencias) {
                    maisOcorrencias = ocorrenciasByLetra.get(caracter) + 1;
                    charMaisOcorrente = caracter;
                }
            }else {
                ocorrenciasByLetra.put(caracter, 1);
            }
        }

        int maiorOcorrencia = Collections.max(ocorrenciasByLetra.values());
        System.out.printf("A letra mais frequente e '%c', com %d ocorrencias.\n", charMaisOcorrente, maiorOcorrencia);

    }

    public static void main(String[] args) {
        String arara = "arara";
        encontrarCaracterMaisFrequente(arara);
    }

}
