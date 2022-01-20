package programmers.study.q2_4.solution1;

import java.util.*;

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

        while (true) {
            Truck truck = waitTrucks.peek();
            if (truck == null) {

                /* 더이상 대기하는 트럭이 없고, 건너고 있는 트럭이 없다면 */
                if (bridge.getTrucks().size() == 0) {
                    break;
                }
                bridge.removeIfArrived();
                bridge.moveTrucks();
                answer++;
                continue;
            }
            bridge.moveTrucks();
            if (bridge.canIn(truck)) {
                waitTrucks.poll();
                bridge.truckIn(truck);
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

        public List<Truck> getTrucks() {
            return trucks;
        }

        public boolean canIn(Truck truck) {
            int sum = 0;
            for (Truck t : trucks) {
                sum += t.getWeight();
            }
            sum += truck.getWeight();
            return sum <= maxWeight;
        }

        public void truckIn(Truck truck) {
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
