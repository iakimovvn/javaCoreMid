package ru.yakimovvn.ObstacleCourse;

import ru.yakimovvn.Tenderers.Competitor;

public class Water extends Obstacle {
    private int length;
    public Water(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(length);
    }
}
