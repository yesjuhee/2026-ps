import java.util.*;

/*
서로 다른 옷의 조합의 수 return

개수만 상관이 있음

int[] counts; // 종류별로 옷 수

종류 수 만큼 선택해서 곱하기
선택하는 경우의 수 -> 백트래킹
다 계산해서 더하기
*/

class Solution {
    
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        for (String[] pair: clothes) {
            String value = pair[0];
            String key = pair[1];
            map.put(key, map.getOrDefault(key, 0) + 1);
        } 
        
        int result = 1;
        for (int count: map.values()) {
            result *= (count + 1);
        }
      
        return result - 1;
    }
}