public class Main {
    public static void main(String[] args) {
        CuentaLocks cuentaLocks = new CuentaLocks(1000);
        CuentaSTM cuentaSTM = new CuentaSTM(1000);

     /*   for (int i = 0; i < 1000; i++) {
            new HilosCuentaLocks("hilo" + i + "CuentaLocks", cuentaLocks, 1).start();
            new HilosCuentaLocks("hilo" + i + "CuentaLocks", cuentaLocks, -1).start();
            new HilosCuentaLocks("hilo" + i + "CuentaLocks", cuentaLocks, -1).start();
        }*/

        for (int i = 0; i < 1000; i++) {
            cuentaSTM.ajustarBalance(1);
            cuentaSTM.ajustarBalance(-1);
            cuentaSTM.ajustarBalance(-1);
            System.out.println(cuentaSTM.toString());
        }

    }
}

class HilosCuentaLocks extends Thread {
    CuentaLocks cuentaLocks;
    int cantidad;

    public HilosCuentaLocks(String str, CuentaLocks cuentaLocks, int cantidad) {
        super(str);
        this.cuentaLocks = cuentaLocks;
        this.cantidad = cantidad;
    }

    public void run() {
        synchronized (cuentaLocks) {
            cuentaLocks.ajustarBalance(cantidad);
            System.out.println(cuentaLocks.toString() + " " + getName());
        }
        System.out.println("Test Finished for: " + getName());
    }
}