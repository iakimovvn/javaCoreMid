package ru.yakimovvn.ObstacleCourse;

import ru.yakimovvn.Tenderers.Competitor;
import ru.yakimovvn.Tenderers.Team;


public class Course {
    private Obstacle [] distance;

    public Course(Obstacle ... distance){
        this.distance = distance;
    }

    public void passingDistance(Team team) {
        for(Competitor player : team.getPlayers()){
            for (Obstacle obstacle: distance) {
                obstacle.doIt(player);
            }
        }
    }
}
