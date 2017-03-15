package com;

public class Timer implements Runnable {
	static int CurrentTime;

	Timer() {
		CurrentTime = 0;
	}

	@Override
	public void run() {

		while (true) {
			CurrentTime++;
			//System.out.println("Current time is :"+CurrentTime);
			if(CurrentTime >=3)
			{
				CurrentTime =3;
			}
			if(CurrentTime<3)
			{
				Ball.setJump(true);
			}
			else{
				Ball.setJump(false);
			}

			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static void Starter() {
		Timer time = new Timer();
		Thread timeKeeper = new Thread(time);
		timeKeeper.start();

	}

	static void resetTimer() {
		Timer.CurrentTime = 0;
	}

	static int getCurrentTime() {
		//System.out.println("current time is :" + Timer.CurrentTime);
		return Timer.CurrentTime;
	}

	public static void main(String... args) {
		Timer.Starter();
	}
}
