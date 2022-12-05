package Financial;

public class StartingMoney extends Money{
    
    public double getMoney() {
        return money;
    }

    public void giveMeMoney(double monies){
        money = monies;
    }
}
