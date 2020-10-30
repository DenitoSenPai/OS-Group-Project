public class ProcessControlBlock {

    enum ThreadState{
        REFILLING, DRINKING, THINKING, WAITING, TERMINATED;
    }

    enum ProcessType{
        STUDENT, BARTENDER;
    }

    int pid;
    int tid;
    ThreadState threadState; //{Refilling, Drinking, Thinking, Waiting, Terminated)}
    ProcessType processType; //{(Student or Bartender)}
    boolean lightBeerRequired; //{if student}
    int lightBeerConsumption; //{if student}
    int wakeCount; //{if bartender}
    int turnaroundTime;


    public ProcessControlBlock(){
        pid = 0;
        tid = 0;
        threadState = null;
        processType = null;
        lightBeerRequired = true;
        lightBeerConsumption = 0;
        wakeCount = 0;
        turnaroundTime = 0;
    }

    public ProcessControlBlock(int pid, int tid, ThreadState threadState, ProcessType processType, boolean lightBeerRequired, int lightBeerConsumption, int wakeCount, int turnaroundTime) {
        this.pid = pid;
        this.tid = tid;
        this.threadState = threadState;
        this.processType = processType;
        this.lightBeerRequired = lightBeerRequired;
        this.lightBeerConsumption = lightBeerConsumption;
        this.wakeCount = wakeCount;
        this.turnaroundTime = turnaroundTime;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public ThreadState getThreadState() {
        return threadState;
    }

    public void setThreadState(ThreadState threadState) {
        this.threadState = threadState;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

    public boolean isLightBeerRequired() {
        return lightBeerRequired;
    }

    public void setLightBeerRequired(boolean lightBeerRequired) {
        this.lightBeerRequired = lightBeerRequired;
    }

    public int getLightBeerConsumption() {
        return lightBeerConsumption;
    }

    public void setLightBeerConsumption(int lightBeerConsumption) {
        this.lightBeerConsumption = lightBeerConsumption;
    }

    public int getWakeCount() {
        return wakeCount;
    }

    public void setWakeCount(int wakeCount) {
        this.wakeCount = wakeCount;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    @Override
    public String toString() {
        return "ProcessControlBlock{" +
                "pid=" + pid +
                ", tid=" + tid +
                ", threadState='" + threadState + '\'' +
                ", processType='" + processType + '\'' +
                ", lightBeerRequired=" + lightBeerRequired +
                ", lightBeerConsumption=" + lightBeerConsumption +
                ", wakeCount=" + wakeCount +
                ", turnaroundTime=" + turnaroundTime +
                '}';
    }
}
