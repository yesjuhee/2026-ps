import java.util.*;

class Solution {
    /*
    2개의 map 쌍 만들기
    하나씩 비교 -> 존재하는지, 숫자 같은지
    */
    
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> pm = new HashMap<>();
        Map<String, Integer> cm = new HashMap<>();
        
        for (int i = 0; i < participant.length; i++) {
            String key = participant[i];
            pm.put(key, pm.getOrDefault(key, 0) + 1);
        }
        
        for (int i = 0; i < completion.length; i++) {
            String key = completion[i];
            cm.put(key, cm.getOrDefault(key, 0) + 1);
        }
        
        for (Map.Entry<String, Integer> pe: pm.entrySet()) {
            String key = pe.getKey();
            Integer value = pe.getValue();
            
            if (!cm.containsKey(key)) {
                return key;
            } else if (cm.get(key) < value) {
                return key;
            }
        }
        
        String answer = "";
        return answer;
    }
}