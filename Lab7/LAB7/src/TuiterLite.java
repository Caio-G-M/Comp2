import java.util.*;

/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 */
public class TuiterLite {

    public static int TAMANHO_MAXIMO_TUITES = 120;
    List<Usuario> listaDeUsuarios;
    HashMap<String, Integer> ocorrenciasByHashtag;

    public TuiterLite() {
        this.listaDeUsuarios = new ArrayList<>();
        this.ocorrenciasByHashtag = new HashMap<>();
    }


    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     *
     * @param nome  O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) {  // throws UsuarioJaExisteException {
        Usuario usuarioNovo = new Usuario(nome, email);
        this.listaDeUsuarios.add(usuarioNovo);
        return usuarioNovo;
    }

    /**
     * @param usuario O autor do tuíte
     * @param texto   O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     * <p>
     * PS.: Se o texto exceder o limite pré-definido, ou o usuário não estiver cadastrado, return null
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) {
        if (texto.length() > TAMANHO_MAXIMO_TUITES) {
            return null;
        } else if (!(this.listaDeUsuarios.contains(usuario))) {
            return null;
        }

        Set<String> hashtagsNoTuite = new HashSet<>();
        String[] tuiteSeparadoEmPalavras = texto.split("([^(a-z|A-Z|0-9|ã-ü|Ã-Ü|#)])+");
        for (String palavra : tuiteSeparadoEmPalavras) {
            if (palavra.charAt(0) == '#') {
                palavra.replaceAll("#+", "");
                hashtagsNoTuite.add(palavra);
                if (!(this.ocorrenciasByHashtag.containsKey(palavra))) {
                    this.ocorrenciasByHashtag.put(palavra, 1);
                } else {
                    int i = this.ocorrenciasByHashtag.get(palavra);
                    this.ocorrenciasByHashtag.put(palavra, i + 1);
                }
            }
        }

        Tuite tuite = new Tuite(usuario, texto, hashtagsNoTuite);

        return tuite;

    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     *
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */
    public String getHashtagMaisComum() {

        if (this.ocorrenciasByHashtag.size() == 0) {
            return null; //nao ha hashtags
        }

        int maiorNumeroDeOcorrencias = 0;
        String hashtagMaisComum = new String();
        for (String hashtag : this.ocorrenciasByHashtag.keySet()) {
            if (this.ocorrenciasByHashtag.get(hashtag) > maiorNumeroDeOcorrencias) {
                maiorNumeroDeOcorrencias = this.ocorrenciasByHashtag.get(hashtag);
                hashtagMaisComum = hashtag;
            }
        }
        return hashtagMaisComum;
    }
}
