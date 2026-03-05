import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n, m, k;
    Map<String, Integer> counts = new HashMap<>();
    char[][] map;

    int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
    
	public static void main(String[] args) throws IOException {
        /*
        8개의 방향으로 이동 가능 
        환형으로 이동 가능
        
        문자열의 길이 최대 5
        N * M -> 모든 경우의 수 -> N * M * (8 * 문자열 길이) * K 가지 -> 4,000,000가지

        1~5 길이의 모든 경우의 수 다 찾아 놓고, K개의 입력에 따라 찾기
        -> N * M * 8 * 5 -> 4,000

        탐색 -> BFS + 지나온 길이 표시(StringBuilder?)
        */
        new Main().solution();
	}

    public void solution() throws IOException {
        // 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = row.charAt(j);
            }
        }

        // 탐색
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < m; j++) {
                Pair pair = new Pair(i, j, new char[]{map[i][j]});
                bfs(pair);
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            String key = br.readLine();
            int value = counts.get(key) == null ? 0 : counts.get(key);
            sb.append(value).append("\n");
        }
        System.out.println(sb);
    }

    void bfs(Pair start) {
        Deque<Pair> q = new ArrayDeque<>();
        q.addLast(start);
        
        while(!q.isEmpty()) {
            Pair now = q.removeFirst();
            add(new String(now.str));
            if (now.str.length == 5) continue;
            for (int i = 0; i < 8; i++) {
                int nx = (now.x + dx[i] + n) % n;
                int ny = (now.y + dy[i] + m) % m;
                
                char[] str = new char[now.str.length + 1];
                charCopy(now.str, str, map[nx][ny]);
                
                Pair next = new Pair(nx, ny, str);
                q.addLast(next);
            }
        }
    }

    void charCopy(char[] fromChar, char[] toChar, char lastChar) {
        for (int i = 0; i < fromChar.length; i++) {
            toChar[i] = fromChar[i];
        }
        toChar[fromChar.length] = lastChar;
    }

    void add(String key) {
        int value = counts.getOrDefault(key, 0) + 1;
        counts.put(key, value);
    }

    class Pair {
        int x, y;
        char[] str;

        Pair(int x, int y, char[] str) {
            this.x = x;
            this.y = y;
            this.str = str;
        }
    }
}
