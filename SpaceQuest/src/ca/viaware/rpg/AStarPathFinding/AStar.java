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
        public Path shortestPath;
        private TempDebugScreen debugScreen;
        private boolean mapExists = false;
     

        public AStar(AStarHeuristic heuristic) {
                
                this.heuristic = heuristic;

                closedList = new ArrayList<Node>();
                openList = new SortedNodeList();
                
        }
        
        public void updatemap(mobMap map){
        	this.map =map;
        	
        	if (!mapExists){
        		mapExists = true;
        		debugScreen = new TempDebugScreen(map.mapWidth, map.mapHeight);
        		System.out.println("Debug screen initialized");
        	}
        }

        public int numberOfWaypointsLeft(){
        	
        	
        	return shortestPath.getLength();
        	
        }
        public Path calcShortestPath(int x, int y, int d, int e) {
        	try{
               	shortestPath.removeall();
        	}catch(Exception E){
        		
        	}
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

                        //get the first Node from non-searched Node list, sorted by lowest distance from our goal as guessed by heuristic
                        Node current = openList.getFirst();

                        // check if goal reached
                        if( current.getY() == map.getGoalLocationY()&&current.getX() == map.getGoalLocationX()) {
                                return reconstructPath(current);
                        }

                        //switch list
                        openList.remove(current);
                        closedList.add(current);

                        //go through all the current Nodes neighbors and calculate if one should be next step
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

        
        public int getNextWaypointX(){ 	
        	int X;
        	try{
        	X = shortestPath.getWayPoint(1).getX();
        	}catch(Exception e){
        		try{
        		X = shortestPath.getWayPoint(0).getX();
        		}catch(Exception e1){
        			X = 0;
        		}
        	}
        	X++;
        
        	System.out.println("X"+X);
        	
        	return X;
        }
        public int getNextWaypointY(){ 	
        	int Y;
        	try{ 
        	Y = shortestPath.getWayPoint(1).getY();
        	}catch(Exception e){
        		try{
            		Y = shortestPath.getWayPoint(0).getY();
            		}catch(Exception e1){
            			Y = 0;
            		}
        	}
        	
        	return Y;
        	
        }
        
        public void printPath() {
            Node node;
            if (mapExists){
            debugScreen.reset();
            for(int x=0; x<map.mapWidth; x++) {

                    if (x==0) {
                            for (int i=0; i<=map.mapWidth; i++)
                            	debugScreen.addText("-");
                            debugScreen.nextLine(); 
                    }
                    debugScreen.addText("|");

                    for(int y=0; y<map.mapHeight; y++) {
                    	
                    		
                    	
                            node = map.getNode(y, x);
                            
                            if (node.isObstacle) {
                            	debugScreen.addText("X");
                            } else if (node.isStart) {
                                debugScreen.addText("S");
                            } else if (node.isGoal) {
                            	debugScreen.addText("*");
                            } else if (shortestPath.haswaypoint(node.getX(), node.getY())) {
                            	debugScreen.addText("W");
                            } else {
                            	debugScreen.addText(" ");
                            }
                            if (y==map.mapWidth)
                            	debugScreen.addText("_");
                    }
                   
                    debugScreen.addText("|");
                    if (x+1 < 10){
                    	debugScreen.addText("X 0"+(x+1));
                    }else{
                    	debugScreen.addText("X "+(x+1));
                    }
                    
                    debugScreen.nextLine();
            }
            for (int i=0; i<=map.mapWidth; i++)
                    debugScreen.addText("-");
    }
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
