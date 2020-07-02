import java.util.LinkedList;
import java.util.Queue;

//import java.util.concurrent.Semaphore;


public class Process extends Thread {
	
	public int processID;
    ProcessState status=ProcessState.New;	
    static boolean processFinish = false;
    
   
    
	
	public Process(int m) {
		processID = m;
	}
	@Override
	public void run() {
		
		switch(processID)
		{
		case 1:try {
				process1();
			} catch (InterruptedException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}break;
		case 2:try {
				process2();
			} catch (InterruptedException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}break;
		case 3:try {
				process3();
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}break;
		case 4:try {
				process4();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}break;
		case 5:try {
				process5();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}break;
		}

	}
	
	private void process1() throws InterruptedException {
		//sleep(OperatingSystem.sleepTime);
		processFinish = false;
		OperatingSystem.input.semPrintWait(this);
		OperatingSystem.print.semPrintWait(this);
		OperatingSystem.read.semPrintWait(this);
		processFinish = false;
		OperatingSystem.printText("Enter File Name: ");
		OperatingSystem.printText(OperatingSystem.readFile(OperatingSystem.TakeInput()));
		
		OperatingSystem.input.semPrintPost();
		OperatingSystem.print.semPrintPost();
		processFinish = true;
		OperatingSystem.read.semPrintPost();
		setProcessState(this,ProcessState.Terminated);
		}
	
	private void process2() throws InterruptedException {
		//sleep(OperatingSystem.sleepTime);
		OperatingSystem.print.semPrintWait(this);
		OperatingSystem.input.semPrintWait(this);
		OperatingSystem.write.semPrintWait(this);
		processFinish = false;
		OperatingSystem.printText("Enter File Name: ");
		String filename= OperatingSystem.TakeInput();
		OperatingSystem.printText("Enter Data: ");
		String data= OperatingSystem.TakeInput();
		OperatingSystem.writefile(filename,data);
		
		OperatingSystem.print.semPrintPost();
		OperatingSystem.input.semPrintPost();
		processFinish = true;
		OperatingSystem.write.semPrintPost();
		setProcessState(this,ProcessState.Terminated);
		}
	private void process3() throws InterruptedException {
		//sleep(OperatingSystem.sleepTime);
		OperatingSystem.print.semPrintWait(this);
		processFinish = false;
		int x=0;
		while (x<301)
		{ 
			OperatingSystem.printText(x+"\n");
			x++;
		}
		
		processFinish = true;
		OperatingSystem.print.semPrintPost();
		setProcessState(this,ProcessState.Terminated);
		}
	
	private void process4() throws InterruptedException {
		//sleep(OperatingSystem.sleepTime);
		OperatingSystem.print.semPrintWait(this);
		processFinish = false;
		int x=500;
		while (x<1001)
		{
			OperatingSystem.printText(x+"\n");
			x++;
		}	
		
		processFinish = true;
		OperatingSystem.print.semPrintPost();
		setProcessState(this,ProcessState.Terminated);

		}
	private void process5() throws InterruptedException {
		//sleep(OperatingSystem.sleepTime);
		OperatingSystem.print.semPrintWait(this);
		OperatingSystem.input.semPrintWait(this);
		OperatingSystem.write.semPrintWait(this);
		processFinish = false;
		OperatingSystem.printText("Enter LowerBound: ");
		String lower= OperatingSystem.TakeInput();
		OperatingSystem.printText("Enter UpperBound: ");
		String upper= OperatingSystem.TakeInput();
		int lowernbr=Integer.parseInt(lower);
		int uppernbr=Integer.parseInt(upper);
	
		String data="";
		
		while (lowernbr<=uppernbr)
		{
			data+=lowernbr++ +"\n";
		}	
		OperatingSystem.writefile("P5.txt", data);
		
		OperatingSystem.print.semPrintPost();
		OperatingSystem.input.semPrintPost();
		processFinish = true;
		OperatingSystem.write.semPrintPost();
		setProcessState(this,ProcessState.Terminated); 
	}
	

	 public static void setProcessState(Process p, ProcessState s) {
		 p.status=s;
		 if (s == ProcessState.Terminated)
		 {
			 OperatingSystem.ProcessTable.remove(OperatingSystem.ProcessTable.indexOf(p));
		 }
	}
	 
	 public static ProcessState getProcessState(Process p) {
		
		 return p.status;
		 
	}
}



