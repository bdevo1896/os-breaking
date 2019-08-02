import java.util.Random;


public class Program5 {

	static int a;
	Random r = new Random();

	//	public synchronized void sub(int subNum){
	//		a -= subNum;
	//	}
	//	
	//	public synchronized void add(int addedNum){
	//		a += addedNum;
	//	}

	public Program5() {
		System.out.println("----------------Program 5------------------");
		a = 0;
		Program5A p = new Program5A();
		p.start();
		p.startP5B();
	}

	private class Program5A extends Thread{

		private Program5B p5b;

		public synchronized void add(int addedNum){
			a += addedNum;
		}

		public void startP5B(){
			p5b.start();
		}

		public Program5A(){

			p5b = new Program5B(this);

		}

		@Override
		public void run() {
			for(int i = 0; i < 25; i++){
				//if(i % 2 == 1){
					this.add(3);
					p5b.sub(2);
				//}else{
					//this.add(3);
				//}
				System.out.println("Run A"+i+":"+a);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private class Program5B extends Thread{
		Program5A p5a;

		public synchronized void sub(int subNum){
			a -= subNum;
		}


		public Program5B(Program5A p5a){
			this.p5a = p5a;
		}

		@Override
		public void run() {
			for(int i = 0; i < 25; i++){
				//if(i % 2 == 0){
					p5a.add(3);
				//}else{
					this.sub(2);
				//}
				System.out.println("Run B"+i+":"+a);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
