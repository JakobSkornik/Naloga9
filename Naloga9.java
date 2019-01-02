import java.io.*;
import java.util.*;

//java Naloga9 C:\Users\Jakob\Desktop\test.txt C:\Users\Jakob\Desktop\izhod1.txt

class Struct {

    //struct contains array of nodes
    //each node represents a person and contains his id and a set of his friends

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

        //maximum amount of possible nodes out of N pairs is 2*N + 1 (+1 as id's start with 1, not with 0)

        lista = new Node[st * 2 + 1];
    }

    public void work(String data, PrintWriter izhod) {

        //this function processes input "a,b"

        String[] d = data.split(",");
        int a = Integer.parseInt(d[0]);
        int b = Integer.parseInt(d[1]);

        //if a node with id "a" or "b" doesn't exist, it's created here
        //each person is also friend with himself, this ensures that when expanding someones circle of friends, everyone is added

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

        //friendship between a and b is added here, add(a,b) returns true if such friendship is already present
        //useless instruction is then printed

        if (add(a, b))
            izhod.printf("%S\n", data);
    }

    public boolean add (int a, int b) {

        //if a and b are already friends, return true

        if (lista[a].prijatelji.contains(b))
            return true;

        //add all b's friends to a

        lista[a].prijatelji.addAll(lista[b].prijatelji);

        //expand the circle of friends to all people that are in a's circle of friends

        for (int iterate : lista[a].prijatelji)
            lista[iterate].prijatelji = lista[a].prijatelji;

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
