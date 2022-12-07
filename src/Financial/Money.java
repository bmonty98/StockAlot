package Financial;
abstract public class Money {
    
    public double money;

    public double getMoney() {
        return this.money;
    }
    public void giveMeMoney(double monies) {
        this.money += monies;
        return;
    }

}