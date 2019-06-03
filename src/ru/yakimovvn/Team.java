package ru.yakimovvn;

class Team {

    enum  Players{
        VLADIMIR("Владимир",3.2), IRINA("Ирина",1.9),
        SERGEI("Сергей", 2.5), ANNA("Анна",1.4);
        private String name;
        private double maxJump;
        private boolean isPastDistance;

        Players(String name, double maxJump){
            this.name = name;
            this.maxJump = maxJump;
            isPastDistance = true;
        }

        public String getName() {
            return name;
        }

        public double getMaxJump() {
            return maxJump;
        }

        public boolean isPastDistance() {
            return isPastDistance;
        }

        public void setPastDistance(boolean pastDistance) {
            isPastDistance = pastDistance;
        }
    }

    private String teamName;
    private Players [] players;

    Team(String teamName){
        this.teamName = teamName;
        players = Players.values();
    }

    void teamInfo(){
        System.out.println("Команда "+ teamName+"\nУчастники:");
        for(Players thePlayer: players){
            System.out.println(thePlayer.getName()+"\tПреодалевает препятствие "+ thePlayer.getMaxJump());
        }
        System.out.println();
    }

    void showResults(){
        for (Players teamPlayer: players) {
            if (teamPlayer.isPastDistance()) {
                System.out.println(teamPlayer.getName() + " прошёл(ла) дистанцию.");
            }else{
                System.out.println(teamPlayer.getName() + " к сожалению не прошёл(ла) дистанцию.");
            }
        }
    }

    Players[] getPlayers() {
        return players;
    }
}
