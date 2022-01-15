package programmers.study.q1_2.solution4;

import java.util.*;

public class Solution {
    class Student implements Comparable<Student> {
        private final int[] arr; // 찍기 배열
        private int answerCount; // 정답의 개수
        private final int studentNumber; // 학생의 번호

        public int[] getArr() {
            return arr;
        }

        public int getAnswerCount() {
            return answerCount;
        }

        public int getStudentNumber() {
            return studentNumber;
        }

        public void addAnswerCount() {
            this.answerCount += 1;
        }

        public Student(int[] arr, int studentNumber) {
            this.arr = arr;
            this.studentNumber = studentNumber;
        }

        @Override
        public int compareTo(Student o) {
            return this.answerCount - o.answerCount;
        }
    }

    public int[] solution(int[] answers) {

        /* 찍기 배열 초기화 부분 */
        ArrayList<Student> persons = new ArrayList<>();
        Student student1 = new Student(new int[]{1, 2, 3, 4, 5}, 1);
        Student student2 = new Student(new int[]{2, 1, 2, 3, 2, 4, 2, 5}, 2);
        Student student3 = new Student(new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5}, 3);
        persons.add(student1);
        persons.add(student2);
        persons.add(student3);

        for (Student student : persons) {
            for (int i = 0; i < answers.length; i++) {
                if (student.getArr()[i % student.getArr().length] == answers[i]) {
                    student.addAnswerCount(); /* 정답이 맞다면, student 내의 메서드를 통해 카운트 증가시킴 */
                }
            }
        }

        int max = persons.stream().max(Student::compareTo).get().answerCount; /* 최댓값 찾기 */

        return persons.stream().filter(a -> (a.getAnswerCount() == max)).mapToInt(Student::getStudentNumber).toArray();
    }
}

