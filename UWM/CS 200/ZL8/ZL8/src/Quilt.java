public class Quilt {

    public static void main(String[] args) {

        char [][] myBlock;
        char [][] myQuilt;

        myBlock = new char[][]{{'x','.','.','.','.'},{'x', '+', '.', '.', '.'}, {'x','+','+','.','.'}, {'x','x','x','x','x'}};
        myQuilt = new char[myBlock.length*3][myBlock[0].length*4];
        createQuilt(myQuilt, myBlock);
        displayPattern(myQuilt);
    }

    public static void displayPattern(char[][] myArray) {
        int i;
        int j;

        for (char[] array:myArray) {
            for(char element:array) {
                System.out.print(element);
            }
            System.out.println();
        }
    }

    public static void createQuilt(char[][] quilt, char[][] block) {
        /*TODO Tasks 4, 5, and 6 */
        int i;
        int j;
        for (i=0; i<block.length*3; i=i+4) {
            for (j=0; j<block[0].length*4; j=j+5) {
                placeBlock(quilt, block, i, j);
            }
        }
    }

    /**
     * Places the 2-D character array block into the 2-D character array quilt
     * starting with the upper left hand corner of the block placed into
     * position in the quilt at startRow, startCol
     *
     * Note: This is implemented for you, DO NOT CHANGE THIS METHOD.
     */
    public static void placeBlock(char[][] quilt, char[][] block,
                                  int startRow, int startCol){
        for (int r = 0; r < block.length; r++) {
            for (int c = 0; c < block[r].length; c++) {
                quilt[r+startRow][c+startCol] = block[r][c];
            }
            createFlipped(block);
        }
    }

    /**
     * Returns a 2-D array which is the horizontally flipped version
     * of the block parameter.
     */
    public static char[][] createFlipped(char[][] block) {
        int i;
        int j;

        for (i= block.length-1; i>-1; i--) {
            for (j=0; j<block[i].length; j++) {
                System.out.print(block[i][j]);
            }
            System.out.println();
        }
        return block;
    }
}