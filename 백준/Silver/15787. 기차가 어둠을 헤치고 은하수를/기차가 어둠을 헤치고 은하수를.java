import java.io.*;
import java.util.*;

class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] states; // n개의 기차의 m개의 좌석 상태
    int result = 0; // 최종 기차의 수 카운트
    int n, m;

	public static void main(String[] args) throws IOException {
        /*
        N개의 기차, 20개의 좌석, M개의 명령
        겹치면 안됨
        은하수를 건널 수 있는 기차의 수
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        states = new int[n];

        // m 개 명령 수행
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken()) - 1; // 기차 번호
            if (cmd == 1) {
                int x = Integer.parseInt(st.nextToken());
                // x번째 좌석에 추가
                states[t] = states[t] | (1 << (x - 1));
            } else if (cmd == 2) {
                int x = Integer.parseInt(st.nextToken());
                // x 번째 좌석 하차
                int mask = 0;
                for (int j = 0; j < 20; j++) {
                    mask = (mask << 1) | ((j == 20 - x) ? 0 : 1);
                }
                states[t] = states[t] & mask;
            } else if (cmd == 3) {
                states[t] = (states[t] << 1) & 0xFFFFF;
            } else {
                states[t] = states[t] >> 1;
            }
            // 모든 기차의 상태 출력
            // System.out.printf("%d번째 명령\n", i + 1);
            // for (int j = 0; j < n; j++) {
            //     System.out.printf("%d: %032d\n", j + 1, Integer.parseInt(Integer.toBinaryString(states[j])));
            // }
        }

        // n개 기차 상태 확인 + 결과 카운트
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int train = states[i];
            if (results.contains(train)) continue;
            results.add(train);
            result++;
        }

        System.out.println(result);
	}
}
