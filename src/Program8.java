import java.util.Random;


public class Program8 {

	static int a;
	Random r = new Random();

	//	public synchronized void sub(int subNum){
	//		a -= subNum;
	//	}
	//	
	//	public synchronized void add(int addedNum){
	//		a += addedNum;
	//	}

	public Program8() {
		System.out.println("----------------Program 8------------------");
		a = 0;
		Program8A p = new Program8A();
		p.start();
		p.startP5B();
	}

	private class Program8A extends Thread{

		private Program8B p5b;

		public synchronized void add(int addedNum){
			a += addedNum;
		}

		public void startP5B(){
			p5b.start();
		}

		public Program8A(){

			p5b = new Program8B(this);

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

	private class Program8B extends Thread{
		Program8A p5a;

		public synchronized void sub(int subNum){
			a -= subNum;
		}


		public Program8B(Program8A p5a){
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
