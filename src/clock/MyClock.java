/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;


import javax.swing.JLabel;
import java.time.Clock;
import java.time.ZoneId;
import java.util.Calendar;

/**
 *
 * @author Milan
 */
public class MyClock extends Thread{
    private JLabel labela;
    
    public MyClock(JLabel labela) {
        this.labela = labela;
    }
    
    
    @Override
    public void run() {
        Clock clock = Clock.system(ZoneId.of("Europe/Paris"));
        
        putTime();
        Long seconds = clock.instant().getEpochSecond();
        while(true){
            if(clock.instant().getEpochSecond()>=seconds+1){
                seconds++;
                
                putTime();
            }

        }
        
        
    }
    
    public void putTime(){
            String secondString = ":";
            String minuteString = ":";
            String hourString = "";
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            
            if(second/10==0) secondString =":0";
            if(minute/10==0) minuteString =":0";
            if(hour/10==0) hourString ="0";

            labela.setText(hourString+hour+minuteString+minute+secondString+second);
        }
    
}
