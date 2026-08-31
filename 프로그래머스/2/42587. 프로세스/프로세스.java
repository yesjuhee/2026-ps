import java.util.*;

/*
location 프로세스가 몇 번째로 실행되는가?

n = 100 이니까 실제로 시뮬레이션 돌려보면 될듯?

1. 제일 앞에 걸 poll 한다 (cur)
2. 나머지 q 를 순회하며 최대값을 기록한다 (localMax)
    if localMax <= cur -> cur 실행
    else -> cur 다시 offer 하고 localMax가 나올 때까지 진행 -> localMax 실행
3. 순번 체크하기

*/

class Solution {
    public int solution(int[] priorities, int location) {
        // 초기화
        Deque<Node> q = new ArrayDeque<>();
        for (int i = 0; i < priorities.length; i++) {
            Node node = new Node(priorities[i], i);
            q.offer(node);
        }
        
        int runCount = 0;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            int localMax = 0;
            for (Node node: q) {
                localMax = Math.max(localMax, node.priority);
            }
            if (localMax <= cur.priority) {
                // cur 실행 
                runCount++;
                if (cur.location == location) {
                    return runCount;
                }
            } else {
                q.offer(cur);
            }
        }
        
        int answer = 0;
        return answer;
    }
}

class Node {
    int priority;
    int location;
    
    Node(int p, int l) {
        this.priority = p;
        this.location = l;
    }
}