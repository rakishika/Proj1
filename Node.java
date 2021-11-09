
package puzzle8;

import java.util.Objects;

public class Node implements Comparable<Node> {
    State state;
    Node parentNode;
    String operator;
    int depth;
    int heuristicCostcost;
    int cost;

    public Node(State state, Node parentNode, String operator, int depth, int cost) {
        this.state = state;
        this.parentNode = parentNode;
        this.operator = operator;
        this.depth = depth;
        this.cost = cost;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    @Override
    public int compareTo(Node o) {
        int tCost = this.getCost() + this.getDepth();
        int oCost = o.getCost() + o.getDepth();

        System.out.println("Child vs Item Total Cost: " + tCost + ":" + oCost);
        if(tCost < oCost){
            return -1;
        }
        if(tCost > oCost){
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(getState().stateArr, node.getState().stateArr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState());
    }
}
