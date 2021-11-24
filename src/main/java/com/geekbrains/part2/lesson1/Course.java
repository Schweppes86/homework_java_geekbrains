package com.geekbrains.part2.lesson1;

//3. Добавить класс Course (полоса препятствий), в котором будут находиться:
//массив препятствий;
//метод, который будет просить команду пройти всю полосу.

public class Course {
    private Obstacle[] obstacles;

    public Course(Obstacle ... obstacle) {
        this.obstacles = obstacle;
    }

    public void doIt(Team team) {
        for (Member member: team.getMembers()) {
            System.out.println("======== Игрок " + member.getMemberName() + " начинает испытание ========");
            for (Obstacle obs :obstacles) {
                System.out.println("Требуется перепрыгнуть через преграду высотой " + obs.getDifficulty() + ", cила прыжка игрока " + member.getJumpForce());
                obs.goChallenge(member);
                if (!member.isCompetitionResult()){
                    break;
                }
            }
            System.out.println("======== Игрок " + member.getMemberName() + " завершает испытание ========");
        }
    }
}