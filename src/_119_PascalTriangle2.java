import java.util.ArrayList;
import java.util.List;

/**
 * 119. Pascal's Triangle 2 (Easy)
 *
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 *
 * Note that the row index starts from 0.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 3
 * Output: [1,3,3,1]
 * Follow up:
 *
 * Could you optimize your algorithm to use only O(k) extra space?
 *
 * */
public class _119_PascalTriangle2 {

    public static void main(String[] args) {
        int rowIndex = 33;
        List<Integer> result = getRow(rowIndex);
        for (int j = 0; j < result.size(); j++) {
            System.out.print(result.get(j) + " ");
        }
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();

        if (rowIndex < 0) {
            return list;
        }

        for (int i = 0; i < rowIndex + 1; i++) {
            list.add(0, 1);
            for (int j = 1; j < list.size() - 1; j++) {
                list.set(j, list.get(j) + list.get(j + 1));
            }

        }
        return list;
    }
}
