import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] heigt = new int[N];
        int[] total = new int[N];
        int[] near = new int[N];
        Arrays.fill(near, 100005);

        StringTokenizer stz = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heigt[i] = Integer.parseInt(stz.nextToken());
        }

        // 왼->오
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && heigt[stack.peek()] <= heigt[i]) {
                stack.pop();
            }
            total[i] = stack.size();
            if (!stack.isEmpty())
                near[i] = stack.peek();
            stack.push(i);
        }

        // 오->왼
        stack = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heigt[stack.peek()] <= heigt[i]) {
                stack.pop();
            }
            total[i] += stack.size();

            if (!stack.isEmpty()) {
                if (near[i] == 100005) {
                    near[i] = stack.peek();
                } else {
                    int leftDist = Math.abs(i - near[i]);
                    int rightDist = Math.abs(stack.peek() - i);

                    if (rightDist < leftDist)
                        near[i] = stack.peek();
                }
            }
            stack.push(i);
        }

        for (int i = 0; i < N; i++) {
            if (total[i] == 0)
                sb.append("0\n");
            else
                sb.append(total[i]).append(" ").append(near[i] + 1).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}