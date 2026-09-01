import java.util.*;

/*
모든 음식 k 이상으로 만들기 위해 섞어야 하는 최소 횟수

1. 스코빌을 pq 에 삽입
2. 반복
    while: min 값이 k보다 작은 동안
    min1, min2 꺼내기
    공식대로 계산해서 추가하기
    반복회수 ++
*/

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        
        for (int s: scoville) {
            pq.add((long) s);
        }
        
        while(pq.peek() < K && pq.size() >= 2) {
            long min1 = pq.poll();
            long min2 = pq.poll();
            pq.add(min1 + min2 * 2);
            answer++;
        }
        
        // 조건 검사 
        if (pq.size() == 1 && pq.peek() < K) {
            return -1;
        }
        
        return answer;
    }
}