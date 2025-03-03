import java.util.Scanner;

public class Main {

    static StringBuilder sb;
    static final int MAXNODE = 1000;

    static public String countNodesEdges(String[][] entries, int numEntries) {

        boolean[] nodeCheck = new boolean[27];
        boolean[][] edgeCheck = new boolean[27][27];
        int numNodes = 0;
        int numEdges = 0;

        /* ------------------- INSERT CODE HERE ---------------------*/

        for (int i = 0; i < entries.length; i++) {

            if (entries[i] == null)
                break;

            int inum = entries[i][0].charAt(0) - 'a';

            if (!nodeCheck[inum]) {
                nodeCheck[inum] = true;
                numNodes++;
            }

            for (int j = 1; j < entries[i].length; j++) {
                if (entries[i][j] == null)
                    break;

                int iinum = entries[i][j].charAt(0) - 'a';

                int lowNum = Math.min(inum, iinum);
                int highNum = Math.max(inum, iinum);

                if (!edgeCheck[lowNum][highNum]) {
                    edgeCheck[lowNum][highNum] = true;
                    numEdges++;
                }

                if (!nodeCheck[iinum]) {
                    nodeCheck[iinum] = true;
                    numNodes++;
                }
            }
        }

        /* -------------------- END OF INSERTION --------------------*/

        String result = "NODES " + numNodes + " EDGES " + numEdges;
        return result;
    }

    static public void printEntries(String[][] entries, int numEntries) {
        for (int i = 0; i < numEntries; i++) {
            String node1 = entries[i][0];

            System.out.print("Nodes " + node1 + "  neighbors =");

            int j = 1;
            while (entries[i][j] != null) {
                String node2 = entries[i][j];
                System.out.print(" " + node2);
                j++;
            }

            System.out.println();
        }
    }

    static public void main(String[] args) {

        // read the graph
        sb = new StringBuilder();
        Scanner inp = new Scanner(System.in);

        try {
            String line = inp.nextLine();
            if (!line.equals("GRAPH BEGIN"))
                throw new Exception();
            while (inp.hasNext()) {
                if (!inp.hasNext()) throw new Exception();

                // found new graph
                String[][] entries = new String[MAXNODE][];
                int numEntries = 0;

                while (inp.hasNext()) {
                    String ln = inp.nextLine();
                    if (ln.equals("GRAPH END"))    //* end of graph specification
                        break;

                    String[] entry = new String[MAXNODE];
                    int idx = 0;

                    // read in entry (on single line)
                    Scanner sc = new Scanner(ln);
                    if (!sc.hasNext()) throw new Exception();
                    entry[idx++] = sc.next();        // node

                    while (sc.hasNext()) {
                        entry[idx++] = sc.next();    // neighbors
                    }

                    entry[idx] = null;
                    entries[numEntries++] = entry;    // save entry
                }

                // printEntries(entries, numEntries);
                String counts = countNodesEdges(entries, numEntries);
                System.out.println(counts);

                // process lines after graph, if any
                while (inp.hasNext()) {
                    line = inp.nextLine();
                    if (line.equals("GRAPH BEGIN")) {
                        break;
                    }
                }
                
            }
        } catch (Exception e) {
            System.out.println("BAD INPUT FORMAT");
        }
    }

}