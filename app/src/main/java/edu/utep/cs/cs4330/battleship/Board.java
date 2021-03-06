package edu.utep.cs.cs4330.battleship;

/**
 * A game board consisting of <code>size</code> * <code>size</code> places
 * where battleships can be placed. A place of the board is denoted
 * by a pair of 0-based indices (x, y), where x is a column index
 * and y is a row index. A place of the board can be shot at, resulting
 * in either a hit or miss.
 */
class Board {

    /**
     * Size of this board. This board has
     * <code>size*size </code> places.
     */
    private final int size;
    private int x;
    private int y;

    /**
     * Create a new board of the given size.
     */
    Board(int size) {
        this.size = size;
    }

    /**
     * Return the size of this board.
     */
    int size() {
        return size;
    }

}
