package programmers.study.q1_6.lecture;

public class Solution {
    public int solution(int n) {
        int bits = bitCount(n);
        while (bitCount(++n) != bits);
        return n;
    }

    private int bitCount(int n) {
        // TODO : 2진수에서 1의 개수를 찾아 리턴한다.
        // 메인 비즈니스 로직, 내가 주로 메인 알고리즘이 수행되는 메인 코드를 Host code 라고 합니다.
        //
        return Integer.bitCount(n);
    }

    /*
    static 은 용도가 있을때에만 쓰는 거에요. static 이어야 할 이유가 없는데 static 이 되는 거는 좋지 않아요.
    모든 코드는 뭔가를 쓴다면 존재 이유가 있어야 합니다.
    main 함수
    class 는 밖으로 보내는 정보를 통제 해주어야 합니다.
    특별히 외부로 공개해주어야 할 필요가 있을 때
    캡슐화를 지키기 위해서
     */

    /*
    우리가 문제 출제자 입장에서 생각해보자.
    보고 싶은 코드는 뭘까요 ? 이사람이 비트를 다룰 수 있는가? 2진수에 대한 개념이 있는지를 알고싶기 때문에 문제를 낸거임
    bitCount 함수만 쓰는 건 좋은점수를 받지 않을 수 있다.
    Integer.bitCount(n) 이렇게 한 건 무조건 좋은 코드라는 말은 아님. 보는 관점에 따라서 다르다.
    Integer.toBinaryString 메서드는 알아. 그러면 여기서 한 글자씩 떼서 1이면 카운트를 증가시킴
    for(int i=0; i<bin.length()-1; i++) {
        String b = bin.substring(i,i+1);
        if(b.equals("1")) count++;
    }

    substring : 일부를 떼어다가 새로운 new String 을 만듬
    equals : 문자열 두개를 가지고 길이만큼 루프를 돌면서 비교함

    ch == '1' : 숫자로 비교하니까 효율성 측면에서 훨씬 좋다.

    for(char ch : bin.toCharArray()) {
        if(ch == '1') count++;
    }

    return (int) bin.chars().filter(ch -> ch =='1').count();

    컴퓨터는 숫자로 연산하는게 제일 빠름
    while(n >0) {
        if(n % 2 == 1) count ++;
        n /= 2;
    }

    자바는 버스카드의 IC 칩 안에 cpu, memory 들어있는데, 버스카드에다가 태그하는 동안 전류되는 동안 컴퓨터가 켜져서 부팅되서 통신하는 거임
    몇 kb 안되는 환경에서는 스트림을 처리하는 거는 너무 heavy. 그래서 숫자연산을 하는 바로 위 코드가 좋음.

    만약 비트연산을 사용할 수 있는지 물어본 다면
    if((n & 1) == 1) count++;
    n >> 1;

    좀 더 개선해본다면,

    while(n > 0) {
        count += (n & 1);
        n >>= 1;
    }

    한 문제를 풀어도, 여러가지 방식으로 풀어보는 것이 훨씬 도움이 된다.
    Integer.bitCount 만 하는 사람이 3진법으로 만들었을 때 2만 나오는 카운트를 세라고 하면, 3진법을 인티저가 제공해주지 않는 한 이 문제는 풀수없다.

    if(n % 3 == 2) count++;
    n /= 3;

    즉, 원리만 알고 있으면 여러가지 응용이 가능하다.
    표현 방법이 달라지면, 생각하는 방법도 달라짐. 생각하는 방법이 달라지면, 문제에 접근할 수 있는 접근하고 풀이하는 방법이 여러가지로 더 늘어난다.
     */
}
