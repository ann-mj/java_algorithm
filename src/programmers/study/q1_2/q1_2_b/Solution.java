package programmers.study.q1_2.q1_2_b;

import java.util.*;

public class Solution {
    class Student {
        int[] arr; // 찍기 배열
        int answerCount; // 정답의 개수
        int studentNumber; // 학생의 번호

        public Student(int[] arr, int answerCount, int studentNumber) {
            this.arr = arr;
            this.answerCount = answerCount;
            this.studentNumber = studentNumber;
        }
    }

    public int[] solution(int[] answers) {
        // 찍기 배열
        ArrayList<Student> persons = new ArrayList<>();
        Student student1 = new Student(new int[]{1, 2, 3, 4, 5}, 0, 1);
        Student student2 = new Student(new int[]{2, 1, 2, 3, 2, 4, 2, 5}, 0, 2);
        Student student3 = new Student(new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5}, 0, 3);
        persons.add(student1);
        persons.add(student2);
        persons.add(student3);

        for (Student student : persons) {
            for (int i = 0; i < answers.length; i++) {
                if (student.arr[i % student.arr.length] == answers[i]) {
                    student.answerCount++;
                }
            }
        }

        persons.sort((o1, o2) -> o2.answerCount - o1.answerCount);
        int max = persons.get(0).answerCount;

        ArrayList<Integer> A = new ArrayList<>();
        for (int i = 0; i < persons.size(); i++) {
            if (max == persons.get(i).answerCount) {
                A.add(persons.get(i).studentNumber);
            }
        }
        // 학생은 1번부터 시작
        return A.stream().mapToInt(Integer::intValue).toArray();
    }
}