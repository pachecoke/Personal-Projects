package projects.blackjack;

public class Player {
	private int money;

	public Player(int money) {
		this.money = money;
	}
	
	public void remove(int money) {
		this.money -= money;
	}
	
	public void add(int money) {
		this.money += money;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
}
