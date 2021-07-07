package ru.yakimovvn;

class Course {
    private double [] distance;

    Course(double [] distance){
        this.distance = distance;
    }

    private boolean isObstaclePassed(double maxJump, double obstacle){
        return maxJump>=obstacle;
    }

    void passingDistance(Team team) {

        for (int i = 0; i <team.getPlayers().length; i++) {
            for (double obstacle : distance) {
                if(!isObstaclePassed(team.getPlayers()[i].getMaxJump(),obstacle)){
                    team.getPlayers()[i].setPastDistance(false);
                    break;
                }
            }
        }
    }


}
