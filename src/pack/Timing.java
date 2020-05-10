package pack;

import java.util.Timer;
import java.util.TimerTask;

public class Timing {
	boolean covid_surv=true;//Indique si le joueur a survécu l’attaque de Covid
	int seconds;         //Un compteur pour le temps écoulé
	boolean gel_intime=false; //Indique si le joueur a pris le gel en temps
	Timer mytimer=new Timer();
//__________________________________________________________________________________________________________
	public void start() {    //Augmente l’attribut seconds lors de l’attaque du covid
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
		},0, 1000);
	}
//__________________________________________________________________________________________________________
	public void set_covid_surv(boolean bo) {//Changement de l’attribut covid_surv
		covid_surv=bo;
	}
//__________________________________________________________________________________________________________
	public boolean get_covid_surv() { //Retourne l’attribut covid_surv
		return covid_surv;
	}
//__________________________________________________________________________________________________________
	public int get_seconds() { //retourne l'attribue seconds
		return seconds;
	}
//__________________________________________________________________________________________________________
	public void set_seconds(int a) {  //modifie l'attribue seconds
		seconds=a;
	}
//__________________________________________________________________________________________________________
	public void set_gel_intime(boolean bo) { //Changement de l’attribut gel_intime selon le paramètre
		gel_intime=bo;
	}

}
