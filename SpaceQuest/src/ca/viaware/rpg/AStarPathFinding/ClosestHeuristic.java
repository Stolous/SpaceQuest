package ca.viaware.rpg.AStarPathFinding;

public class ClosestHeuristic implements AStarHeuristic {

        public float getEstimatedDistanceToGoal(int startX, int startY, int goalX, int goalY) {         
                float dx = goalX - startX;
                float dy = goalY - startY;
                
                
                //euclid style (pythagorean theroem)
                float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
                
                
                
               // manhatten style
               // float result = (float) (dx*dx)+(dy*dy);
                
                
                return result;
        }

	
}
