public class Time extends Thread{

    private int seconds = 0;

   @Override
    public void run(){
        try{
            while(true){
                Thread.sleep(1000);

                seconds++;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


   }

    public int getSeconds() {
        return seconds;
    }
}
