package com.example.noa.timer;

/**
 * Created by noa on 23.07.15.
 */
public class CountDownRunner implements Runnable{
    // @Override
    public void run() {
        MainActivity main = new MainActivity();
        while(!Thread.currentThread().isInterrupted()){
            try {
                main.doWork();
                Thread.sleep(1000); // Pause of 1 Second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }catch(Exception e){
            }
        }
    }
}