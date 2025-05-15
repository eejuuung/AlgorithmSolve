import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String answer = str;

        boolean errCheck = false;
        boolean bigCheck = false;
        boolean underCheck = false;
        boolean doubleUnderCheck = false;

        for (int i = 0; i < str.length(); i++) {

            // 첫글자가 대문자라면 에러
            if (i == 0) {
                if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
                    continue;
                else {
                    errCheck = true;
                    break;
                }
            }

            // _인경우
            if (str.charAt(i) == '_') {
                underCheck = true;

                // __ 두개 연속이라면 에러
                if (doubleUnderCheck) {
                    errCheck = true;
                    break;
                } else {
                    doubleUnderCheck = true;
                }
            }

            // 대문자인경우
            else if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                bigCheck = true;
            }
            // 소문자인경우
            else {
                doubleUnderCheck = false;
            }

            // 대문자 _모두있다면 에러
            if (bigCheck && underCheck) {
                errCheck = true;
                break;
            }

        }

        // 맨앞, 맨뒤가 _라면 에러
        if (str.charAt(0) == '_' || str.charAt(str.length() - 1) == '_') {
            errCheck = true;
        }

        if (errCheck) {
            answer = "Error!";
            bigCheck = false;
            underCheck = false;
        }

        if (bigCheck) {
            answer = "";

            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                    answer += "_";
                    answer += (char) (str.charAt(i) + ('a' - 'A'));
                } else {
                    answer += str.charAt(i);
                }
            }

        } else if (underCheck) {
            answer = "";

            underCheck = false;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '_') {
                    underCheck = true;
                } else if (underCheck) {
                    answer += (char) (str.charAt(i) - ('a' - 'A'));
                    underCheck = false;
                } else {
                    answer += str.charAt(i);
                }
            }
        }

        System.out.println(answer);
    }
}
