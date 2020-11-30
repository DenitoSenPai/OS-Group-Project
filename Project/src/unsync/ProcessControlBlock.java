package unsync;

// Holds And Manipulates All The Values Necessary For The Program. 

public class ProcessControlBlock {
	
	enum ThreadState {
		Refilling, Drinking, Waiting, Terminated, Thinking; 
	}

	enum ProcessState{
		Student, Bartender; 
	}

	protected static int ProcessID; 
	protected static int ThreadID; 
	protected static ThreadState ThreadState; 
	protected static ProcessState ProcessState; 
	protected static boolean LightBeerRequired; 
	protected static int LightBeerConsumption; 
	protected static int WakeCount; 
	protected static int TurnAroundTime; 
	
	public ProcessControlBlock ()
	{
		ProcessControlBlock.ProcessID = 0; 
		ProcessControlBlock.ThreadID = 0; 
		ProcessControlBlock.ThreadState = null; 
		ProcessControlBlock.ProcessState = null; 
		ProcessControlBlock.LightBeerRequired = true; 
		ProcessControlBlock.LightBeerConsumption = 0;
		ProcessControlBlock.WakeCount = 0; 
		ProcessControlBlock.TurnAroundTime = 0; 
	}
	public ProcessControlBlock (int ProcessID, int ThreadID, ThreadState ThreadState, ProcessState ProcessState, boolean LightBeerRequired, int LightBeerConsumption, int WakeCount, int TurnAroundTime)
	{
		ProcessControlBlock.ProcessID = ProcessID; 
		ProcessControlBlock.ThreadID = ThreadID; 
		ProcessControlBlock.ThreadState = ThreadState; 
		ProcessControlBlock.ProcessState = ProcessState; 
		ProcessControlBlock.LightBeerRequired = LightBeerRequired; 
		ProcessControlBlock.LightBeerConsumption = LightBeerConsumption;
		ProcessControlBlock.WakeCount = WakeCount; 
		ProcessControlBlock.TurnAroundTime = TurnAroundTime; 
	} 
	
	public int getProcessID() {
		return ProcessID;
	}

	public void setProcessID(int processID) {
		ProcessControlBlock.ProcessID = processID;
	}

	public int getThreadID() {
		return ThreadID;
	}

	public void setThreadID(int threadID) {
		ProcessControlBlock.ThreadID = threadID;
	}

	public ThreadState getThreadState() {
		return ThreadState;
	}

	public void setThreadState(ThreadState threadState) {
		ProcessControlBlock.ThreadState = threadState;
	}

	public ProcessState getProcessState() {
		return ProcessState;
	}

	public void setProcessState(ProcessState processState) {
		ProcessControlBlock.ProcessState = processState;
	}

	public boolean isLightBeerRequired() {
		return LightBeerRequired;
	}

	public void setLightBeerRequired(boolean lightBeerRequired) {
		ProcessControlBlock.LightBeerRequired = lightBeerRequired;
	}

	public int getLightBeerConsumption() {
		return LightBeerConsumption;
	}

	public void setLightBeerConsumption(int lightBeerConsumption) {
		ProcessControlBlock.LightBeerConsumption = lightBeerConsumption;
	}

	public int getWakeCount() {
		return WakeCount;
	}

	public void setWakeCount(int wakeCount) {
		ProcessControlBlock.WakeCount = wakeCount;
	}

	public int getTurnAroundTime() {
		return TurnAroundTime;
	}

	public void setTurnAroundTime(int turnAroundTime) {
		ProcessControlBlock.TurnAroundTime = turnAroundTime;
	}
	
	@Override
	public String toString() {
		return "ProcessControlBlock [ProcessID=" + ProcessID + ", ThreadID=" + ThreadID + ", ThreadState=" + ThreadState
				+ ", LightBeerRequired=" + LightBeerRequired + ", LightBeerConsumption=" + LightBeerConsumption
				+ ", WakeCount=" + WakeCount + ", TurnAroundTime=" + TurnAroundTime + ", getProcessID()="
				+ getProcessID() + ", getThreadID()=" + getThreadID() + ", getThreadState()=" + getThreadState()
				+ ", isLightBeerRequired()=" + isLightBeerRequired() + ", getLightBeerConsumption()="
				+ getLightBeerConsumption() + ", getWakeCount()=" + getWakeCount() + ", getTurnAroundTime()="
				+ getTurnAroundTime() + "]";
	}

	
}



