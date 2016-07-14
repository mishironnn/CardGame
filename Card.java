package CardGame;


public class Card {
    public String name;
    public int attack;
    public int defence;
    public int cost;
    public Card(String name, int attack, int defence, int cost) {
	this.name = name;
	this.attack = attack;
	this.defence = defence;
	this.cost = cost;
    }
}