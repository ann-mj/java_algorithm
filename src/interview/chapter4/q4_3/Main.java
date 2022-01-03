package interview.chapter4.q4_3;

/**
 * 비트 뒤집기
 * 많은 역순 연산을 필요로 하는 경우에 가장 효율적인 방법은 숫자에 대한 룩업테이블을 미리 만들어 놓는다.
 * A[y]는 y를 역순으로 배열한 값이 저장되어 있다.
 * 이제 x의 역순은 자연스럽게 y0 의 역순, y1 의 역순, y2의 역순, y3의 역순이 순서대로 등장하면 된다.
 * 입력이 10010011 이고, 룩업테이블이 <00,10,01,11>이 있다고 하면
 * 이 단어의 역순은
 * rev(11), rev(00), rev(01), rev(10) 을 순서대로 등장시키면 된다.
 */
public class Main {
    /*
    public static long reverseBits(long x) {
        final int WORD_SIZE = 16;
        final int BIT_MASK = 0xFFFF;
        return rev[(int)(x&BIT_MASK)] << (3 * WORD_SIZE)
            | rev[(int)((x >>> WORD_SIZE ) & BIT_MASK)] << (2 * WORD_SIZE)
            | rev[(int)((x >>> 2 * WORD_SIZE) & BIT_MASK)] << (WORD_SIZE)
            | rev[(int)((x >>> 3 * WORD_SIZE )) & BIT_MASK)]
    }
     */
}
