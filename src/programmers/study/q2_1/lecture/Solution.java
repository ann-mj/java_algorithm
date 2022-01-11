package programmers.study.q2_1.lecture;

public class Solution {
    /*
     boolean solution(String s) {
        Stack<String> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            String p = String.valueOf(ch); // 얘가 시간이 많이 걸리나? 후보 1
            if (p.equals("(")) { // 후보 2 equals
                stack.push(p);
            } else {
                if(stack.isEmpty()) return false; // 가장 빠르게 리턴한다!! // 후보 4
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    위와 같이 풀면 시간초과가 난다.

    사실 1번과 2번이 시간이 제일 많이 걸립니다.
    String.valueOf 하면 ch 가 2바이트 숫자인데, 2바이트에 유니코드의 객체를 스트링으로 변환해서 String 으로 만듬
    Stack 은 이미 몇개의 스택 공간이 확보되어 있음. 만들 때. 내부에서 한다고 함

    Stack 을 Character 로 바꾼다면 시간초과를 해결할까? 해결하네요

    ch 와 String 의 차이가 보이시나요
    ch == '(' 로 해서 풀면 됨

    근데 시간을 어디서 제일 많이 잡아먹을까요 ?

    2번과 4번 , stack push, pop 중에 push 가 더 오버헤드가 있음
    stack 의 capacity 가 3인데, push 하려고 하면 공간을 확보한 다음에 해야함.. pop 보다는 push 가 조금 더 시간이 오래 걸린다.
    몇 마이크로second 정도의 차이임. 사람한테 큰 의미가 없다.
     */

    // 결국 stack 에서 뭐가 빠졌는지 뭐가 들어가있는지 어차피 알 필요없으니까 stackCount 로만 풀어도 됨
    /*
        boolean solution(String s) {
        int stackCount = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'C') {
                stackCount++;
            } else {
                if( stackCount==0) return false;
                stackCount--;
            }
        }
        return stackCount == 0;
    }
     */
}
