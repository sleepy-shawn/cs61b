package lab11.graphs;
import edu.princeton.cs.algs4.Stack;

/**
 * Detects cycles in a maze using DFS.
 */
public class MazeCycles extends MazeExplorer {
    private Maze maze;
    private int[] parent;
    private boolean cycleFound = false;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        parent = new int[maze.V()];
        for (int i = 0; i < maze.V(); i++) {
            parent[i] = -1; // Initialize parent array with -1
        }
    }

    @Override
    public void solve() {
        for (int v = 0; v < maze.V(); v++) {
            if (!marked[v] && !cycleFound) {
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        marked[v] = true;
        announce();

        for (int w : maze.adj(v)) {
            if (cycleFound) return;

            // If neighbor is not visited, continue DFS
            if (!marked[w]) {
                parent[w] = v;
                edgeTo[w] = v;
                dfs(w);
            }
            // If visited and not the parent, cycle found
            else if (w != parent[v]) {
                cycleFound = true;
                edgeTo[w] = v;
                announce();
                return;
            }
        }
    }
}
