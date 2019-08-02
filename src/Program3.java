import java.util.concurrent.Semaphore;


public class Program3 {

static int a;
	
	public void add(int addedNum){
		a += addedNum;
	}
	
	public void sub(int subNum){
		a -= subNum;
	}
	

	public Program3() {
		System.out.println("----------------Program 3------------------");
		Semaphore s = new Semaphore(1);
		a = 0;
		Runnable rOne = new Runnable(){

			@Override
			public void run() {
				for(int i = 0; i < 25; i++){
					try {
						s.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(s.availablePermits()==0){
					add(3);
					System.out.println("Run A"+i+":"+a);
					}
					s.release();
				}
			}

		};

		Runnable rTwo = new Runnable(){

			@Override
			public void run() {
				for(int i = 0; i < 25; i++){
					try {
						s.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(s.availablePermits() == 0){
					sub(2);
					System.out.println("Run B"+i+":"+a);
					}
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
