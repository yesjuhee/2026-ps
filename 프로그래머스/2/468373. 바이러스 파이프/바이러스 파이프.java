import java.util.*;

/*
k번 파이프를 열었다 닫은 후, 감염된 배양체 개수의 최대값 

n <= 100
k <= 10 

완전탐색?
그래프
감염 -> bfs

파이프를 여는 모든 경우의 수 -> 3^k, 3^10

1. 모든 경우의 수 만들기 (백트래킹, 3개를 선택하는 경우, 순열)
2. 해당 경우로 bfs 돌리기 

그래프 표현?
List<Integer>[] graph // 각 노드의 연결된 번호
List<Integer>[][] graph // 각 노드 + abc 

모든 경우의 수 관리 -> int[] route

*/

class Solution {
    List<Integer>[][] graph;
    int result = -1;
    int infection;
    int n;
    
    public int solution(int n, int infection, int[][] edges, int k) {
        // 그래프 초기화
        this.infection = infection;
        this.n = n;
        graph = new List[n+1][4]; // 1~n개 배양채 / 1~3번 파이프
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 3; j++) {
                graph[i][j] = new ArrayList<>();
            }
        }
        
        for (int i = 0; i < n-1; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            int type = edges[i][2];
            graph[x][type].add(y);
            graph[y][type].add(x);
        }
        
        int[] actions = new int[k]; // k 번의 행동 경로
        func(0, actions);
        
        return result;
    }
    
    void func(int idx, int[] actions) {
        if (idx == actions.length) {
            int count = bfs(actions); // actions로 bfs 돌렸을 때 감염된 수
            result = Math.max(count, result);
            return;
        }
        for (int action = 1; action <= 3; action++) {
            actions[idx] = action;
            func(idx+1, actions);
        }
    }
    
    int bfs(int[] actions) {
        List<Integer> infected = new ArrayList<>();
        infected.add(infection);
        
        for (int action: actions) {
            boolean[] visited = new boolean[n + 1];
            Queue<Integer> q = new ArrayDeque<>(); 
            for (int start: infected) {
                q.offer(start);
                visited[start] = true;
            }
            while(!q.isEmpty()) {
                int cur = q.poll();
                for (int next: graph[cur][action]) {
                    if (visited[next]) continue;
                    q.offer(next);
                    visited[next] = true;
                    infected.add(next);
                }
            }
        }
        
        return infected.size();
    }
}