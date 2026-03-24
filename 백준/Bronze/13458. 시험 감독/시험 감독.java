import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] arr;
    int n;
    int b, c;
    
	public static void main(String[] args) throws IOException {
        /*
        시험장마다 응시생을 모두 감독하기 위해 필요한 감독관의 최소 수
        1. 각 시험장 응시생 입력
        2. 감독관 입력
        3. 응시생 순회 -> 음수 될 때 까지 빼기
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)  {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        long result = 0;
        for (int i = 0; i < n; i++) {
            int v = arr[i] - b;
            result++;
            if (v <= 0) continue;
            result += v / c;
            if (v % c != 0) result++;
        }

        System.out.println(result);
    }
}
