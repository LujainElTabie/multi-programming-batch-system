import java.util.Queue;
import java.util.LinkedList;
import java.io.File;
import java.util.*;

public class semaphore {
static boolean semophoreValue=true;
Queue<Process> blocked = new LinkedList<>();

public semaphore() {
	
}

public void semPrintWait(Process P)  {
	System.out.println("semPrintWait   for :"+P.getName()+"  it value  "+this.semophoreValue);
	if(this.semophoreValue == true) {
		
		System.out.println("semPrintWait    in if "+P.getName());
		this.semophoreValue = false; 
	}else {
		System.out.println("semPrintWait    in else "+P.getName());
		P.suspend();
		System.out.println("semPrintWait    after suspend "+P.getName());
		P.setProcessState(P, ProcessState.Waiting);
		this.blocked.add(P);
		
	}
	
}
public void semPrintPost() {
	System.out.println("semPrintPost   :");
	if(this.semophoreValue == false) {
		this.semophoreValue = true ; 
		if(!this.blocked.isEmpty() ) {
			Process Pr = this.blocked.remove();
			System.out.println("semPrintPost  in if :"+Pr.getName());
			Pr.resume();
		}
	}
	
}

}
