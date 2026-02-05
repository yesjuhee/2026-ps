import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, k;
    long[] cable;

	public static void main(String[] args) throws IOException {
        /*
        최적화 문제: N개를 만들 수 있는 랜선의 최대 길이
        결정 문제: 랜선의 길이가 X일 때 랜선이 N개 이상인가 아닌가?
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        cable = new long[k];
        for (int i = 0; i < k; i++) {
            cable[i] = Long.parseLong(br.readLine());
        }

        System.out.println(upperBound(n) - 1);
    }

    // target 개수 만큼 만들 수 있는 길이의 최대값 구하기
    // target을 구할 수 있는 가장 큰 길이 찾기
    // n 개 보다 많이 만들어도 된다
    long upperBound(int targetCount) {
        long start = 1;
        long end = 1L << 31;

        while(start < end) {
            long mid = (start + end) / 2;
            long midCount = count(mid);

            /*
            midCount > targetCount -> mid 초과
            midCount < targetCount -> mid 이하
            midCount == targetCount -> mid 초과
            */
            if (midCount >= targetCount) {
                start = mid + 1L;
            } else {
                end = mid;
            } 
        }
        
        return start;
    }

    // len 만큼 자를 때 만들 수 있는 총 케이블 수
    long count(long len) {
        long cnt = 0;
        for (int i = 0; i < k; i++) {
            cnt += cable[i] / len;
        }
        return cnt;
    }
}
