import java.util.*;

/*
구) 모든 사람이 심사를 받는데 걸리는 시간의 최소값
f(x): x분에 모든 사람이 심사를 받았는가?
최적화 문제: f(x) = true 가 되는 x의 최소값은?
결정 문제: 어떤 x에서 f(x) = true가 되는가? (lower bound)

x
f(x) f f f t t t  -> lower bound 구하기

f(x) 어떻게 구함??

28분에 모든 사람이 심사를 받았는가?
7분 * 4명
10분 * 2명
계산가능
*/

class Solution {
    public long solution(int n, int[] times) {
        long st = 1;
        // long en = Long.MAX_VALUE;
        long en = 1_000_000_000L * 1_000_000_000L;
        // long en = 1_000_000_000L; // 통과
        // long en = 1_000_000_0000L; // 통과
        // long en = 1_000_000_00000L; // 실패
        long answer = 0;
        
        while(st <= en) {
            long mid = st + (en - st) / 2;
            
            if (isFinished(mid, times, n)) {
                // 모두 심사를 받음 -> 왼쪽 탐색
                answer = mid;
                en = mid - 1;
            } else {
                // 모두 심사를 못받음 -> 오른쪽 탐색
                st = mid + 1;
            }
        }
        
        return answer;
    }
    
    public boolean isFinished(long x, int[] times, int n) {
        long sum = 0;
        for (int i = 0; i < times.length; i++) {
            int time = times[i];
            sum += x / time;
        }
        return sum >= n;
    }
}