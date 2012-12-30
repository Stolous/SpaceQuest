package ca.viaware.rpg.AStarPathFinding;

import java.util.ArrayList;
import java.util.Collections;



import ca.viaware.rpg.AStarPathFinding.AStarHeuristic;


public class AStar {
        private mobMap map;
        private AStarHeuristic heuristic;    
         //closed list= nodes not searched sorted via heuristic               
        private ArrayList<Node> closedList;
        private SortedNodeList openList;
        private Path shortestPath;
     

        public AStar(mobMap map, AStarHeuristic heuristic) {
                this.map = map;
                this.heuristic = heuristic;

                closedList = new ArrayList<Node>();
                openList = new SortedNodeList();
        }
        
        

        public Path calcShortestPath(int x, int y, int d, int e) {
               
                map.setStartLocation(x, y);
                map.setGoalLocation(d, e);

                //check if possible goal
                if (map.getNode(d, e).isObstacle) {
                        return null;
                }

                map.getStartNode().setDistanceFromStart(0);
                closedList.clear();
                openList.clear();
                openList.add(map.getStartNode());

                //while goal not reached
                while(openList.size() != 0) {

                        //get the first Node from non-searched Node list, sorted by lowest distance from our goal as guessed by our heuristic
                        Node current = openList.getFirst();

                        // check if goal reched
                        if( current.getY() == map.getGoalLocationY()&&current.getX() == map.getGoalLocationX()) {
                                return reconstructPath(current);
                        }

                        //switch list
                        openList.remove(current);
                        closedList.add(current);

                        //go through all the current Nodes neighbors and calculate if one should be our next step
                        for(Node neighbor : current.getNeighborList()) {
                                boolean neighborIsBetter;
                                if (closedList.contains(neighbor))
                                        continue;
                                if (!neighbor.isObstacle) {

                                        // cost calculator
                                        float neighborDistanceFromStart = (current.getDistanceFromStart() + map.getDistanceBetween(current, neighbor));

                                        //add neighbor to the open list if it is not there
                                        if(!openList.contains(neighbor)) {
                                                openList.add(neighbor);
                                                neighborIsBetter = true;
                                        } else if(neighborDistanceFromStart < current.getDistanceFromStart()) {
                                                neighborIsBetter = true;
                                        } else {
                                                neighborIsBetter = false;
                                        }
                                        if (neighborIsBetter) {
                                                neighbor.setPreviousNode(current);
                                                neighbor.setDistanceFromStart(neighborDistanceFromStart);
                                                neighbor.setHeuristicDistanceFromGoal(heuristic.getEstimatedDistanceToGoal(neighbor.getX(), neighbor.getY(), map.getGoalLocationX(), map.getGoalLocationY()));
                                        }
                                }

                        }

                }
                return null;
        }

        
        
       

        private Path reconstructPath(Node node) {
                Path path = new Path();
                while(!(node.getPreviousNode() == null)) {
                        path.addWayPoint(node);
                        node = node.getPreviousNode();
                }
                this.shortestPath = path;
                return path;
        }

        
        public void printPath() {
            Node node;
            for(int x=0; x<map.mapWidth; x++) {

                    if (x==0) {
                            for (int i=0; i<=map.mapWidth; i++)
                                    System.out.print("-");
                            System.out.println();   
                    }
                    System.out.print("|");

                    for(int y=0; y<map.mapHeight; y++) {
                            node = map.getNode(y, x);
                            
                            if (node.isObstacle) {
                                    System.out.print("X");
                            } else if (node.isStart) {
                                    System.out.print("s");
                            } else if (node.isGoal) {
                                    System.out.print("*");
                            } else if (shortestPath.haswaypoint(node.getX(), node.getY())) {
                                    System.out.print("O");
                            } else {
                                    System.out.print("-");
                            }
                            if (y==map.mapWidth)
                                    System.out.print("_");
                    }
                   
                    System.out.print("|");
                    System.out.println("X "+x);
            }
            for (int i=0; i<=map.mapWidth; i++)
                    System.out.print("-");
    }
        private class SortedNodeList {

                private ArrayList<Node> list = new ArrayList<Node>();

                public Node getFirst() {
                        return list.get(0);
                }

                public void clear() {
                        list.clear();
                }

                public void add(Node node) {
                        list.add(node);
                        Collections.sort(list);
                }

                public void remove(Node n) {
                        list.remove(n);
                }

                public int size() {
                        return list.size();
                }

                public boolean contains(Node n) {
                        return list.contains(n);
                }
        }

}
