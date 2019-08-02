
public class Program2 {
	
	static int a;
	
	public void add(int addedNum){
		a += addedNum;
	}
	
	public synchronized void sub(int subNum){
		a -= subNum;
	}
	

	public Program2() {
		System.out.println("----------------Program 2------------------");
		a = 0;
		Runnable rOne = new Runnable(){

			@Override
			public void run() {
				for(int i = 0; i < 25; i++){
					add(3);
					
					System.out.println("Run A"+i+":"+a);
				}
			}

		};

		Runnable rTwo = new Runnable(){

			@Override
			public void run() {
				for(int i = 0; i < 25; i++){
					sub(2);
					System.out.println("Run B"+i+":"+a);
				}

			}

		};

		Thread thrOne = new Thread(rOne);
		Thread thrTwo = new Thread(rTwo);

		thrOne.start();
		thrTwo.start();
	}

}
