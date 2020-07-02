import java.util.LinkedList;
import java.util.Queue;

public class semaphoreRead {
	static boolean semophoreValue=true;


	public semaphoreRead() {
		
	}

	public void semPrintWait(Process P)  {
		if(this.semophoreValue == true) {
			this.semophoreValue = false; 
			
		}else {
			OperatingSystem.blocked.add(P);
			P.setProcessState(P, ProcessState.Waiting);
			
			P.suspend();
			this.semophoreValue = false; 
		}
		
	}
	public void semPrintPost() throws InterruptedException {
		if(this.semophoreValue == false) {
			this.semophoreValue = true ; 
			if(!OperatingSystem.blocked.isEmpty() ) {
				
				if(Process.processFinish==true) {
					Process Pr = OperatingSystem.blocked.remove();
					OperatingSystem.readyQueue.add(Pr);
					OperatingSystem.Scheduling(OperatingSystem.readyQueue);
					}
			}
		}
		
	}
	
	

}




