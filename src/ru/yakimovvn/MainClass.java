package ru.yakimovvn;

public class MainClass {
    public static void main(String[] args) {
        Team team = new Team("Под асфальт укладчики");
        team.teamInfo();

        Course course = new Course(new double[]{0.2,0.7,1.2,1.0,1.8,1.6,2.0,1.9,2.4,2.1,2.5});
        course.passingDistance(team);

    }
}
