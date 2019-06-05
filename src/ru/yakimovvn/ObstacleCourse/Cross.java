package ru.yakimovvn.ObstacleCourse;

import ru.yakimovvn.Tenderers.Competitor;

public class Cross extends Obstacle{
    private int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.run(length);
    }
}
