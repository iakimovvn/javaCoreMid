package ru.yakimovvn;

import ru.yakimovvn.ObstacleCourse.*;
import ru.yakimovvn.Tenderers.*;



public class MainClass {
    public static void main(String[] args) {
        Team team = new Team("Под асфальт укладчики",new Human("Владимир"),
                new Cat("Барсик"), new Dog("Жучка"),new Human("Сергей"));
        Course course = new Course(new Wall(20),new Water(50),new Cross(2000));
        course.passingDistance(team);
        team.showResults();
    }
}
