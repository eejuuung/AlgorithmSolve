import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        boolean isOkay;
        Stack<Character> stack;

        while (!str.equals(".")) {
            stack = new Stack<>();
            isOkay = true;

            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(' || str.charAt(i) == '[') {
                    stack.add(str.charAt(i));
                } else if (str.charAt(i) == ')') {
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        isOkay = false;
                        break;
                    }
                } else if (str.charAt(i) == ']') {
                    if (!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        isOkay = false;
                        break;
                    }
                }

            }

            if (!stack.isEmpty())
                isOkay = false;

            if (isOkay)
                sb.append("yes").append("\n");
            else
                sb.append("no").append("\n");

            str = br.readLine();
        }

        System.out.print(sb);
    }
}
