import java.io.*;
import java.util.*;

class Main {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder sb = new StringBuilder();

	int t;
	int n;
    int[] graph;
    int[] isCycle; // 0: 확인 전, 1: 사이클, -1: 사이클 없음
    boolean[] visited; // 방문 여부

	public static void main(String[] args) throws IOException {
		/*
        임의의 학생 x에서 시작해 다음 학생으로 계속 이동할 때

        1. 사이클에 포함된 학생 혹은 사이클에 포함되지 않은 학생을 재방문했을 경우 x는 사이클에 포함되지 않은 학생이다. 지금까지 방문한 학생들을 사이클에 포함되지 않은 학생으로 분류한다.
        2. x가 아닌 다른 방문한 학생 y를 재방문 했을 경우 x는 사이클에 포함되지 않고 y는 사이클에 포함되어 있다. y에서 출발해 다시 이동하며 다시 y에 도달할 때 까지 만나는 학생들을 사이클에 포함된 학생으로 만들고, x에서 출발해 이동하며 y에 도달할 때까지 만나는 학생들을 사이클에 포함되지 않는 학생으로 만든다.
        3. x를 재방문했을 경우 x는 사이클에 포함된 학생이다. x에서 출발해 이동하며 다시 x에 도달할 때 까지 만나는 학생들을 사이클에 포함된 학생으로 만든다.

        각 학생을 최대 2번 방문하므로 O(N)
		*/
		new Main().solution();
	}

	public void solution() throws IOException {
		t = Integer.parseInt(br.readLine());
		while(t-- > 0) {
			init();

        	for (int i = 1; i <= n; i++) {
                if (isCycle[i] != 0 || visited[i]) continue;
                visit(i); // i 번 노드 부터 탐색 시작
            }

            // StringBuilder sb2 = new StringBuilder();
            // for (int i = 1; i <= n; i++) {
            //     sb2.append(isCycle[i]).append(" ");
            // }
            // // sb2.append("\n");
            // System.out.println(sb2);

            sb.append(count()).append("\n"); // 사이클 없는 학생 카운트
		}
		System.out.println(sb);
	}

    public int count() {
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (isCycle[i] == -1) {
                cnt++;
            }
        }
        return cnt;
    }

    public void visit(int start) {
        // visited = new boolean[n + 1];
        visited[start] = true;
        int cur = start;
        
        while(true) {
            // 다음 방문
            int next = graph[cur];
            // 사이클이 결정된 학생인가? -> start:next -> -1
            if (isCycle[next] != 0) {
                mark(start, next, -1);
                break;
            }
            // 자기 자신으로 돌아왔는가? -> 사이클!
            if (visited[next] && next == start) {
                mark(start, start, 1);
                break;
            }
            // 자기 자신 아닌 다른 노드로 돌아왔는가? -> next:next 사이클 & start:next -1
            if (visited[next]) {
                mark(next, next, 1);
                mark(start, next, -1);
                break;
            }

            visited[next] = true;
            cur = next;
        }
    }

    // st:en 을 status로 표기
    public void mark(int st, int en, int status) {
        int cur = st;
        isCycle[cur] = status;
        
        while(true) {
            int next = graph[cur];            
            if (next == en) break;
            isCycle[next] = status;
            cur = next;
        }
    }

	public void init() throws IOException {
		n = Integer.parseInt(br.readLine());
		graph = new int[n + 1];
        isCycle = new int[n + 1];
        visited = new boolean[n + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int a = 1; a < n + 1; a++) {
			int b = Integer.parseInt(st.nextToken());
			graph[a] = b;
		}
	}
}
