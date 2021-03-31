import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;
import java.io.*;
import parcs.*;

public class Miller implements AM {

public static void main(String[] args) {
        task curtask = new task();
        curtask.addJarFile("Miller.jar");
        (new Miller()).run(new AMInfo(curtask, (channel)null));
        curtask.end();
    }

    public void run(AMInfo info) {
        int my_result = 0;
	BigInteger n;
	int it;
	try{
			//Scanner sc = new Scanner(new File(info.curtask.findFile("Solovey.data")));
			System.out.print("Enter n for factorization: ");
			Scanner sc = new Scanner(System.in);
			n = sc.nextBigInteger();
			System.out.println("N is: " + n);
			System.out.print("Enter it for iteration: ");
			Scanner sc = new Scanner(System.in);
			it = sc.nextInt();
	}
		catch (IOException e) {e.printStackTrace(); return;}
	long startTime = System.nanoTime();
	System.out.println("Iterations: " + it);
	point p = info.createPoint();
        channel c = p.createChannel();
        p.execute("Algo");
        c.write(n);
        c.write(it);
	System.out.println("Waiting for result...");
	my_result = c.readInt();
	System.out.println("Result found.");
	if (my_result == 1)
	{
	System.out.println("prime");
	}
	else
	{	System.out.println("non-prime");}
	double estimatedTime = (double) (System.nanoTime() - startTime) / 1000000000;
	System.out.println("\nTime total: " + estimatedTime);
	try {
          PrintWriter out = new PrintWriter(new FileWriter(info.curtask.addPath("Solovey.res")));
          out.println(my_result);
          out.close();
      } catch (IOException e) {e.printStackTrace(); return;}
    }
}
