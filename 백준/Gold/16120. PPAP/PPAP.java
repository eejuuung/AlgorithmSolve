import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<Character> stack = new Stack<>();
        boolean bPPAP = false;
        int length = str.length();

        for (int i = 0; i < length; i++) {
            char chr = str.charAt(i);
            stack.push(chr);
            int num = stack.size();

            if (stack.size() >= 4 &&
                    stack.get(num - 4) == 'P' &&
                    stack.get(num - 3) == 'P' &&
                    stack.get(num - 2) == 'A' &&
                    stack.get(num - 1) == 'P') {
                stack.pop();
                stack.pop();
                stack.pop();
                stack.pop();

                stack.push('P');
            }
        }

        if (stack.isEmpty() || (stack.size() == 1 && stack.peek() == 'P')) {
            bPPAP = true;
        }

        System.out.println(bPPAP ? "PPAP" : "NP");
    }
}