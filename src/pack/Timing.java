package pack;

import java.util.Timer;
import java.util.TimerTask;

public class Timing {
	boolean covid_surv=true;
	int seconds;
	boolean gel_intime=false;
	Timer mytimer=new Timer();
	public void start() {
		mytimer.scheduleAtFixedRate(new TimerTask() {
			public void run() {

				if (seconds==10) {
				set_covid_surv(false);
				System.out.println("\u001b[1;31mkilled by covid!\u001b[0m");
				System.exit(0);
				} else if (gel_intime) {
					cancel();
					seconds=0;
					gel_intime=false;
				}
				else {
					seconds++;
					System.out.println("                                                        seconds Passed :"+ seconds);
				//cancel();
				}
			}
		},20, 1000);
	}
	public void set_covid_surv(boolean bo) {
		covid_surv=bo;
	}
	public boolean get_covid_surv() {
		return covid_surv;
	}
	public int get_seconds() {
		return seconds;
	}
	public void set_seconds(int a) {
		seconds=a;
	}
	public void set_gel_intime(boolean bo) {
		gel_intime=bo;
	}

}
