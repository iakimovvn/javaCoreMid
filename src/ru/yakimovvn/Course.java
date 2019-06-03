package ru.yakimovvn;

public class Course {
    private double [] distance;

    public  Course(double [] distance){
        this.distance = distance;
    }

    private boolean isObstaclePassed(double maxJump, double obstacle){
        return maxJump>=obstacle;
    }

    public void passingDistance(Team team) {

        for (int i = 0; i <team.getPlayers().length; i++) {
            boolean distancePassed = true;
            for (double obstacle : distance) {
                if(!isObstaclePassed(team.getPlayers()[i].getMaxJump(),obstacle)){
                    distancePassed = false;
                    break;
                }
            }
            if(distancePassed) team.pastDistancePlayers(team.getPlayers()[i]);
        }
    }


}
