package programmers.study.q1_9.solution1;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for (int i = 0; i < skill_trees.length; i++) {
            if(checkValidSkillTree(skill, skill_trees[i])) {
                answer++;
            }
        }
        return answer;
    }

    private boolean checkValidSkillTree(String skill, String skillTree) {
        boolean[] visit = new boolean[skill.length()];
        for (int i = 0; i < skillTree.length(); i++) {
            char c = skillTree.charAt(i);
            int findIndex = skill.lastIndexOf(c);  /* 스킬트리 내에 있는 스킬이 주어진 스킬 안에 있는지*/
            if (findIndex == -1) continue;         /* 스킬에 없다면 신경쓰지 않는다. */

            /* 배우지 않은 선행 스킬이 있는데, 해당 스킬을 먼저 배운게 있는지 체크한다. */
            for (int j = 0; j < findIndex; j++) {
                if(!visit[j]) return false;
            }

            visit[findIndex] = true;
        }
        return true;
    }
}