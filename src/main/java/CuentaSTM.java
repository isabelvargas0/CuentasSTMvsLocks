import org.multiverse.api.StmUtils;
import org.multiverse.api.Txn;
import org.multiverse.api.callables.TxnCallable;
import org.multiverse.api.references.TxnInteger;
import org.multiverse.api.references.TxnLong;

public class CuentaSTM {

    private final TxnLong fecha;
    private final TxnInteger balance;

    public CuentaSTM(final int balance) {
        fecha = StmUtils.newTxnLong(System.currentTimeMillis());
        this.balance = StmUtils.newTxnInteger(balance);
    }

    public void ajustarBalance(final int amount) {
        ajustarBalance(amount, System.currentTimeMillis());
    }

    public void ajustarBalance(final int amount, final long date) {
        StmUtils.atomic(new Runnable() {
            @Override
            public void run() {
                balance.increment(amount);
                fecha.set(date);
                if (balance.get() < 0) {
                    throw new IllegalArgumentException("balance negativo no permitido");
                }
            }
        });
    }

    @Override
    public String toString() {
        return StmUtils.atomic(new TxnCallable<String>() {
            @Override
            public String call(final Txn txn) throws Exception {
                return String.format("%d (as of %tF %<tT)", balance.get(txn), fecha.get(txn));
            }
        });
    }
}
