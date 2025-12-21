import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        boolean[] alpa = new boolean[26];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            String[] strArr = str.split(" ");
            boolean isokay = false;

            // 단어의 첫글자 단축키
            String makeWord = "";
            for (int j = 0; j < strArr.length; j++) {
                String word = strArr[j];

                int num = 0;
                if (word.charAt(0) <= 90)
                    num = word.charAt(0) - 65;
                else
                    num = word.charAt(0) - 97;

                if (!alpa[num] && !isokay) {
                    makeWord += ("[" + word.charAt(0) + "]" + word.substring(1));
                    alpa[num] = true;
                    isokay = true;
                } else
                    makeWord += (word);

                if (j + 1 != strArr.length)
                    makeWord += " ";

            }
            if (isokay)
                sb.append(makeWord).append("\n");

            // 차례대로 지정
            makeWord = "";
            if (!isokay) {
                for (int j = 0; j < str.length(); j++) {
                    if (str.charAt(j) != ' ' && !isokay) {
                        int num = 0;
                        if (str.charAt(j) <= 90)
                            num = str.charAt(j) - 65;
                        else
                            num = str.charAt(j) - 97;

                        if (!alpa[num]) {
                            alpa[num] = true;
                            makeWord += ("[" + str.charAt(j) + "]");
                            isokay = true;
                            continue;
                        }
                    }
                    makeWord += str.charAt(j);
                }

                if (isokay) {
                    sb.append(makeWord).append("\n");
                }
            }

            // 무엇도 지정안됨
            if (!isokay) {
                sb.append(str).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
