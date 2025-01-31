import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stz = new StringTokenizer(br.readLine());

        List<Stack<Integer>> stacks = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            stacks.add(new Stack<>());
        }

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(stz.nextToken());
            boolean isin = false;

            for (int j = 0; j < 4; j++) {
                if (stacks.get(j).isEmpty() || stacks.get(j).peek() < num) {
                    stacks.get(j).add(num);
                    isin = true;
                    break;
                }
            }

            if (!isin) {
                System.out.println("NO");
                return;
            }
        }

        for (int i = N; i >= 1; i--) {
            boolean isin = false;
            for (int j = 0; j < 4; j++) {
                if (!stacks.get(j).isEmpty() && stacks.get(j).peek() == i) {
                    stacks.get(j).pop();
                    isin = true;
                    break;
                }
            }

            if (!isin) {
                System.out.println("NO");
                return;
            }
        }


        System.out.println("YES");
    }
}