package unsync;

public class Barrel {
	 private static int total;
	    private final int MAX = 50;
	    private int count = 0;

	    public Barrel() {
	        total = 0;
	    }

	    public static int getAmnt() {
	        return total;
	    }

}
