import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m; // n개의 기타, m개의 곡
    long[] guitars;
    int min; // 기타 최소
    int max; // 곡 최대
    
	public static void main(String[] args) throws IOException {
        /*
        최대한 많은 곡을 제대로 연주하려 할 때, 필요한 기타의 최소 개수
        모든 경우의 수: 2^N가지
        2^N가지 탐색 -> 비트마스킹으로 검사
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        guitars = new long[n];
        min = n + 1;
        max = 0;
        for (int i = 0; i < n; i++) {
            String song = br.readLine().split(" ")[1];
            long s = 0;

            // 뒤에서부터 하나씩 밀면서 채우기
            for (int j = m - 1; j >= 0; j--) {
                guitars[i] = (guitars[i] << 1) | (song.charAt(j) == 'Y' ? 1 : 0);
            }            
        }

        // 2^n가지 경우의 수
        for (int t = 0; t < (1 << n); t++) {
            // 기타들끼리 비트연산 후, 곡 개수 구하기
            // 1이 있으면 계속 생존 -> OR 연산
            long songBits = 0;
            int guitarCount = 0;
            for (int i = 0; i < n; i++) {
                if (((t >> i) & 1) != 1) continue; // i번째 자리 확인
                guitarCount++;
                // i 번째 기타가 연주할 수 있는 곡 확인
                songBits |= guitars[i];
            }
            int songCount = count(songBits);

            // System.out.printf("t: %d, guitarCount: %d, songCount: %d, songBits: %d\n", t, guitarCount, songCount, songBits);
            // System.out.printf("min: %d, max: %d\n", min, max);

            // 최대한 많은 곡이 맞는가?
            if (songCount < max || songCount == 0) continue;
            if (songCount > max) {
                min = guitarCount;
                max = songCount;
                continue;
            }
            if (songCount == max) {
                min = Math.min(min, guitarCount);
                max = songCount;
            }
        }

        // 출력
        if (max == 0) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
	}

    public int count(long bits) {
        // 1의 개수를 세는 함수
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            cnt += (bits >> i) & 1;
        }
        return cnt;
    }
}
