package example2;

public class UndirectedGraph {

    // 그래프를 만드는 방법
    private int count; // 노드가 몇개냐

    private int[][] vertexMatrix; // 2차원 배열

    public UndirectedGraph(int count) {
        this.count = count;
        vertexMatrix = new int[count][count];
    }

    // 간선의 유무 , 정보를 정의
    // undirected graph 니까!
    public void addEdges(int from, int to, int weight) {
        vertexMatrix[from][to] = weight;
        vertexMatrix[to][from] = weight;
    }

    public int[][] getMatrix() {
        return vertexMatrix;
    }
}
