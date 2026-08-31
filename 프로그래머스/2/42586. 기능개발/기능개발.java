import java.util.*;
/*
각 배포마다 몇 개의 기능이 배포되는지를 return

2개의 Queue 사용

1. 전체 작업날짜 queue / 보관용 queue
2. 날짜 하나 옮기기
    q1 vs q2 비교
    q1.peek() > q2.peek() -> q2 가 하나의 묶음으로 작용
    q1.peek() <= q2.peek() -> 이어서 옮기기
*/

class Solution {
    
    int n;
    Deque<Integer> q1 = new ArrayDeque<>();
    Deque<Integer> q2 = new ArrayDeque<>();
    
    public int[] solution(int[] progresses, int[] speeds) {
        n = progresses.length;
        for (int i = 0; i < n; i++) {
            int diff = 100 - progresses[i];
            int day = diff % speeds[i] == 0 ? diff / speeds[i] : diff / speeds[i] + 1;
            q1.offer(day);
        }
        
        Deque<Integer> resultQ = new ArrayDeque<>();
        while(!q1.isEmpty()) {
            q2.offer(q1.poll());
            if (q1.isEmpty() || q1.peek() > q2.peek()) {
                resultQ.offer(q2.size());
                q2.clear();
            }
        }
        
        int[] result = new int[resultQ.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultQ.poll();
        }
        
        return result;
    }
}