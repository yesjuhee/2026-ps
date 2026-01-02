import java.io.*;
import java.util.*;

public class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n, m;
	int fire[][]; // 불 이동거리 표시, -1:벽, 1 이상 -> 방문함
	int move[][]; // 지훈 이동거리 표시, -1:벽
	Pair jihun;
	Deque<Pair> q = new ArrayDeque<>();
	int[] dx = new int[]{-1, 1, 0, 0};
	int[] dy = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }

    public void solution() throws IOException {
    	// IMPOSSIBLE vs 탈출 시간
    	// 1. 불에 대해서 BFS -> 퍼저 나가는 시간
    	// 2. 지훈 BFS -> 불보다 먼저 이동해서 나갈 수 있는지 확인

    	// 입력
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	fire = new int[n][m];
    	move = new int[n][m];
    	for (int i = 0; i < n; i++) {
    		String row = br.readLine();
    		for (int j = 0; j < m; j++) {
    			char cur = row.charAt(j);
    			// 벽
    			if (cur == '#') {
    				fire[i][j] = -1;
    				move[i][j] = -1;
    				continue;
    			}
    			// J
    			if (cur == 'J') {
    				jihun = new Pair(i, j);
    				continue;
    			}
    			// F
    			if (cur == 'F') {
    				Pair f = new Pair(i, j);
    				q.addLast(f);
    				fire[i][j] = 1;
    				continue;
    			}
    		}
    	}

    	// 불 붙은 곳 BFS
    	while(!q.isEmpty()) {
    		Pair cur = q.removeFirst();
    		for (int i = 0; i < 4; i++) {
    			int nx = cur.x + dx[i];
    			int ny = cur.y + dy[i];
    			if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
    			if (fire[nx][ny] != 0) continue;    			
    			q.addLast(new Pair(nx, ny));
    			fire[nx][ny] = fire[cur.x][cur.y] + 1;
    		}
    	}

    	// 지훈 BFS
    	q.addLast(jihun);
    	move[jihun.x][jihun.y] = 1;
    	while(!q.isEmpty()) {
    		Pair cur = q.removeFirst();
    		for (int i = 0; i < 4; i++) {
    			int nx = cur.x + dx[i];
    			int ny = cur.y + dy[i];
    			if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
    				// 탈출
    				if (move[cur.x][cur.y] < fire[cur.x][cur.y] || fire[cur.x][cur.y] == 0) {
                        System.out.println(move[cur.x][cur.y]);
    					return;
    				} else {
    					continue;
    				}
    			}
    			if (move[nx][ny] != 0) continue;
    			q.addLast(new Pair(nx, ny));
    			move[nx][ny] = move[cur.x][cur.y] + 1;
    		}
    	}

		System.out.println("IMPOSSIBLE");
    }

    class Pair {

    	int x, y;

    	public Pair(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
}