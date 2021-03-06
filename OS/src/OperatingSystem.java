import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class OperatingSystem {
	public static Queue<Process> readyQueue = new LinkedList<>();
	public static Queue<Process> blocked = new LinkedList<>();
	public static ArrayList<Thread> ProcessTable;
	public static semaphorePrint print = new semaphorePrint();
	public static semaphoreInput input = new semaphoreInput();
	public static semaphoreRead read = new semaphoreRead();
	public static semaphoreWrite write = new semaphoreWrite();
	static int  sleepTime =500;

//	public static int activeProcess= 0;
	//system calls:
	// 1- Read from File
	@SuppressWarnings("unused")
	public static String readFile(String name) {
		String Data="";
		File file = new File(name);
	 try {
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine())
		{
			Data+= scan.nextLine()+"\n";
		}
		scan.close();
	} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
	}
		return Data;
	}
	
	// 2- Write into file
	@SuppressWarnings("unused")
	public static void writefile(String name, String data) {
		try
		{
			BufferedWriter BW = new BufferedWriter(new FileWriter(name));
			BW.write(data);
			BW.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}

	}
	//3- print to console
	@SuppressWarnings("unused")
	public static void printText(String text) {

		System.out.println(text);
		
	}
	
	//4- take input
	
	@SuppressWarnings("unused")
	public static String TakeInput() {
		Scanner in= new Scanner(System.in);
		String data = in.nextLine();
		return data;
		
	}
	
	private static void createProcess(int processID){
		Process p = new Process(processID);
		ProcessTable.add(p);
		Process.setProcessState(p,ProcessState.Ready);
		readyQueue.add(p);
		
		
	}
	public static void Scheduling(Queue<Process> readyQueue) throws InterruptedException {
		while(!readyQueue.isEmpty()) {
			Process pReady = readyQueue.remove();
			if(pReady.status == ProcessState.Ready) {
				pReady.sleep(50);
				pReady.status = ProcessState.Running;
				pReady.start();
		}else if(pReady.status == ProcessState.Waiting) {
			pReady.sleep(50);
			pReady.status = ProcessState.Running;
			pReady.resume();	
		}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
   		ProcessTable = new ArrayList<Thread>();

		//createProcess(1);
		//createProcess(2);
		createProcess(3);
		createProcess(4);
		//createProcess(5);
		
		
		Scheduling(readyQueue);
		
	
	}
}



