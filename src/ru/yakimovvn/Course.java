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
        output:
        for (int i = 0; i <team.getPlayers().length; i++) {
            for (double obstacle : distance) {
                if(!isObstaclePassed(team.getPlayers()[i].getMaxJump(),obstacle)){
                    break output;
                }
            }
            team.pastDistancePlayers(team.getPlayers()[i]);
        }
    }


}
