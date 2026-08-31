import java.util.*;

/*
접두어 관계가 있는지 여부 반환

1. 맨 앞자리 숫자로 분류 -> 1개인 거 버림
2. 1에서 같은 칸에 있던 걸로 그 다음 자리수로 분류
    -> 반복하다가, 끝까지 간게 있으면 접두어로 쓰이는 번호라는 뜻임

*/

class Solution {
    
    public boolean solution(String[] phoneBook) {   
        List<String> phones = new ArrayList<>();
        for (String str: phoneBook) phones.add(str);
        
        return !hasPrefix(0, phones);
    }
    
    public boolean hasPrefix(int n, List<String> phones) {
        if (phones.size() == 1) return false; // 같이 있는 번호가 없음 
        // 종료 조건인지 확인 
        for (String phone: phones) {
            if (phone.length() == n) return true; // 접두어 발견
        }
        
        // 그 다음 트리 생성
        Map<Character, List<String>> map = new HashMap<>();
        for (String phone: phones) {
            map.computeIfAbsent(phone.charAt(n), k -> new ArrayList<>())
                .add(phone);
        }
        
        // 각 노드에 대해 검사하고 결과 판단 
        for (Character key: map.keySet()) {
            boolean result = hasPrefix(n + 1, map.get(key));
            if (result) return true;
        }
        return false;
    }
}