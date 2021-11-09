package puzzle8;

import java.util.ArrayList;
import java.util.Arrays;

public class State {
    public int[] stateArr;
    public int row_col;

    public State() {
        this.row_col = 0;
    }

    public State(int row_col, int[] stateArr) {
        this.stateArr = stateArr;
        this.row_col = row_col;
    }

    public State(int[] stateArr) {
        this.stateArr = stateArr;
        this.row_col = (int)Math.sqrt(stateArr.length);
    }

    public State(State state) {
        this(state.getRow_col(), state.getStateArr());
        //for (int i=0; i< row_col * row_col; i++)
        //    this.stateArr[i] = state.stateArr[i];
    }

    public int[] getStateArr() {
        return stateArr;
    }

    public void setStateArr(int[] stateArr) {
        this.stateArr = stateArr;
    }

    public int getRow_col() {
        return row_col;
    }

    public void setRow_col(int row_col) {
        this.row_col = row_col;
    }

    public int findIndexOfEmptyTile() {
        for (int i=0; i<this.stateArr.length; i++) {
            if (this.stateArr[i] == 0) {
                System.out.println("Empty index: " + i);
                return i;
            }
        }
        return -1;
    }

    public int[] swapWithEmpty(int[] arr, int i, int j) {
        //System.out.println("i: j " + i + " " + j);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }


    public State moveUp() {
        int index_empty = this.findIndexOfEmptyTile();
        int[] copy = Arrays.copyOf(this.stateArr, this.stateArr.length);

        if (index_empty >= this.getRow_col()) {
            State new_state = new State(this.row_col,
                    this.swapWithEmpty(copy, index_empty, index_empty - this.row_col));
            return new_state;
            //return swapWithEmpty(copy, index_empty, index_empty - this.row_col);
        } else {
            return null;
        }
    }

    //for 3x3, if index of empty tile is not in the last row
    // (< 6, i.e index is < stateArr.length - row_col), we can move down
    public State moveDown() {
        int index_empty = this.findIndexOfEmptyTile();
        int[] copy = Arrays.copyOf(this.stateArr, this.stateArr.length);

        if (index_empty < this.stateArr.length - this.row_col) {
            State new_state = new State(this.row_col,
                    this.swapWithEmpty(copy, index_empty, index_empty + this.row_col));
            //new_state.swapWithEmpty(index_empty, index_empty + this.row_col);
            return new_state;
        } else {
            return null;
        }
    }

    //for 3x3, if index of empty tile is not in the 1st column
    // (i.e index % row_col != 0), we can move left
    public State moveLeft() {
        int index_empty = this.findIndexOfEmptyTile();
        int[] copy = Arrays.copyOf(this.stateArr, this.stateArr.length);

        if (index_empty % this.row_col != 0) {
            State new_state = new State(this.row_col,
                    this.swapWithEmpty(copy, index_empty, index_empty - 1));
            return new_state;
        } else {
            return null;
        }
    }

    //for 3x3, if index of empty tile is not in the 1st column
    // (i.e index + 1 % row_col != 0), we can move right
    public State moveRight() {
        int index_empty = this.findIndexOfEmptyTile();
        int remainder = (index_empty + 1) % this.row_col;
        int[] copy = Arrays.copyOf(this.stateArr, this.stateArr.length);

        //System.out.println("remainder: " + remainder);
        if (remainder != 0) {
            State new_state = new State(this.row_col,
                    this.swapWithEmpty(copy, index_empty, index_empty + 1));
            return new_state;
        } else {
            return null;
        }
    }

    public void display() {
        int index = 0;
        for (int i=0; i<this.row_col; i++) {
            String str = "";
            for (int j = 0; j < this.row_col; j++) {
                str += this.stateArr[index] + "   ";
                index++;
            }
            System.out.println(str);
        }
        System.out.println("\n");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;
        State state = (State) o;
        return Arrays.equals(stateArr, state.stateArr);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(stateArr);
    }

    public static void main(String[] args) {
        int[] initArr = new int[]{1, 2, 3, 4, 5, 6, 0, 7, 8};
        State initial = new State(initArr);

        initial.display();

    }

}
