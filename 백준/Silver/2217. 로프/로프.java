import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int[] ropes;
    
	public static void main(String[] args) throws IOException {
        /*
        로프들을 사용해 들어올릴 수 있는 최대 중량
        5 10 15

        제일 굵은 로프부터
        15 - 15
        15 10 - 20
        15 10 7 - 21
        15 10 7 5 - 20
        15 10 7 5 4 - 20
        => 20이 최대
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        ropes = new int[n];
        for (int i = 0; i < n; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }

        // 중량순 정렬
        Arrays.sort(ropes);

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, ropes[i] * (n - i)); // i번째 사용했을 떄 최대값
        }
        System.out.println(max);
	}
}
