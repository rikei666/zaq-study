package com.zaqbest.study.misc;

import java.util.*;

/**
 * 思路描述
 *     - 将原矩阵简化成0，1（大于等于0.5转换为1，小于0.5转换为0）
 *     - 构建每个位置i的元素和与他相邻的节点放到nexts列表中
 *     - 将列表放入大根堆，按照相邻元素从大到小排序
 *     - 依次取出每一个元素X删除，并把邻接表中对应元素的nexts中删除X
 *     - 知道队列中的所有元素的nexts都变成0，那么这些元素就是完全独立的，输出即可
 */
public class V2exAlg {
    public static void main(String[] args) {
        //原矩阵简化成联通为1，不连通为0
        int[][] matrix = new int[][]{
                {1,},
                {0, 1,},
                {0,0, 1},
                {1,0,0,1},
                {1,1,1,0,1},
                {1,0,0,1,1,1},
                {0,0,0,0,1,1,1}
        };
//        int N = 5000;
//        int[][] matrix = new int[N][N];
//        Random random = new Random();
//        for (int col = 0; col < N; col++){
//            for (int row = col+1; row < N; row++){
//                matrix[row][col] = random.nextInt(10) > 5 ? 1 : 0;
//            }
//        }
        long start = System.currentTimeMillis();
        List<Integer> ans = f(matrix);
        System.out.println("用时总共：" + (System.currentTimeMillis()-start)+"ms");
        System.out.println(ans);
    }
    public static List<Integer> f(int[][] matrix){
        int N = matrix.length;
        Node[] nodes = new Node[N];

        //构建每个节点，并初始化邻接元素
        for (int i = 0; i < N; i++){
            nodes[i] = new Node(i);
        }

        //构建节点关联关系
        //long start = System.currentTimeMillis();
        for (int col = 0; col < N; col++){
            for (int row = col+1; row < N; row++){
                if (matrix[row][col] == 1){
                    nodes[row].nexts.add(nodes[col]);
                    nodes[col].nexts.add(nodes[row]);
                }
            }
        }
        //System.out.println("用时2：" + (System.currentTimeMillis()-start)+"ms");

        //构建大根堆，按照邻接数量从大到小排序
        MyHeap<Node> queue = new MyHeap<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.nexts.size() - o1.nexts.size();
            }
        });
        //所有节点加入队列
        List<Integer> ans = new LinkedList<>();
        //start = System.currentTimeMillis();
        for (Node node: nodes){
            if (node.nexts.size() == 0){
                ans.add(node.index);
                continue;
            }
            queue.push(node);
        }
        //System.out.println("用时3：" + (System.currentTimeMillis()-start)+"ms");

        //start = System.currentTimeMillis();
        while (!queue.isEmpty() && queue.peek().nexts.size() > 0){
            Node node = queue.pop();
            for (Node nextItem: node.nexts){
                nextItem.nexts.remove(node);
                //邻接表中对应节点去除node,然后重新调整堆
                queue.resign(nextItem);
            }
        }
        //System.out.println("用时4：" + (System.currentTimeMillis()-start)+"ms");

        //收集答案
        while (!queue.isEmpty()){
            ans.add(queue.pop().index);
        }

        return ans;
    }

    static class Node{
        public int index;
        public List<Node> nexts;

        public Node(int index) {
            this.index = index;
            nexts = new ArrayList<>();
        }
    }

    /**
     * 改写之后的堆排序，支持动态调整
     * @param <T>
     */
    public static class MyHeap<T> {
        private ArrayList<T> heap;
        private HashMap<T, Integer> indexMap; //用于保存对象及其在数组中的索引值
        private int heapSize;
        private Comparator<? super T> comparator;

        public MyHeap(Comparator<? super T> com) {
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            heapSize = 0;
            comparator = com;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public int size() {
            return heapSize;
        }

        public boolean contains(T key) {
            return indexMap.containsKey(key);
        }

        public void push(T value) {
            heap.add(value);
            indexMap.put(value, heapSize);
            heapInsert(heapSize++);
        }

        public T peek(){
            return heap.get(0);
        }

        public T pop() {
            T ans = heap.get(0);
            int end = heapSize - 1;
            swap(0, end);
            heap.remove(end);
            indexMap.remove(ans);
            heapify(0, --heapSize);
            return ans;
        }

        public void resign(T value) {
            int valueIndex = indexMap.get(value);
            heapInsert(valueIndex);
            heapify(valueIndex, heapSize);
        }

        private void heapInsert(int index) {
            while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int largest = left + 1 < heapSize && (comparator.compare(heap.get(left + 1), heap.get(left)) < 0)
                        ? left + 1
                        : left;
                largest = comparator.compare(heap.get(largest), heap.get(index)) < 0 ? largest : index;
                if (largest == index) {
                    break;
                }
                swap(largest, index);
                index = largest;
                left = index * 2 + 1;
            }
        }

        private void swap(int i, int j) {
            T o1 = heap.get(i);
            T o2 = heap.get(j);
            heap.set(i, o2);
            heap.set(j, o1);
            indexMap.put(o1, j);
            indexMap.put(o2, i);
        }

    }
}
