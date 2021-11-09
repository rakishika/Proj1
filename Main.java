package puzzle8;

public class Main {


    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        long startTime, endTime, uDuration, mDuration, tDuration = 0L;

        //DEPTH 2
        //int[] initArr = new int[]{1, 2, 3, 4, 5, 6, 0, 7, 8};

        //DEPTH 4
        //int[] initArr = new int[]{1, 2, 3, 5, 0, 6, 4, 7, 8};

        //DEPTH 13
        //int[] initArr = new int[]{1, 2, 3, 4, 5, 0, 6, 7, 8};

        //DEPTH 24
        int[] initArr = new int[]{0, 7, 2, 4, 6, 1, 3, 5, 8};

        //GOAL
        int[] goalArr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};

        State initial = new State(initArr);
        State goal = new State(goalArr);

        //initial.display();
        //goal.display();

        //A* Missing Tile
        startTime = System.nanoTime();
        Utils.search(initial, goal, "U");
        endTime = System.nanoTime();
        uDuration = (endTime - startTime) / 1000000; //milliesecond
        System.out.println("Initial State....\n");
        initial.display();
        System.out.println("Goal State....");
        goal.display();
        System.out.println("Uniform Cost Search Duration (ms): " + uDuration);

//        //A* Misplaced Tile Heuristic
//        startTime = System.nanoTime();
//        Utils.search(initial, goal, "T");
//        endTime = System.nanoTime();
//        tDuration = (endTime - startTime) / 1000000; //milliesecond
//        System.out.println("Initial State....\n");
//        initial.display();
//        System.out.println("Goal State....");
//        goal.display();
//        System.out.println("Misplaced Tile Heuristich Duration (ms): " + tDuration);

//        //A* Manhattan Distance Heuristic
//        startTime = System.nanoTime();
//        Utils.search(initial, goal, "M");
//        endTime = System.nanoTime();
//        mDuration = (endTime - startTime) / 1000000; //milliesecond
//        System.out.println("Initial State....\n");
//        initial.display();
//        System.out.println("Goal State....");
//        goal.display();
//        System.out.println("Manhattan Distance Heuristic Duration (ms): " + mDuration);


        //System.out.println("Depth: " + 4 + " *** Expanded State: " + 25);
        //goal.display();






        //State current = Utils.moveRight(initial);
        //if (current != null)
        //    current.display();
        //else
        //    System.out.println("Not possible");
    }

}
