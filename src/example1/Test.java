package example1;



/**
 * 02. 정렬된 수에서 하나의 수의 위치 찾기
 *
 * 문제 정의
 * 여러 개의 수가 정렬된 순서로 있을 때 특정한 수를 찾는 방법
 * 단순 반복문을 이용하면 수의 개수에 따라 비교 횟수가 증가하는 O(n)의 수행이 이루어짐
 * 수가 정렬된 상태에서는 이진 탐색(binary search)을 활용하면 매번 비교되는 요소의 수가 절반으로 감소될 수 있으므로 O(logN)의 수행으로 원하는 수를 찾을 수 있음
 * 수의 예 : [12, 25, 31, 48, 54, 66, 70, 83, 95, 108]
 *
 * 83의 위치를 찾아보세요
 * 88의 위치를 찾아보세요
 *
 * 해결 방법
 * 수가 정렬된 상태이므로 중간의 값을 하나 선택한다. 찾으려는 값이 그보다 크면 범위를 오른쪽으로 그보다 작으면 범위를 왼쪽으로 좁힐수 있다.
 *
 * 한번 비교 할때 마다 1/2씩 범위가 좁혀진다.
 * */
public class Test {

    public static void main(String[] args) {
        int[] arr = {12, 25, 31, 48, 54, 66, 70, 83, 95};

        // index
        int indexLeft = 0;
        int indexRight = 0;
        int indexMid = 0;

        // value
        int valueTemp = 0;
        int valueTarget = 94; // 얘를 조정하면서 테스트

        indexLeft = 0;
        indexRight = arr.length - 1;
        indexMid = (indexLeft + indexRight) / 2; // mid index 초기화

        valueTemp = arr[indexMid];
        // left , right 같을 때까지
        while(indexLeft <= indexRight) {

            // 찾은 경우 탈출
            if(valueTemp == valueTarget) {
                System.out.println("찾음 , index : " + (indexMid + 1) + " 번째");
                return;
            }

            if(valueTarget > valueTemp) {
                indexLeft = indexMid + 1;
            } else {
                indexRight = indexMid - 1;
            }

            indexMid = (indexLeft + indexRight) / 2;
            valueTemp = arr[indexMid];
        }

        System.out.println("못찾음");
    }
}
