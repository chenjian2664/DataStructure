package leetcode;

import java.util.*;

/**
 * Created by chenjian
 * 2018/8/19 16:13
 */
public class TopKFrequent {

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(/*new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        }*/

                (o1, o2) -> map.get(o1) - map.get(o2)

        );

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (count < k) {
                queue.add(entry.getKey());
                ++count;
            } else if (entry.getValue() > map.get(queue.peek())) {
                queue.remove();
                queue.add(entry.getKey());
            }
        }

        return new LinkedList<>(queue);
    }

    public static void main(String[] args) {
       int nums1[] = new int[] {1,1,1,2,2,3,4,5,6};
       List<Integer> list = new TopKFrequent().topKFrequent(nums1, 2);
       for (int i : list) {
           System.out.println(i);
       }
    }
}
