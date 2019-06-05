package ru.yakimovvn.Tenderers;

import java.util.Arrays;

public class Team {



    private String teamName;
    private Competitor [] players;

    public Team(String teamName, Competitor ... players){
        this.teamName = teamName;
        this.players = players;
        teamInfo();
    }

    private void teamInfo(){
        System.out.println("Команда "+ teamName+"\nУчастники:");
        for(Competitor thePlayer: players){
            System.out.println(thePlayer);
        }
        System.out.println();
    }

    public void showResults(){
        for (Competitor teamPlayer: players) {
            teamPlayer.info();
        }
    }

    public Competitor[] getPlayers() {
        return Arrays.copyOf(players,players.length);
    }
}
