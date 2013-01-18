package ca.viaware.rpg.AStarPathFinding;

import java.util.ArrayList;

public class Path {
        private ArrayList<Node> waypoints = new ArrayList<Node>();
        
        public Path() {
        }
        public void removeall(){
        	waypoints.removeAll(waypoints);
        }
        
        public int getLength() {
                return waypoints.size();
        }

        public Node getWayPoint(int index) {
                return waypoints.get(index);
        }

        
       
        public int getX(int index) {
                return getWayPoint(index).getX();
        }

        
        public int getY(int index) {
                return getWayPoint(index).getY();
        }

        
        public void changeWayPoint(Node n) {
                waypoints.add(n);
        }

        
        public void addWayPoint(Node n) {
                waypoints.add(0, n);
        }
        public void addWayPoint(Node n,int location) {
            waypoints.add(location, n);
    }

        
        public boolean haswaypoint(int x, int y) {
                for(Node node : waypoints) {
                        if (node.getX() == x && node.getY() == y)
                                return true;
                }
                return false;
        }

}
