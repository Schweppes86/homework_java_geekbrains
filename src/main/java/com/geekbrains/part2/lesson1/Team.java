package com.geekbrains.part2.lesson1;

//2. Добавить класс Team, который будет содержать:
//название команды;
//массив из четырех участников — в конструкторе можно сразу всех участников указывать);
//метод для вывода информации о членах команды, прошедших дистанцию;
//метод вывода информации обо всех членах команды.


public class Team {
    private static String teamName;
    private Member[] members;

    public Team (String teamName, Member... members) {
        this.members = members;
        this.teamName = teamName;
    }

    public void showResults() {
        System.out.println("      Результат прохождения полосы препядствий");
        System.out.println("Полосу препятствий преодолевала команда: " + getTeamName());
        for (Member member:members) {
            if (member.isCompetitionResult()){
                System.out.println(member.getMemberName() + " прошел полосу препятствий");
            }
            else {
                System.out.println(member.getMemberName() + " не прошел полосу препятствий");
            }
        }
    }

    public Member[] getMembers() {
        return members;
    }

    public static String getTeamName() {
        return teamName;
    }
}