import java.util.*;

/*
접두어 관계가 있는지 여부 반환

하나의 문자열 -> 접두어 검사 -> 목록에 있는지 검사
*/

class Solution {
    
    public boolean solution(String[] phoneBook) {   
        Set<String> set = new HashSet<>(Arrays.asList(phoneBook));
        
        for (String str: set) {
            for (int i = 1; i < str.length(); i++) {
                if (set.contains(str.substring(0, i))) return false;
            }
        }
        return true;
    }
}