import java.io.*;

public class Main {

    static int stringToInt(String val) {
        int result = 0;
        for (int i = 0; i < val.length(); i++) {
            int bit = val.charAt(i) - '0';
            result = (result << 1) | bit;
        }
        return result;
    }

    static String intToString(int val) {
        StringBuilder result = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            int bit = (val >> i) & 1;
            result.append(bit);
        }
        return result.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            int pc = 0;
            int ac = 0;
            int[] memory = new int[32];
            memory[0] = stringToInt(line);

            // 나머지 31줄 읽기
            for (int i = 1; i < 32; i++) {
                memory[i] = stringToInt(br.readLine());
            }

            while (true) {
                int order = memory[pc] / 32;
                int operand = memory[pc] % 32;

                if (order == 7)
                    break;

                pc = (pc + 1) % 32;

                switch (order) {
                    case 0:
                        memory[operand] = ac;
                        break;
                    case 1:
                        ac = memory[operand];
                        break;
                    case 2:
                        if (ac == 0)
                            pc = operand;
                        break;
                    case 4:
                        ac = (ac + 255) % 256;
                        break;
                    case 5:
                        ac = (ac + 1) % 256;
                        break;
                    case 6:
                        pc = operand;
                        break;
                }
            }

            System.out.println(intToString(ac));
        }
    }
}
