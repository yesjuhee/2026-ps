import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m;
    Set<String> prefixes = new HashSet<>();

	public static void main(String[] args) throws IOException {
        /*
        문자열 10,000개, 
        길이 최대 500 -> 접두사 최대 500개
        10,000 * 500 -> 5,000,000 개 Set -> 포함되어 있는지 확인

        접두사 만들기 -> O(N * |S|) -> 여기에 String 생성하는 비용도 넣어줘야 하나?
        -> 그렇다면 O(N * S^2) -> 25 * 10^8 -> 시간 고과
        접두사 찾기 -> O(M)
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        // 접두사 초기화
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int s = 1; s <= str.length(); s++) {
                prefixes.add(str.substring(0, s));
            }
        }
        // 접두사 찾기
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            String str = br.readLine();
            if (prefixes.contains(str)) cnt++;
        }

        System.out.println(cnt);
    }
}
