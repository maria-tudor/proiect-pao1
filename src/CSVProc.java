import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class CSVProc
{
    private CSVProc () {}

    private static CSVProc newCSVProc = null;

    public static CSVProc getNewCSVProc ()
    {
        if (newCSVProc == null)
        {
            newCSVProc = new CSVProc();
        }

        return newCSVProc;
    }


    public void saveCSV (ArrayList <ArrayList<String>> lns, String flnm)
    {
        FileWriter wrt;

        try
        {
            if (flnm.lastIndexOf ("/") != -1) {
                String dirPath = flnm.substring(0, flnm.lastIndexOf("/"));
                File file      = new File (dirPath);

                if (!file.mkdir())
                    System.err.println ("Couldn't create new directory, maybe it already exists");
            }

            wrt = new FileWriter(flnm, false);
            System.out.println ("Saving to '" + flnm + "'...");

            for (ArrayList <String> l : lns)
            {
                for (int i = 0; i < l.size (); i++)
                {
                    wrt.write (l.get (i));
                    if (i != l.size () - 1)
                        wrt.write(",");
                }
                wrt.write ("\n");
            }

            wrt.close();
        } catch (IOException e) {
            System.out.println ("Nu s-apututscrie in fisier.");
            e.printStackTrace ();
        }
    }

    public ArrayList <ArrayList<String>> loadCSV (String flnm)
    {
        ArrayList <ArrayList<String>> valsOut = new ArrayList <> ();
        File    fl;
        Scanner scn;

        try
        {
            fl = new File(flnm);
            System.out.println ("Se citeste din '" + flnm );
            scn = new Scanner(fl);

            while (scn.hasNextLine ())
            {
                String ln             = scn.nextLine ();
                String[] vals         = ln.split (",");
                ArrayList<String> newln  = new ArrayList <> (Arrays.asList (vals));
                valsOut.add (newln);
            }

            scn.close ();
        } catch (FileNotFoundException e) {
            System.out.println ("Nu s-a putut citi din fisier");

        }

        return (valsOut);
    }
}
