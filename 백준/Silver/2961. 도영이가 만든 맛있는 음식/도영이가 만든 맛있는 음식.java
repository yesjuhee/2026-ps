import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n;
    int[] a; // 신맛
    int[] b; // 쓴맛
    int min = Integer.MAX_VALUE; // 신맛 쓴맛 차이
    
	public static void main(String[] args) throws IOException {
        /*
        신맛과 쓴맛의 차이가 가장 작은 요리
        모든 경우의 수 - 2^N = 모든 경우의 수 가능
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        b = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
        }

        // 2^N가지 경우의 수
        for (int t = 1; t < (1 << n); t++) {
            // t에서 이진수 1인 자릿수만 선택해서 계산하기
            int aa = 1; // 누적곱
            int bb = 0; // 누적 합
            for (int i = 0; i < n; i++) {
                if (((t >> i) & 1) == 0) continue;
                aa *= a[i];
                bb += b[i];
            }
            // 차이 계산해서 최소 판단
            min = Math.min(min, Math.abs(aa - bb));
        }

        System.out.println(min);
	}
}
