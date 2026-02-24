import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
        /*
        나이브 풀이
        전체 숫자 리스트로 저장 -> O(N^2)
        정렬 -> O(N^2logN^2)
        조회 -> O(1)
        가능하겠는데? 근데 아슬아슬함 (8천만)
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n * n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i * n + j] = num;
            }
        }

        Arrays.sort(arr);

        System.out.println(arr[n * n - n]);
    }
}
