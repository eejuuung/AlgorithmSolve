import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        StringTokenizer stz;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            stz = new StringTokenizer(br.readLine());
            stz.nextToken();
            int b = Integer.parseInt(stz.nextToken());

            while (!stack.isEmpty() && stack.peek() > b) {
                stack.pop();
                answer++;
            }

            if (!stack.isEmpty() && stack.peek() == b) {
                continue;
            }

            stack.push(b);
        }
        while (!stack.isEmpty()) {
            if (stack.peek() > 0)
                answer++;
            stack.pop();
        }

        System.out.println(answer);

    }
}