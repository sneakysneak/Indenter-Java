import java.util.*;
import java.io.*;
import java.nio.file.*;

class Main
{
  /*
   * The approach used for indenting has limitations.
   * For example:
   *  This comment is multi line.
   *  A switch statement has several components.
   */

    /**
     * @param args Files to be indented
     */
    public static void main( String args[] )
    {
        for ( String fileName: args )
        {
            Program aProgram = new Program();              // New stored program, it saves the program class
            Path file = Paths.get( fileName );             // Read from file
            try
            {
                InputStream in = Files.newInputStream(file);
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                String line = null; //On Oracle's website it's written like this but there is no need for null here
                while ( (line = reader.readLine() ) != null )
                {
                    aProgram.addLine( line );                 // Add line with the addLine method, it's void, so it can be used anything in freely.
                }
            } catch (IOException e)
            {
                System.out.printf( "Error with file: %s\n%s: %s\n",
                        fileName,
                        e.getClass().getSimpleName(),
                        e.getMessage() );
            }
            String res = aProgram.indentProgram();       // Indent program
            System.out.print( res );                     // Print result
        }
    }

}