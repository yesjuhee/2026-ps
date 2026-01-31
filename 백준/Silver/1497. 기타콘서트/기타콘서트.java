import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    String[] guitars;
    int min; // 기타 최소
    int max; // 곡 최대
    
	public static void main(String[] args) throws IOException {
        /*
        최대한 많은 곡을 제대로 연주하려 할 때, 필요한 기타의 최소 개수
        모든 경우의 수: 2^N가지
        2^N가지 탐색 -> 비트마스킹으로 검사

        그냥 반복문 검사 -> 시간복잡도는 똑같은듯..?
        2^N * N
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        min = n + 1;
        max = 0;
        guitars = new String[n];
        for (int i = 0; i < n; i++) {
            guitars[i] = br.readLine().split(" ")[1];
        }

        for (int t = 0; t < (1<<n); t++) { // 2^N가지 기타 조합
            boolean[] songs = new boolean[m]; // m개의 곡을 다 연주할 수 있는지?
            int guitarCount = 0;
            int songCount = 0;
            for (int g = 0; g < n; g++) {                
                if (((t >> g) & 1) != 1) continue; // g번째 비트가 1인지 체크
                String guitar = guitars[g];
                guitarCount++;
                for (int s = 0; s < m; s++) {
                    if (guitar.charAt(s) == 'Y' && songs[s] == false) {
                        songs[s] = true;
                        songCount++;
                    }
                }
            }

            if (songCount == 0) continue;
            if (songCount == max) {
                min = Math.min(min, guitarCount);
                max = songCount;
            } else if (songCount > max) {
                min = guitarCount;
                max = songCount;
            }

            // System.out.printf("guitarCount: %d, songCount: %d\n", guitarCount, songCount);
            // System.out.printf("t: %d, guitarMin: %d, songMax: %d\n\n", t, min, max);
        }

        if (max == 0) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
	}
}
