import java.util.concurrent.Semaphore;

/**
 * This program deadlocks because of the order I acquired and released the semaphores which was a mixed
 * up order. EX: THR1: s.a then s2.a THR2: s2.a then s.a
 * @author User
 *
 */
public class Program4 {

static int a;
	
	public void add(int addedNum){
		a += addedNum;
	}
	
	public void sub(int subNum){
		a -= subNum;
	}
	

	public Program4() {
		System.out.println("----------------Program 4------------------");
		Semaphore s = new Semaphore(1);
		Semaphore s2 = new Semaphore(1);
		a = 0;
		Runnable rOne = new Runnable(){

			@Override
			public void run() {
				for(int i = 0; i < 25; i++){
					try {
						s.acquire();
						s2.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(s.availablePermits()==0){
					add(3);
					System.out.println("Run A"+i+":"+a);
					}
					s.release();
					s2.release();
				}
			}

		};

		Runnable rTwo = new Runnable(){

			@Override
			public void run() {
				for(int i = 0; i < 25; i++){
					try {
						s2.acquire();
						s.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
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
