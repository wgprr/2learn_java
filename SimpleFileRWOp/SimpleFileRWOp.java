import java.util.*;
import java.nio.file.*;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleFileRWOp {

	static StringBuilder sb = new StringBuilder();
	
	public static void fileRead(String file)throws IOException {
		Scanner in = new Scanner(Paths.get(file));
		
		System.out.printf("[%s]\t%tc\n", file,new Date());
		while (in.hasNextLine()) {
			System.out.println(in.nextLine());		
		}
		
		in.close();
		
	}
	
	public static void fileWrite(String file,String str)throws IOException {
		PrintWriter out = new PrintWriter(file);
		
		if (str == null) {
			out.println(sb.toString());
			out.close();
			return ;
		}
		
                if (sb.length() != 0) {
		        sb.append('\n');
                }
		sb.append(str);	
	}
	
	public static void main(String[] argv)throws IOException {
		Scanner sc = new Scanner(System.in);
		String file = "test.txt";
		
                System.out.println("Input something and use CTRL+D to end it ,then we will write it to test.txt");
		while (sc.hasNextLine()) {
			fileWrite(file,sc.nextLine());
		}
		fileWrite(file,null);

                System.out.println("\nREAD FILE:");
		fileRead(file);
	}

}
