public class Cuenta {
    private int balance;

    public Cuenta(int balance) {
        setBalance(balance);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int newBalance) {
        if (balance < 0) {
            throw new IllegalStateException("balance negativo no permitido");
        }
        balance += newBalance;
    }

    @Override
    public String toString() {
        return "Nuevo balance de cuenta = " + balance;
    }
}
