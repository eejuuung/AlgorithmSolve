import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int pointer = 0;
        for (int i = 1; i <= 30000; i++) {
            String base = String.valueOf(i);

            for (int j = 0; j < base.length(); j++) {
                if (base.charAt(j) == str.charAt(pointer)) {
                    pointer++;
                }

                if (pointer == str.length()) {
                    System.out.println(base);
                    return;
                }
            }
        }

    }
}