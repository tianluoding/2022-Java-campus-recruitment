import java.util.*;

 
class Solution{
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public int[] levelOrder(TreeNode root) {
        if(root == null)
            return null;
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            while(count > 0){
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
                count--;
            }
            res.add(list);
        }
        List<Integer> res_list = new ArrayList<>();
        for(List<Integer> l : res) {
            l.sort(Comparator.naturalOrder());;
            for(Integer i : l) {
                res_list.add(i);
            }
        }
        int[] l =  res_list.stream().mapToInt(Integer::valueOf).toArray();
        return l;
    }
}
