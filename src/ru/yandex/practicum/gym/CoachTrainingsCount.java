package ru.yandex.practicum.gym;

public class CoachTrainingsCount {
    private final Coach coach;
    private final int count;

    public CoachTrainingsCount(Coach coach, int count) {
        this.coach = coach;
        this.count = count;
    }

    public Coach getCoach() {
        return coach;
    }

    public int getCount() {
        return count;
    }
}