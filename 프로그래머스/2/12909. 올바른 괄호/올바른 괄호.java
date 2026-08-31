import java.util.*;

class Solution {
    
    boolean solution(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        
        for(char c: s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(c); 
                continue;
            }
            if (stack.peek() == '(' && c == ')') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        
        return stack.isEmpty();
    }
}