public class Main {

    public static void main(String[] args) {
        Shared shared = new Shared();
        new Thread(new Chrono1(shared)).start();
        new Thread(new Chrono5(shared)).start();
        new Thread(new Chrono7(shared)).start();


    }

    static class Shared{
        public Shared() {
        }
    }

    static class Chrono1 implements Runnable {
        final Shared shared;
        public Chrono1(Shared shared) {
            this.shared = shared;
        }
        @Override
        public void run() {
            while(true){
                synchronized (shared){
                    System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis());
                    shared.notifyAll();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                }
            }
        }
    }

    static class Chrono5 implements Runnable {
        final Shared shared;
        public Chrono5(Shared shared) {
            this.shared = shared;
        }
        @Override
        public void run() {
            while(true){
                synchronized (shared){
                    try {
                        shared.wait();
                        System.out.println(Thread.currentThread().getName() + ": " + "5 seconds " + System.currentTimeMillis());
                        shared.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                }
            }
        }
    }

    static class Chrono7 implements Runnable {
        Shared shared;
        public Chrono7(Shared shared) {
            this.shared = shared;
        }
        @Override
        public void run() {
            while(true){
                synchronized (shared){
                    try {
                        shared.wait();
                        System.out.println(Thread.currentThread().getName() + ": " + "7 seconds " + System.currentTimeMillis());
                        shared.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                }
            }
        }
    }
}
