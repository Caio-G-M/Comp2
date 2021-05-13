import java.util.*;

public class EncontrarPar {

    static void encontrarPar(List<Integer> lista, int k){

        HashSet<Integer> HashSetDeInt = new HashSet<>();

        for (int i : lista) {
            HashSetDeInt.add(i);
        }

        for (Integer j : lista) {
            if (HashSetDeInt.contains(k-j)) {
                System.out.printf("O par de numeros (%d, %d), quando somados equivalem a %d.\n", j, k-j, k);
                break;
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(5);
        l.add(-10);
        l.add(56);
        l.add(44);
        l.add(12);
        l.add(18);

        encontrarPar(l, 34);
    }

}
