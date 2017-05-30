import java.util.concurrent.TimeUnit;

public class CuentaLocks {
    private long fecha;
    private int balance;

    public CuentaLocks(int balance) {
        this.balance = balance;
        this.fecha = System.currentTimeMillis();
    }

    public synchronized void ajustarBalance(int cantidad) {
       /* try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        int tempBalance = balance;
        long tempfecha = fecha;
        balance += cantidad;
        fecha = System.currentTimeMillis();
        if (balance < 0) {
            balance = tempBalance;
            fecha = tempfecha;
            System.out.println(toString());
            throw new IllegalStateException("balance negativo no permitido; volviendo al estado anterior");
        }
    }

    public synchronized int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("$%d (fecha %tF %<tT)", balance, fecha);
    }

}
