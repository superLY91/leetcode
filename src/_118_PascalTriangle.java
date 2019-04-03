import java.util.ArrayList;
import java.util.List;

/**
 * 118. Pascal's Triangle (Easy)
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 * */
public class _118_PascalTriangle {

    public static void main(String[] args) {
        int numRows = 5;
        List<List<Integer>> result = generate(numRows);

        for (int i = 0; i < numRows; i++) {
            List<Integer> list = result.get(i);
            int size = list.size();
            for (int j = 0; j < size; j++) {
                System.out.print(list.get(j) + " ");
            }
            System.out.println();
        }
    }

    /**
     * Approach 1: Dynamic Programming
     * Intuition
     *
     * If we have the a row of Pascal's triangle, we can easily compute the next row by each pair of adjacent values.
     *
     * Algorithm
     *
     * Although the algorithm is very simple, the iterative approach to constructing Pascal's triangle can be classified
     * as dynamic programming because we construct each row based on the previous row.
     *
     * First, we generate the overall triangle list, which will store each row as a sublist.
     * Then, we check for the special case of 00, as we would otherwise return [1][1].
     * If numRows > 0numRows>0, then we initialize triangle with [1][1] as its first row, and proceed to fill the rows as follows:
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) {
            return result;
        }
        result.add(new ArrayList<>());
        result.get(0).add(1);
        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> preRow = result.get(rowNum - 1);
            row.add(1);
            for (int j = 1; j < rowNum; j++) {
                row.add(preRow.get(j - 1) + preRow.get(j));
            }
            row.add(1);
            result.add(row);
        }
        return result;
    }
}
