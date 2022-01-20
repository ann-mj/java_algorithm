package programmers.study.q2_4.solution2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] t = {10,10,10,10,10,10,10,10,10,10};
        System.out.println(solution.solution(100, 100, t));
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Bridge bridge = new Bridge(bridge_length, weight);
        Queue<Truck> waitTrucks = new LinkedList<>();
        for (int w : truck_weights) {
            waitTrucks.offer(new Truck(bridge_length, w));
        }

        while (!waitTrucks.isEmpty() || bridge.getTrucks().size() > 0) {
            Truck truck = waitTrucks.peek();
            if (truck == null) {
                bridge.removeIfArrived();
                bridge.moveTrucks();
                answer++;
                continue;
            }
            bridge.moveTrucks();
            if (bridge.isTruckWeightAvailable(truck)) {
                waitTrucks.poll();
                bridge.enterTruck(truck);
            }
            bridge.removeIfArrived();
            answer++;
        }
        return answer;
    }

    class Bridge {
        private int length;
        private int maxWeight;
        private List<Truck> trucks = new ArrayList<>();
        private int currentWeight = 0;
        public List<Truck> getTrucks() {
            return trucks;
        }

        public boolean isTruckWeightAvailable(Truck truck) {
            return currentWeight + truck.getWeight() <= maxWeight;
        }

        public void enterTruck(Truck truck) {
            currentWeight += truck.getWeight();
            trucks.add(truck);
            truck.go();
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public Bridge(int length, int maxWeight) {
            this.length = length;
            this.maxWeight = maxWeight;
        }

        public void moveTrucks() {
            for (int i = 0; i < trucks.size(); i++) {
                trucks.get(i).go();
            }
        }

        /* 다리를 다 건넌 트럭을 없앤다. */
        public void removeIfArrived() {
            if (trucks.get(0).isFinished()) {
                currentWeight -= trucks.get(0).getWeight();
                trucks.remove(0);
            }
        }
    }

    class Truck {
        private int remainDistance;
        private int weight;

        public Truck(int remainDistance, int weight) {
            this.remainDistance = remainDistance;
            this.weight = weight;
        }

        public boolean isFinished() {
            return remainDistance == 0;
        }

        public void go() {
            // 1칸 씩 이동한다.
            remainDistance--;
        }

        public int getWeight() {
            return weight;
        }
    }
}
