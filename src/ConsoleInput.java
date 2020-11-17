import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput
{
    /**
     * reads a line from standard input, returns string
     * @return the string read from the terminal/keyboard/stdin
     */

    public static String readString()
    {
        String line=null;
        try {
            line = ourReader.readLine();
        }
        catch (IOException ioe)	{
            System.out.println("error in readString");
        }

        return line;
    }

    /**
     * reads a line from standard input, returns first int on line
     * catches NumberFormatException, returns 0 if caught
     * @return the int read from the terminal/keyboard/stdin
     *
     */

    public static int readInt()
    {
        String line = null;
        int local = 0;
        try
        {
            line = ourReader.readLine();
        }
        catch (IOException ioe)	{ }

        try {
            local = Integer.parseInt(line);
        }
        catch (NumberFormatException nfe) {
            System.out.println("illegal numeric entry "+line);
        }
        return local;
    }

    /**
     * reads a line from standard input, returns first double on line
     * catches NumberFormatException, returns 0 if caught
     * @return the double read from the terminal/keyboard/stdin
     */

    public static double readDouble()
    {
        String line = null;
        double local = 0.0;
        try {
            line = ourReader.readLine();
        }
        catch (IOException ioe)	{ }

        try {
            local = Double.valueOf(line).doubleValue();
        }
        catch (NumberFormatException nfe) {
            System.out.println("illegal numeric entry "+line);
        }
        return local;
    }

    private static BufferedReader ourReader =
            new BufferedReader(
                    new InputStreamReader(System.in));

}