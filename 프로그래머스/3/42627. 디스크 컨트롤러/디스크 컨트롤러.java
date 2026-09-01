import java.util.*;

/*
반환 시간의 평균의 정수 부분

우선순위
1. 소요시간 짧은 것 (l)
2. 요청 시각이 빠른 것 (s)
3. 작업의 번호가 작은 것 (id)

반환시간(turnaround) -> 종료 - 요청

아 요청 시간 순이 아닐 수 있음

1. pq 초기화, prev 초기화 (제일 작은 시작 시간으로)
2. while: !jobNodes.isEmpty() 
    prev 실행 -> 현재 시간 조정 / 끝나는 시각 기록
        도착한 작업 있으면 대기 큐로 모두 이동
    if (대기큐가 비어있으면) 
        그 다음 작업 꺼내서 prev
    else 대기큐가 있으면
        하나 꺼내서 prev
3. 전체에서 평균값 계산해서 구하기    
    
*/

class Solution {
    
    Job prev; // 직전에 실행한 작업
    int now; // 현재 시각
    int turnSum;
    Job cur;
    
    PriorityQueue<Job> jobNodes = new PriorityQueue<>(
        (j1, j2) -> {
            if (j1.s != j2.s) return j1.s - j2.s;
            if (j1.l != j2.l) return j1.l - j2.l;
            return j1.id - j2.id;
        }
    ); // 도착 시간 순
    PriorityQueue<Job> queue = new PriorityQueue<>( // 대기큐
        (j1, j2) -> {
            if (j1.l != j2.l) return j1.l - j2.l;
            else if (j1.s != j2.s) return j1.s - j2.s;
            return j1.id - j2.id;
        }
    );
    
    public int solution(int[][] jobs) {
        // 1. 초기화, now 도 prev에 맞게 초기화
        init(jobs);
        
        // 전체 진행
        while(!jobNodes.isEmpty() || !queue.isEmpty()) {
            // 현재 시각에 맞게 대기 큐 채우기
            while(!jobNodes.isEmpty() && jobNodes.peek().s <= now) {
                queue.add(jobNodes.poll());
            }
            // Cur 찾기
            if (queue.isEmpty()) {
                // 대기 큐 비어있음
                cur = jobNodes.poll();
                now = cur.s;
            } else {
                // 대기 큐 차있음
                cur = queue.poll();
            }
            
            // cur 실행
            run(cur);
            
            // System.out.printf("now: %d\n", now);
            // System.out.printf("cur: %s\n", cur);
            // System.out.printf("jobNodes: %s\n", jobNodes);
            // System.out.printf("queue: %s\n", queue);
            
            
            
            // prev 실행
//             run(prev);
            
//             // 현재 시각에 맞게 대기 큐 채우기 (prev 작업 끝난 시점)
//             while(!jobNodes.isEmpty() && jobNodes.peek().s <= now) {
//                 queue.add(jobNodes.poll());
//             }
//             // System.out.printf("queue: %s\n", queue);
            
//             // 그 다음 prev 정하기
//             if (queue.isEmpty()) {
//                 // 대기 큐 비어있음
//                 prev = jobNodes.poll();
//                 now = prev.s;
//             } else {
//                 // 대기 큐 차있음
//                 prev = queue.poll();
//             }
        }
        
        // run(prev);
        
        return turnSum / jobs.length;
    }
    
    void run(Job job) {
        now += job.l;
        turnSum += (now - job.s);
        // System.out.printf("run / now: %d\n", now);
    }
    
    void init(int[][] jobs) {
        for (int i = 0; i < jobs.length; i++) {
            Job job = new Job(i, jobs[i][0], jobs[i][1]);
            jobNodes.add(job);
        }
    }
}

class Job {
    
    int id;
    int s;
    int l;
    
    public Job(int id, int s, int l) {
        this.id = id;
        this.s = s;
        this.l = l;
    }
    
    public String toString() {
        return String.format("(id:%d, s:%d, l:%d)", id, s, l);
    }
}