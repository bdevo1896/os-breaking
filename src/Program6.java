import java.util.Random;
import java.util.concurrent.Semaphore;


public class Program6 {
static int a;
Random r;
	
	public void add(int addedNum){
		a += addedNum;
	}
	
	public void sub(int subNum){
		a -= subNum;
	}
	

	public Program6() {
		System.out.println("----------------Program 6------------------");
		Semaphore s = new Semaphore(1);
		Semaphore s2 = new Semaphore(1);
		r = new Random();
		a = 0;
		Runnable rOne = new Runnable(){

			@Override
			public void run() {
				for(int i = 0; i < 25; i++){
					
					try {
						s.acquire();
						s2.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(s.availablePermits()==0){
					add(3);
					System.out.println("Run A"+i+":"+a);
					}
					s2.release();
					s.release();
					
					try {
						Thread.sleep(r.nextInt(5)*100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		};

		Runnable rTwo = new Runnable(){

			@Override
			public void run() {
				for(int i = 0; i < 25; i++){
					try {
						Thread.sleep(r.nextInt(5)*100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					try {
						s2.acquire();
						s.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(s.availablePermits() == 0){
					sub(2);
					System.out.println("Run B"+i+":"+a);
					}
					s2.release();
					s.release();
					
				}

			}

		};

		Thread thrOne = new Thread(rOne);
		Thread thrTwo = new Thread(rTwo);

		thrOne.start();
		thrTwo.start();
	}
}
