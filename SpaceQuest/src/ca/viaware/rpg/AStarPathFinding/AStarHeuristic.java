package ca.viaware.rpg.AStarPathFinding;

public interface AStarHeuristic {

        //this tries to guess how far two nodes are
		//the closer the more likelyit will be searched
        public float getEstimatedDistanceToGoal(int startX, int startY, int goalX, int goalY);
}