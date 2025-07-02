/*
DFS, 탐색 경로 백트래킹
양을 찾을 때마다 캡쳐 -> 상태를 저장하고 0번 노드부터 다시 시작
*/

import java.util.*;

class Solution {
    
    private static final Map<Integer, List<Integer>> edgesMap = new HashMap<>();
    private static final Set<CapturedRoute> visited = new HashSet<>();
    
    public int solution(int[] info, int[][] edges) {
        
        for (int i = 0; i < edges.length; i++) {
            if (!edgesMap.containsKey(edges[i][0])) {
                List<Integer> arr = new ArrayList<>(List.of(edges[i][1]));
                edgesMap.put(edges[i][0], arr);
            } else {
                edgesMap.get(edges[i][0]).add(edges[i][1]);
            }
        }
        
        CapturedRoute first = new CapturedRoute(new HashSet<>(List.of(0)), new HashSet<>());
        Queue<CapturedRoute> q = new LinkedList<>(List.of(first));
        
        while (!q.isEmpty()) {
            CapturedRoute cr = q.poll();
            visited.add(cr);
            while(true) {
                CapturedRoute newCr = dfs(cr, 0, new ArrayList<>(List.of(0)), info);
                if (newCr == null) {
                    break;
                }
                q.add(newCr);
            } 
        }
        
        int res = 0;
        for (CapturedRoute c : visited) {
            res = Math.max(res, c.sheeps.size());
        }
        return res;
    }
    
    public CapturedRoute dfs(CapturedRoute cr, int current, List<Integer> visitedNodes, int[] info) {
        
        if (info[current] == 0 && !cr.sheeps.contains(current)) {
            List<Integer> sheeps = new ArrayList<>();
            List<Integer> wolfs = new ArrayList<>();
            for (int node : visitedNodes) {
                if (info[node] == 0) sheeps.add(node);
                else wolfs.add(node);
            }
            
            CapturedRoute newCr = new CapturedRoute(sheeps, wolfs, cr);
            if (!visited.contains(newCr)) {
                visited.add(newCr);
                return newCr;
            }
        }
        
        if (!edgesMap.containsKey(current)) return null;
        
        for (int next : edgesMap.get(current)) {
            int wolfCnt = 0;
            visitedNodes.add(next);
            for (int visitedNode : visitedNodes) {
                if (info[visitedNode] == 1 && !cr.wolfs.contains(visitedNode)) {
                    wolfCnt++;
                }
            }
            
            // 양의 갯수보다 늑대의 갯수가 많거나 같으면 경로 사용 X
            if (cr.sheeps.size() <= cr.wolfs.size() + wolfCnt) {
                visitedNodes.remove(visitedNodes.size()-1);
                continue;
            }
            
            CapturedRoute newCr = dfs(cr, next, visitedNodes, info);
            visitedNodes.remove(visitedNodes.size()-1);
            if (newCr != null) return newCr;
        }
        
        return null;
    }
    
    class CapturedRoute {
        Set<Integer> sheeps;
        Set<Integer> wolfs;
        
        public CapturedRoute(Set<Integer> sheeps, Set<Integer> wolfs) {
            this.sheeps = new HashSet<>(sheeps);
            this.wolfs = new HashSet<>(wolfs);
        }
        
        public CapturedRoute(List<Integer> sheeps, List<Integer> wolfs, CapturedRoute c) {
            this.sheeps = new HashSet<>();
            this.wolfs = new HashSet<>();
            this.sheeps.addAll(sheeps);
            this.sheeps.addAll(c.sheeps);
            this.wolfs.addAll(wolfs);
            this.wolfs.addAll(c.wolfs);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(sheeps, wolfs);
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            CapturedRoute oRoute = (CapturedRoute) o;
            return Objects.equals(sheeps, oRoute.sheeps) && Objects.equals(wolfs, oRoute.wolfs);
        }
        
        @Override
        public String toString() {
            return sheeps + " " + wolfs + "\n";
        }
    }
}