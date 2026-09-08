/*
체육 수업을 들을 수 있는 학생의 최대값?

n <= 30

n = 5
1 2 3 4 5
11 x 33 x 55
그냥 나눠주면 끝

n = 5
1 2 3 4 5
1 x 33 x 5
2 or 4 빌려줘야

n = 3
1 2 3
11 2 x 

앞에서 부터 lost 확인
1. 내가 있는가? -> 해결
2. 앞에 학생이 있는가? -> 앞의 학생 것 가져오기
3. 뒤의 학생이 있는가? -> 뒤의 학생 것 가져오기
*/
import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        boolean[] hasReserve = new boolean[n + 1]; // n번 학생의 여벌
        for (int i = 0; i < reserve.length; i++) {
            hasReserve[reserve[i]] = true;
        }
        
        int result = n - lost.length;
        Set<Integer> skip = new HashSet<>();
        for (int i = 0; i < lost.length; i++) {
            if (hasReserve[lost[i]]) {
                hasReserve[lost[i]] = false;
                skip.add(lost[i]);
            }
        }
        result += skip.size();
        
        for (int i = 0; i < lost.length; i++) {
            int student = lost[i]; // 체육복 빌릴 수 있는지 확인 
            if (skip.contains(lost[i])) continue;
            if (student != 1 && hasReserve[student - 1]) {
                result++;
                hasReserve[student - 1] = false;
            } else if (student != n && hasReserve[student + 1]) {
                result++;
                hasReserve[student + 1] = false;
            }
        }
        
        return result;
    }
}