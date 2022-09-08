import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * 9.3 美团第三题
 */

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] tree = new int[n];

        for (int i = 1; i < n; i++) {
            tree[i] = sc.nextInt();
        }
        String str = sc.next();

        char[] node = str.toCharArray();

        List<List<Integer>> g = new ArrayList<List<Integer>>();
        for (int i = 0; i < n + 1; i++) {
            g.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int parent = tree[i];
            int son = i + 1;
            // g[parent].push_back(son);
            g.get(parent).add(son);
        }
        int[] mp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            mp[i] = g.get(i).size();
        }

        List<Set<Character>> res = new ArrayList<Set<Character>>();
        for (int i = 0; i < n + 1; i++) {
            res.add(new HashSet<>());
        }
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            if (mp[i] == 0) {
                q.offer(i);
                res.get(i).add(node[i - 1]);
            }
        }
        while (!q.isEmpty()) {
            int son = q.peek();
            q.poll();
            if (son > 1) {
                int parent = tree[son - 1];
                for (Character i : res.get(son)) {
                    res.get(parent).add(i);
                }
                res.get(parent).add(node[parent - 1]);
                q.offer(parent);
            }
        }
        // for(int i = 1; i <= n; i ++) cout << res[i].size() << ' ';
        for (int i = 1; i <= n; i++)
            System.out.print(res.get(i).size() + " ");

    }

}