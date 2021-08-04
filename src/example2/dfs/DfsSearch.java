package example2.dfs;

import example2.UndirectedGraph;

import java.util.Stack;

public class DfsSearch {

    int count;
    // 방문 했냐 안했냐 하는 정보를 가지는 배열
    boolean[] visited;
    Stack<Integer> stack;
    int[][] matrix;

    public DfsSearch(int count) {
        // 매개변수 count 는 undirectedgraph 의 count 와 같다.
        this.count = count;
        visited = new boolean[count];
        stack = new Stack<Integer>();
    }
    
    // 어느 노드부터 돌 것인가.
    public void dfsTraverse() {

        // 우리는 0 부터 돌 것이다.
        stack.push(0);
        visited[0] = true;

        while(stack.isEmpty() != true) {
            int node = stack.pop(); // node 를 하나 꺼낸다.
            // node 를 꺼냈으니까 방문한거죠?
            System.out.print(node + " ");

            for (int j=0; j<count; j++) {

                if(matrix[node][j] != 0 && visited[j] == false) {
                    // stack 에 들어가지 않았다면
                    stack.push(j);
                    visited[j] = true;
                }
            }

        }
        
    }
    
    
    public static void main(String[] args) {
        // 그래프 그리기
        int count = 8; // vertex 8 개
        UndirectedGraph graph = new UndirectedGraph(count);
        graph.addEdges(0,1, 1); // 원래는 1,0, x 인 경우도 넣어줘야 하는데, 방향성이 없으므로 패스 ㄱㄱ
        graph.addEdges(0,2,1);
        graph.addEdges(1,3,1);
        graph.addEdges(1,4,1);
        graph.addEdges(2,5,1);
        graph.addEdges(2,6,1);
        graph.addEdges(4,5,1);
        graph.addEdges(3,7,1);

        DfsSearch dfs = new DfsSearch(count);
        dfs.matrix = graph.getMatrix();

        dfs.dfsTraverse();
    }
}
