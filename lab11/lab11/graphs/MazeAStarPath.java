package lab11.graphs;

import edu.princeton.cs.algs4.In;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private int targetX;
    private int targetY;
    private PriorityQueue<Integer> frontier;
    private HashMap<Integer, Integer> priorityMap;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        this.targetX = targetX;
        this.targetY = targetY;
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;

        priorityMap = new HashMap<>();
        frontier = new PriorityQueue<>(Comparator.comparingInt(priorityMap::get));
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int sourceX = maze.toX(v);
        int sourceY = maze.toY(v);
        return Math.abs(sourceX - targetX) + Math.abs(sourceY - targetY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        priorityMap.put(s, distTo[s] + h(s));
        frontier.add(s);
        while (!frontier.isEmpty()) {
            int p = frontier.poll();
            marked[p] = true;
            announce();
            if (p == t) {
                return;
            }
            for (int i : maze.adj(p)) {
                relax(p, i);
            }
        }
    }

    private void relax(int x, int y) {
        if (!marked[y]) {
            int current = distTo[x] + 1;
            if (current < distTo[y]) {
                distTo[y] = current;
                edgeTo[y] = x;
                int priority = distTo[y] + h(y);
                priorityMap.put(y, priority);
                frontier.remove(y);
                frontier.add(y);
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }


}

