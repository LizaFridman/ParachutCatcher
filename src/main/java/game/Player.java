package main.java.game;

public class Player {
    private int totalScore;
    private int lives;
    private String name;

    public Player(int initialScore, int initialLives, String username){
        totalScore = initialScore;
        lives = initialLives;
        name = username;
    }

    public void addToScore(int points){
        totalScore+=points;
    }

    public int getLives(){
        return lives;
    }

    public int getScore(){
        return totalScore;
    }

    public int loseLife() {
        lives--;
        return getLives();
    }
}
