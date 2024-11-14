package lab11.graphs;
import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    Queue<Integer> frontier;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        frontier = new LinkedList<>();
        frontier.add(s);
        // Add more variables here!
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        while (!targetFound) {
            int fringe = frontier.remove();
            marked[fringe] = true;
            announce();

            if (fringe == t) {
                targetFound = true;
                return;
            }

            for (int w : maze.adj(fringe)) {
                if (!marked[w]) {
                    frontier.add(w);
                    edgeTo[w] = fringe;
                    distTo[w] = distTo[fringe] + 1;
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

