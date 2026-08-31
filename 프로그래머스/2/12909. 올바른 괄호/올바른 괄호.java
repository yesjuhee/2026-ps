import java.util.*;

class Solution {
    
    boolean solution(String s) {
        Deque<Character> stack1 = new ArrayDeque<>();
        Deque<Character> stack2 = new ArrayDeque<>();
        
        for(char c: s.toCharArray()) {
            stack1.push(c);
        }
        
        while(!stack1.isEmpty()) {
            stack2.push(stack1.pop());
            if (stack1.isEmpty()) return false;
            
            while (
                !stack1.isEmpty() && !stack2.isEmpty() &&
                stack1.peek() == '(' && stack2.peek() == ')') {
                // 짝이 맞음 
                stack1.pop();
                stack2.pop();
            }
        }

        return stack2.isEmpty();
    }
}