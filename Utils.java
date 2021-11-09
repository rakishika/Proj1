package puzzle8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class Utils {


    public static ArrayList<Node> expandNode(Node node, State goal, String type) {
        ArrayList<Node> expanded_nodes = new ArrayList<Node>();
        State currentState = node.getState();
        int cost = 0;

        State upChildState = currentState.moveUp();
        //currentState.display();
        if (upChildState != null) {
            //TODO: calculate cost
            System.out.println("SHIFTING UP.....");
            upChildState.display();
            cost = getCost(upChildState, goal, type);
            expanded_nodes.add(new Node(upChildState, node, "u",
                    node.getDepth() + 1, cost));
        }

        State downChildState = currentState.moveDown();
        //currentState.display();
        if (downChildState != null) {
            //TODO: calculate cost
            System.out.println("SHIFTING DOWN.....");
            downChildState.display();
            cost = getCost(downChildState, goal, type);
            expanded_nodes.add(new Node(downChildState, node, "d",
                    node.getDepth() + 1, cost));
        }

        //currentState.display();
        State leftChildState = currentState.moveLeft();
        if (leftChildState != null) {
            //TODO: calculate cost
            System.out.println("SHIFTING LEFT.....");
            leftChildState.display();
            cost = getCost(leftChildState, goal, type);
            expanded_nodes.add(new Node(leftChildState, node, "l",
                    node.getDepth() + 1, cost));
        }

        //currentState.display();
        State rightChildState = currentState.moveRight();
        if (rightChildState != null) {
            //TODO: calculate cost
            System.out.println("SHIFTING RIGHT.....");
            rightChildState.display();
            cost = getCost(rightChildState, goal, type);
            expanded_nodes.add(new Node(rightChildState, node, "r",
                    node.getDepth() + 1, cost));
        }

        System.out.println("expanded child nodes: " + expanded_nodes.size());
        return expanded_nodes;
    }

    public static void search(State initial, State goal, String type) {
        int initialCost = getCost(initial, goal, type);
        Node initialNode = new Node(initial, null, null, 0, initialCost);
        Node parentNode = initialNode;
        PriorityQueue<Node> toBeProcessedPQ = new PriorityQueue<Node>();
        LinkedList<Node> processedLL = new LinkedList<Node>();
        int expandedState = 0;
        boolean solutionFound = false;

        System.out.println("INITIAL STATE.....");
        initial.display();

        System.out.println("GOAL STATE.....");
        goal.display();;

        //queue.add(initialNode);
        //currentNode = queue.pop

        //parentNode.setCost(0);
        //parentNode.setDepth(0);

        toBeProcessedPQ.add(initialNode);

        while (!toBeProcessedPQ.isEmpty()) {
            Node current = toBeProcessedPQ.peek();
            System.out.println("*******************************************");
            System.out.println("Depth: " + current.getDepth() + " *** Expanded State: " + expandedState);
            solutionFound = true;
            current.getState().display();
            if (current.getState().equals(goal)) {
                System.out.println("Success!!! Depth: " + current.getDepth() + " *** Expanded State: " + expandedState);
                solutionFound = true;
                break;
            }

            expandedState++;

            toBeProcessedPQ.remove(current);
            processedLL.add(current);

            ArrayList<Node> childrenAL = expandNode(current, goal, type);
            System.out.println("Total Children: " + childrenAL.size());
            for (int i=0;i<childrenAL.size(); i++) {
                Node childNode = childrenAL.get(i);
                childNode.getState().display();

                //Check if we have not visited this child node before. It shouldn't be in toBeProcessedPQ & processedLL
                Node itemNodeInPQ = findInPQ(toBeProcessedPQ, childNode);
                Node itemNodeInLL = findInLL(processedLL, childNode);
                //if (!toBeProcessedPQ.contains(childNode) && !processedLL.contains(childNode)) {
                if (itemNodeInPQ == null && itemNodeInLL == null) {
                    toBeProcessedPQ.add(childNode);
                    System.out.println("Fresh...adding to queue...");
                    //System.out.println("Entering 100");
                } else {
                    //if found in toBeProcessedPQ, check the cost. If cost is less, replace it
                    //System.out.println("Entering 200");
                    //if (toBeProcessedPQ.contains(childNode)) {
                    //Node itemNode = findInPQ(toBeProcessedPQ, childNode);
                    if (itemNodeInPQ != null) {
                        System.out.println("Found a copy in PQ.....");
                        //for (Node itemNode : toBeProcessedPQ) {
                            System.out.println("Cost of this child: " + childNode.getCost()
                                    + " and prev: " + itemNodeInPQ.getCost());
                            //if (childNode.getCost() < itemNodeInPQ.getCost()) {
                            if (childNode.compareTo(itemNodeInPQ) < 0) {
                                System.out.println("Replacing...");
                                toBeProcessedPQ.remove(itemNodeInPQ);
                                toBeProcessedPQ.add(childNode);
                            }
                        //}
                    }

                    //if found in processedLL, check the cost. If cost is less, replace it
                    if (itemNodeInLL != null) {
                        //System.out.println("Entering 400");
                        System.out.println("Found a copy in LL.....");
                        //for (Node itemNode1 : processedLL) {
                            System.out.println("Cost of this child: " + childNode.getCost()
                                    + " and prev: " + itemNodeInLL.getCost());
                            //if (childNode.getCost() < itemNodeInLL.getCost()) {
                            if (childNode.compareTo(itemNodeInLL) < 0) {
                                System.out.println("Replacing...");
                                processedLL.remove(itemNodeInLL);
                                processedLL.add(childNode);
                            }
                        //}
                    }

                }

            }
            System.out.println("Queue: " + toBeProcessedPQ.size() + " **** isEmpty(): " + toBeProcessedPQ.isEmpty());
            //break;
            //if (expandedState == 5) break;
            //if (current.getDepth() == 25) break;
        }

        if (!solutionFound)
            System.out.println("No Solution Found");

    }

    public static Node findInPQ(PriorityQueue<Node> pq, Node childNode) {
        String childStr = Arrays.toString(childNode.getState().getStateArr());
        String itemStr = "";
        for (Node item : pq) {
            itemStr = Arrays.toString(item.getState().getStateArr());
            //if (childStr.equals(itemStr)) {
            //if (childNode.getState().getStateArr().equals(item.getState().getStateArr()))  {
            if (ArrayCompare(childNode.getState().getStateArr(), item.getState().getStateArr())) {
                System.out.println("Comparing in PQ..." + childStr + ": " +  childNode.getCost()
                        + " ****** " + itemStr + ": " + item.getCost());
                System.out.println("Matched!!!!!!!!");
                return item;
            }
        }
        return null;
    }

    public static Node findInLL(LinkedList<Node> ll, Node childNode) {
        String childStr = Arrays.toString(childNode.getState().getStateArr());
        String itemStr = "";
        for (Node item : ll) {
            itemStr = Arrays.toString(item.getState().getStateArr());
            //if (childStr.equals(itemStr))
            //if (childNode.getState().getStateArr().equals(item.getState().getStateArr())) {
            if (ArrayCompare(childNode.getState().getStateArr(), item.getState().getStateArr())) {
                System.out.println("Comparing in LL..." + childStr + ": " +  childNode.getCost()
                        + " ****** " + itemStr + ": " + item.getCost());
                System.out.println("Matched!!!!!!!!");
                return item;
            }
        }
        return null;
    }

    public static boolean ArrayCompare(int[] a, int[] a2) {
        if (a==a2)   // checks for same array reference
            return true;
        if (a==null || a2==null)  // checks for null arrays
            return false;

        int length = a.length;
        if (a2.length != length)  // arrays should be of equal length
            return false;

        for (int i=0; i<length; i++)  // compare array values
            if (a[i] != a2[i])
                return false;

        return true;
    }

    public static int getCost(State child, State goal, String type) {
        if (type.equals("U"))
            return 0;
        else if (type.equals("M"))
            return getManhattanHeuristicCost(child, goal);
        else
            return getTileHeuristicCost(child, goal);
    }

    public static int getTileHeuristicCost(State child, State goal) {
        int cost = 0;
        for(int i = 0; i < child.getStateArr().length; i++){
            if(child.getStateArr()[i] != goal.getStateArr()[i]){
                cost++;
            }
        }
        return cost;
    }

    public static int getManhattanHeuristicCost(State child, State goal) {
        int row_col = child.getRow_col();
        int[][] itemArray = new int[row_col][row_col];
        int[][] goalArray = new int[row_col][row_col];
        int index = 0;
        int cost = 0;


        for (int i = 0; i < row_col; i++) {
            for (int j = 0; j < row_col; j++) {
                itemArray[i][j] = child.getStateArr()[index];
                goalArray[i][j] = goal.getStateArr()[index];
                index++;
            }
        }

        for (int i = 0; i < row_col; i++) {
            for (int j = 0; j < row_col; j++) {
                int currentItem = itemArray[i][j];

                for (int k = 0; k < row_col; k++) {
                    for (int n = 0; n < row_col; n++) {
                        if (goalArray[k][n] == currentItem) {
                            cost = cost + (Math.abs(i - k) + Math.abs(j - n));
                        }
                    }
                }
            }
        }
        return cost;
    }
}
