package ru.yakimovvn;

public class Team {

    enum  Players{
        VLADIMIR("Владимир",3.2), IRINA("Ирина",1.9),
        SERGEI("Сергей", 2.5), ANNA("Анна",1.4);
        private String name;
        private double maxJump;

        Players(String name, double maxJump){
            this.name = name;
            this.maxJump = maxJump;
        }

        public String getName() {
            return name;
        }

        public double getMaxJump() {
            return maxJump;
        }
    }

    private String teamName;
    private Players [] players;

    public Team(String teamName){
        this.teamName = teamName;
        players = Players.values();
    }

    public void teamInfo(){
        System.out.println("Команда "+ teamName+"\nУчастники:");
        for(Players thePlayer: players){
            System.out.println(thePlayer.getName()+"\tПреодалевает препятствие "+ thePlayer.getMaxJump());
        }
        System.out.println();
    }

    public void pastDistancePlayers(Players player){
        System.out.println(player.getName()+ " прошёл дистанцию.");
    }

    public Players[] getPlayers() {
        return players;
    }
}
