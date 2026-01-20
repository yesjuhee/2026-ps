import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = 12;
    int m = 6;
    char[][] board = new char[n][m];
    int result = 0;

    boolean[][] visited;
    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, 1, -1};
    
	public static void main(String[] args) throws IOException {
        /*
        연쇄가 몇 번 연속으로 일어나나?
        1. 연쇄 일어나는 개수 카운트 -> 동시에 초기화
        2. 중력 끌어당기기
        3. 없을 때 까지 반복
        */
        new Main().solution();
	}

	public void solution() throws IOException {
        // 입력
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        // 폭발 카운트
        while(true) {
            // StringBuilder sb = new StringBuilder();
            // for (int i = 0; i < n; i++) {
            //     for (int j = 0; j < m; j++) {
            //         sb.append(board[i][j]);
            //     }
            //     sb.append("\n");
            // }
            // System.out.println(sb);
            
            boolean isExplosed  = explosion();
            if (!isExplosed) break;
            result++;
            gravity();
        }

        System.out.println(result);
	}

    // 폭발 & 폭발 여부 반환
    public boolean explosion() {
        // bfs로 탐색
        boolean isExplosed = false;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '.' || visited[i][j]) continue;
                List<Pair> visit = bfs(new Pair(i, j), board[i][j]); // 방문한 지점
                if (visit.size() >= 4) {
                    isExplosed = true;
                    for (int e = 0; e < visit.size(); e++) {
                        Pair p = visit.get(e);
                        board[p.x][p.y] = '.';
                    }
                }
            }
        }
        return isExplosed;
    }

    // c 를 가지는 점 배열
    public List<Pair> bfs(Pair start, char c) {
        Deque<Pair> q = new ArrayDeque<>();
        q.addLast(start);
        visited[start.x][start.y] = true;

        List<Pair> visit = new ArrayList<>();
        visit.add(start);

        while(!q.isEmpty()) {
            Pair cur = q.removeFirst();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visited[nx][ny] || board[nx][ny] != c) continue;
                Pair next = new Pair(nx, ny);
                q.addLast(next);
                visited[next.x][next.y] = true;
                visit.add(next);
            }
        }

        return visit;
    }

    // 중력으로 잡아 당기기
    public void gravity() {
        char[][] tmp = new char[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(tmp[i], '.');
        }
        // 각 열들에 대해서
        for (int j = 0; j < m; j++) {
            // 뒤에서 부터 하나씩
            int idx = n - 1; // 새로 추가될 row
            for (int i = n - 1; i >= 0; i--) {
                if (board[i][j] == '.') continue;
                // 뿌요뿌요 있으면 추가
                tmp[idx--][j] = board[i][j];
            }
        }

        board = tmp;
    }

    class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
