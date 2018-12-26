import java.io.*;
import java.util.*;

//java Naloga9 C:\Users\User\Desktop\test.txt C:\Users\User\Desktop\out.txt

class Struct {

    Node[] lista;

    class Node {

        int id;
        Set<Integer> prijatelji;

        Node(int id, Set<Integer> prijatelji) {

            this.id = id;
            this.prijatelji = prijatelji;
        }
    }

    public void init(int st) {

        lista = new Node[st * 2 + 1];
    }

    public void work(String data, PrintWriter izhod) {

        String[] d = data.split(",");
        int a = Integer.parseInt(d[0]);
        int b = Integer.parseInt(d[1]);


        if (lista[a] == null) {

            Node an = new Node(a, null);
            an.prijatelji = new HashSet<>();
            lista[a] = an;
            an.prijatelji.add(a);
        }

        if (lista[b] == null){

            Node bn = new Node(b, null);
            bn.prijatelji = new HashSet<>();
            lista[b] = bn;
            bn.prijatelji.add(b);
        }

        if (add(a, b))
            izhod.printf("%S\n", data);
    }

    public boolean add (int a, int b) {

        if (lista[a].prijatelji.contains(b))
            return true;

        lista[a].prijatelji.addAll(lista[b].prijatelji);

        for (int iterate : lista[a].prijatelji)
            lista[iterate].prijatelji.addAll(lista[a].prijatelji);

        return false;
    }
}

public class Naloga9 {

    public static void main(String[] args) throws IOException{

        if (args.length == 2) {

            final long st = System.currentTimeMillis();

            BufferedReader vhod = new BufferedReader(
                    new FileReader(args[0])
            );

            PrintWriter izhod = new PrintWriter(
                    new FileWriter(args[1])
            );

            Struct struct = new Struct();

            int stevilo = Integer.parseInt(vhod.readLine());

            struct.init(stevilo);

            for (int i = 0; i < stevilo; i++)
                struct.work(vhod.readLine(), izhod);

            final long et = System.currentTimeMillis();

            System.out.println("Cas: " + (et - st));

            vhod.close();
            izhod.close();
        }
    }
}
