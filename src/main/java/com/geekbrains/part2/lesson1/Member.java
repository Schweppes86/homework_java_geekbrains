package com.geekbrains.part2.lesson1;

public class Member {
    private String memberName;
    private int jumpForce;
    private boolean competitionResult;

    public Member(String memberName, int jumpForce) {
        this.memberName = memberName;
        this.jumpForce = jumpForce;
    }

    public void setCompetitionResult(boolean competitionResult) {
        this.competitionResult = competitionResult;
    }

    public boolean isCompetitionResult() {
        return competitionResult;
    }

    public int getJumpForce() {
        return jumpForce;
    }

    public String getMemberName() {
        return memberName;
    }

    private boolean checkPower(int courseDifficulty) {
        return jumpForce > courseDifficulty || jumpForce == courseDifficulty;
    }

    public void jump(int courseDifficulty) {
        if (checkPower(courseDifficulty)) {
            setCompetitionResult(true);
            System.out.println(memberName + " успешно прошел испытание");
        }
        else {
            System.out.println(memberName + " провалил испытание");
            setCompetitionResult(false);
        }
    }
}
