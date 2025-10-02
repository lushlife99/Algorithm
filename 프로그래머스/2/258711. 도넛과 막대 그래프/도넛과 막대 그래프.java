/*
inDegree가 없는 노드, outDegree가 2이상인 노드 -> 추가된 노드
추가된 노드를 기점으로 bfs
*/

import java.util.*;
import java.util.stream.*;

class Solution {
    
    private List<Edge>[] edges;
    private int[] inDegree;
    private int[] outDegree;
    
    class Edge {
        int id, from, to;
        
        public Edge(int id, int from, int to) {
            this.id = id; this.from = from; this.to = to;
        }
        
        @Override
        public boolean equals(Object o) {
            Edge oE = (Edge) o;
            return oE.id == this.id;
        }
        
        @Override
        public int hashCode() {
            return this.id;
        }
    }
    
    public int[] solution(int[][] es) {
        
        int size = 0;
        for (int i = 0; i < es.length; i++) {
            int max = Math.max(es[i][0], es[i][1]);
            size = Math.max(size, max);
        }
        
        edges = IntStream.rangeClosed(0, size)
            .mapToObj(i -> new ArrayList<>())
            .toArray(List[]::new);
        inDegree = new int[size+1];
        outDegree = new int[size+1];
        
        int id = 0;
        for (int i = 0; i < es.length; i++) {
            int from = es[i][0];
            int to = es[i][1];
            
            edges[from].add(new Edge(++id, from, to));
            outDegree[from]++;
            inDegree[to]++;
        }
        
        int appendedNode = 0;
        
        for (int i = 0; i <= size; i++) {
            if (inDegree[i] == 0 && outDegree[i] >= 2) {
                appendedNode = i;
                break;
            }
        }
        
        int[] answer = {appendedNode, 0, 0, 0};
        
        for (Edge e : edges[appendedNode]) {
            int[] res = bfs(e.to);
            int nodeSize = res[0];
            int edgeSize = res[1];
            if (nodeSize == edgeSize) {
                answer[1]++;
            } else if (nodeSize - 1 == edgeSize) {
                answer[2]++;
            } else answer[3]++;
        }
        
        
        return answer;
    }
    
    private int[] bfs(int start) {
        Set<Edge> usedE = new HashSet<>();
        Set<Integer> usedN = new HashSet<>();
        
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        
        while (!q.isEmpty()) {
            int current = q.poll();
            usedN.add(current);
            for (Edge e : edges[current]) {
                if (!usedE.contains(e)) {
                    usedE.add(e);
                    q.add(e.to);
                }
            }
        }
        
        return new int[]{usedN.size(), usedE.size()};
    }
}