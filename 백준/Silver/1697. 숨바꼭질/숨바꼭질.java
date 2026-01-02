import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n, k;
	Deque<Integer> q = new ArrayDeque<>();
	boolean[] visited;
	int[] dist = new int[100002];

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}

	public void solution() throws IOException {
		// n = 5, k = 17
		// 5 10 9 18 17
		// 이동할 수 있는 방법은 3가지
		// x-1, x+1, x*2
		// 비슷하게 큐를 만들어서 가장 적은 이동 시간 구하기?
		// q: 5
		// q: 4, 6, 10  : 1
		// q: 3, 5, 8, ..., 9, 11, 20 : 2
		// q: ... 8, 10, 18, ... : 3
		// q: ... 9, 11, 20, 17 -> 답
		// 가지가 3개씩 뻗어 나가는 트리의 형태! -> BFS

		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		Arrays.fill(dist, -1);

		q.push(n);	
		dist[n] = 0;

		// BFS
		while(dist[k] == -1) {
			int cur = q.removeFirst();
			for (int next: List.of(cur - 1, cur + 1, cur * 2)) {
				if (next < 0 || next > 100000 || dist[next] != -1) {
					continue;
				}
				q.addLast(next);
				dist[next] = dist[cur] + 1;
			}
		}

		System.out.println(dist[k]);
	}
}