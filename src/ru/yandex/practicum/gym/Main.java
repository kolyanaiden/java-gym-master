package ru.yandex.practicum.gym;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Расписание для наглядности\n");

        // Создаем тренеров
        Coach coach1 = new Coach("Шмотков", "В.", "В.");
        Coach coach2 = new Coach("Семёнов", "В.", "К.");
        Coach coach3 = new Coach("Морев", "Е.", "В.");

        // Создаем группы
        Group childGroup = new Group("Тренировка 1", Age.CHILD, 60);
        Group adultGroup = new Group("Тренировка 2", Age.ADULT, 90);
        Group yogaGroup = new Group("Тренировка 3", Age.ADULT, 75);

        // Создаем расписание
        Timetable timetable = new Timetable();

        // Добавляем тренировки
        System.out.println("Добавляем тренировки в расписание");

        // Понедельник
        timetable.addNewTrainingSession(new TrainingSession(
                childGroup, coach1, DayOfWeek.MONDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(
                adultGroup, coach2, DayOfWeek.MONDAY, new TimeOfDay(18, 0)));
        timetable.addNewTrainingSession(new TrainingSession(
                yogaGroup, coach3, DayOfWeek.MONDAY, new TimeOfDay(20, 0)));

        // Вторник
        timetable.addNewTrainingSession(new TrainingSession(
                childGroup, coach1, DayOfWeek.TUESDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(
                adultGroup, coach2, DayOfWeek.TUESDAY, new TimeOfDay(18, 0)));

        // Среда
        timetable.addNewTrainingSession(new TrainingSession(
                yogaGroup, coach3, DayOfWeek.WEDNESDAY, new TimeOfDay(9, 0)));
        timetable.addNewTrainingSession(new TrainingSession(
                adultGroup, coach2, DayOfWeek.WEDNESDAY, new TimeOfDay(19, 0)));

        // Четверг
        timetable.addNewTrainingSession(new TrainingSession(
                childGroup, coach1, DayOfWeek.THURSDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(
                adultGroup, coach2, DayOfWeek.THURSDAY, new TimeOfDay(18, 0)));
        timetable.addNewTrainingSession(new TrainingSession(
                yogaGroup, coach3, DayOfWeek.THURSDAY, new TimeOfDay(20, 0)));

        // Пятница
        timetable.addNewTrainingSession(new TrainingSession(
                childGroup, coach1, DayOfWeek.FRIDAY, new TimeOfDay(10, 0)));

        // Суббота
        timetable.addNewTrainingSession(new TrainingSession(
                yogaGroup, coach3, DayOfWeek.SATURDAY, new TimeOfDay(11, 0)));
        timetable.addNewTrainingSession(new TrainingSession(
                adultGroup, coach2, DayOfWeek.SATURDAY, new TimeOfDay(12, 0)));

        System.out.println("Тренировки добавлены!\n");

        // Тест 1: Получить все тренировки на понедельник
        System.out.println("1. Все тренировки на понедельник");
        List<TrainingSession> mondaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        for (TrainingSession session : mondaySessions) {
            System.out.printf("- %02d:%02d: %s (тренер: %s %s.%s.)%n",
                    session.getTimeOfDay().getHours(),
                    session.getTimeOfDay().getMinutes(),
                    session.getGroup().getTitle(),
                    session.getCoach().getSurname(),
                    session.getCoach().getName().charAt(0),
                    session.getCoach().getMiddleName().charAt(0));
        }
        System.out.println();

        // Тест 2: Получить тренировки на понедельник в 10:00
        System.out.println("2. Тренировки на понедельник в 10:00");
        List<TrainingSession> monday10am = timetable.getTrainingSessionsForDayAndTime(
                DayOfWeek.MONDAY, new TimeOfDay(10, 0));
        if (monday10am.isEmpty()) {
            System.out.println("Нет тренировок в это время");
        } else {
            for (TrainingSession session : monday10am) {
                System.out.printf("- %s (тренер: %s)%n",
                        session.getGroup().getTitle(),
                        session.getCoach().getSurname());
            }
        }
        System.out.println();

        // Тест 3: Получить тренировки на среду в 19:00
        System.out.println("3. Тренировки на среду в 19:00");
        List<TrainingSession> wednesday7pm = timetable.getTrainingSessionsForDayAndTime(
                DayOfWeek.WEDNESDAY, new TimeOfDay(19, 0));
        if (wednesday7pm.isEmpty()) {
            System.out.println("Нет тренировок в это время");
        } else {
            for (TrainingSession session : wednesday7pm) {
                System.out.printf("- %s (тренер: %s)%n",
                        session.getGroup().getTitle(),
                        session.getCoach().getSurname());
            }
        }
        System.out.println();

        // Тест 4: Статистика по тренерам
        System.out.println("4. Статистика по количеству тренировок у тренеров");
        List<CoachTrainingsCount> coachStats = timetable.getCountByCoaches();
        for (CoachTrainingsCount stat : coachStats) {
            System.out.printf("- %s %s.%s.: %d тренировок в неделю%n",
                    stat.getCoach().getSurname(),
                    stat.getCoach().getName().charAt(0),
                    stat.getCoach().getMiddleName().charAt(0),
                    stat.getCount());
        }
        System.out.println();

        // Тест 5: Проверка дня без тренировок
        System.out.println("5. Проверка воскресенья (должно быть пусто)");
        List<TrainingSession> sundaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.SUNDAY);
        if (sundaySessions.isEmpty()) {
            System.out.println("В воскресенье нет тренировок - правильно!");
        } else {
            System.out.println("Ошибка: в воскресенье есть тренировки!");
        }
    }
}