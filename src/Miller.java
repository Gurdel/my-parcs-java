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
			Scanner sc = new Scanner(new File(info.curtask.findFile("Solovey.data")));
			n = sc.nextBigInteger();
			it = sc.nextInt();
	}
		catch (IOException e) {e.printStackTrace(); return;}
        System.out.println("Number: " + n);
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
		
	try {
          PrintWriter out = new PrintWriter(new FileWriter(info.curtask.addPath("Solovey.res")));
          out.println(my_result);
          out.close();
      } catch (IOException e) {e.printStackTrace(); return;}
    }
}
