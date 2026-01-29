import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
        /*
        1. 길이가 짧은 것
        2. 숫자를 더해서 합이 작은 것
        3. 사전순 (숫자<알파벳)
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        int n = Integer.parseInt(br.readLine());
        Serial[] arr = new Serial[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Serial(br.readLine());
        }

        // 정렬
        Arrays.sort(arr, (a, b) -> {
            if (a.length == b.length && a.sum == b.sum) {
                return a.str.compareTo(b.str);
            } else if (a.length == b.length) {
                return a.sum - b.sum;
            } else {
                return a.length - b.length;
            }
        });

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i].str).append("\n");
        }
        System.out.println(sb);
	}

    class Serial {
        int length;
        String str;
        int sum;
        
        public Serial(String str) {
            this.str = str;
            this.length = str.length();
            this.sum = sum(str);
        }

        private int sum(String str) {
            int sum = 0;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (Character.isDigit(c)) {
                    sum += Character.getNumericValue(c);
                }
            }
            return sum;
        }
    }
}
