import java.util.*;

/*
구명보트 개수의 최소값을 return 

n <= 50,000
O(nlogn)

최대 2명씩 밖에 탈 수 없다.....
엥 그럼 정렬해서 제일 큰거, 작은거 확인하는게 최적 아님??


30 40 50 50 60 70 -> 이러면 3대
50(평균)을 기준으로 2개의 stack 으로 쪼개기

50 초과인 것 -> 앞의 것과 더하기,,
아 이거 아닌데

30 30 40 50 60 70

최대 2명씩 밖에 탈 수 없는거니까, 큰 것 부터 처리해야함
작은 것 부터 처리하면, 큰게 짝이 안맞아서 혼자 남음

정렬 + Deque 이용

1. 정렬한다
2. 제일 큰 값 + 제일 작은 값을 꺼낸다
    둘이 더해서 limit을 초과하면 제일 큰 값 하나만 구명보트
    초과를 안하면 둘 다 같이 구명보트

*/

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int start = 0;
        int end = people.length - 1;
        int result = 0;
        
        while(start < end) {
            int max = people[end];
            int min = people[start];
            
            if (max + min > limit) {
                end--;
                result++;
            } else { // max + min <= limit
                end--;
                start++;
                result++;
            }
            // System.out.printf("start:%d, end:%d\n", start, end);
            
        }
        if (start == end) {
            result++;
        }
        
        return result;
    }
}