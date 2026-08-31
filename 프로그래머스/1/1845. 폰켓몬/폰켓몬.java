import java.util.*;

class Solution {
    
    /*
    n/2 선택하는 방법 중, 최대 몇 종류까지 선택 가능?
    
    1. set에 넣기 -> set의 크기가 종류의 수 
    2. set이 n/2 보다 작으면 set, 크면 n/2
    */
    
    public int solution(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        
        for (int num: nums) {
            set.add(num);
        }
        
        if (set.size() < n/2) return set.size();
        return n/2;
    }
}