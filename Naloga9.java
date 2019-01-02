import java.io.*;
import java.util.*;

class Struct {

    //struct contains array of nodes
    //each node represents a person and contains his id and a set of his friends

    Node[] array_of_people;

    class Node {

        int id;
        Set<Integer> friends;

        Node (int id, Set<Integer> friends) {

            this.id = id;
            this.friends = friends;
        }
    }

    public void init (int st) {

        //maximum amount of possible nodes out of N pairs is 2*N + 1 (+1 as id's start with 1, not with 0)

        array_of_people = new Node[st * 2 + 1];
    }

    public void work (String data, PrintWriter output) {

        //this function processes input "a,b"

        String[] d = data.split(",");
        int a = Integer.parseInt(d[0]);
        int b = Integer.parseInt(d[1]);

        //if a node with id "a" or "b" doesn't exist, it's created here
        //each person is also friend with himself, this ensures that when expanding someones circle of friends, everyone is added

        if (array_of_people[a] == null) {

            Node an = new Node(a, null);
            an.friends = new HashSet<>();
            array_of_people[a] = an;
            an.friends.add(a);
        }

        if (array_of_people[b] == null) {

            Node bn = new Node(b, null);
            bn.friends = new HashSet<>();
            array_of_people[b] = bn;
            bn.friends.add(b);
        }

        //friendship between a and b is added here, add(a,b) returns true if such friendship is already present
        //useless instruction is then printed

        if (add(a, b))
            output.printf("%S\n", data);
    }

    public boolean add (int a, int b) {

        //if a and b are already friends, return true

        if (array_of_people[a].friends.contains(b))
            return true;

        //add all b's friends to a

        array_of_people[a].friends.addAll(array_of_people[b].friends);

        //expand the circle of friends to all people that are in a's circle of friends

        for (int iterate : array_of_people[a].friends)
            array_of_people[iterate].friends = array_of_people[a].friends;

        return false;
    }
}

public class Naloga9 {

    public static void main (String[] args) throws IOException{

        if (args.length == 2) {

            final long st = System.currentTimeMillis();

            BufferedReader input = new BufferedReader(
                    new FileReader(args[0])
            );

            PrintWriter output = new PrintWriter(
                    new FileWriter(args[1])
            );

            Struct struct = new Struct();

            int input_line_amount = Integer.parseInt(input.readLine());

            struct.init(input_line_amount);

            for (int i = 0; i < input_line_amount; i++)
                struct.work(input.readLine(), output);

            final long et = System.currentTimeMillis();

            System.out.println("Time: " + (et - st));

            input.close();
            output.close();
        }
    }
}
