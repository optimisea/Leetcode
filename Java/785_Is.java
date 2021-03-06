Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in 
the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  
 Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i,
and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
 

Note:

graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].

class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        for (int i = 0; i < n; i++){
            if (color[i] == 0){
                color[i] = -1;
            }
            if (!dfs(graph, color, i)){
                return false;
            }
        }
        return true;
    }
    private boolean dfs(int[][] graph, int[] color, int start){
        for (int nei : graph[start]){
            if (color[nei] == 0){
                color[nei] = -color[start];
                if (!dfs(graph, color, nei)){
                    return false;
                }
            }else{
                if (color[nei] == color[start]){
                    return false;
                }
            }
        }
        return true;
    }
}


Better version:
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        for (int i = 0; i < n; i++){ //This graph might be a disconnected graph. So check each unvisited node.
            if (colors[i] == 0 && !isValid(graph, i, 1, colors)){
                return false;
            }
        }
        return true;
    }
    private boolean isValid(int[][] graph, int curr, int color, int[] colors){
        colors[curr] = color;
        for (int nei : graph[curr]){
            if (colors[nei] == 0){ 
                if(!isValid(graph, nei, -color, colors))
                    return false;
            }else if (colors[nei] == color){
                return false;
            }
        }
        return true;
    }
}
