import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class HuffmanNodeRaryMain
{
   static ArrayList<HuffmanNodeRary> unsorted;
   static String c = new String("");
   static double top;
   static boolean first = true;
   static int radix;
   static double acwl;
   
   public static void main(String[]args)
   {
      unsorted = new ArrayList<HuffmanNodeRary>();
      try
      {
         String s;
         Scanner inScan = new Scanner(System.in);
         System.out.print("Enter file name (must be in InputFiles folder): ");
         String input = inScan.next();
         System.out.print("Enter radix(2-9): ");
         radix = inScan.nextInt();
         ArrayList<HuffmanNodeRary> subNs = new ArrayList();
         File file = new File("InputFiles\\" + input);
         Scanner scan = new Scanner(file).useDelimiter(",|\\r");
         boolean first = true;
         while(scan.hasNextLine())
         {
            if (first)
            {
               s = scan.next();
            }
            else
            {
               s = scan.next().substring(1,2);
            }
            double p = scan.nextDouble();
            first = false;
            HuffmanNodeRary myNode = new HuffmanNodeRary(s, p, radix, subNs);
            unsorted.add(myNode);
         } 
      }
      catch (IOException e)
      {
         System.out.println("Can't find file");
      }
      for (int i = 0; i< unsorted.size()-1; i++)
      {
         for (int j = 1; j<unsorted.size(); j++)
         {
            if (unsorted.get(j-1).getProbability() > unsorted.get(j).getProbability())
            {
               HuffmanNodeRary temp = unsorted.get(j-1);
               unsorted.set(j-1, unsorted.get(j));
               unsorted.set(j, temp);
            }
         }
      }
      for (HuffmanNodeRary s: unsorted)
      {
         top += s.getProbability();
      }
      create(unsorted.get(0));
      unsorted.get(0).setCode("");
      traverse(unsorted.get(0));
      System.out.print("ACWL: " + acwl);
   }
   public static void create(HuffmanNodeRary myNode)
   {
      double totalP = 0.0;
      ArrayList<HuffmanNodeRary> subN = new ArrayList();
      int firstStep = (unsorted.size()-2) % (radix - 1) + 2;
      if (first)
      {
         for (int i = 0; i < firstStep; i++)
         {
            subN.add(i, unsorted.get(i));
            totalP += unsorted.get(i).getProbability();
         }
      }
      else
      {
         for (int i = 0; i < radix; i++)
         {
            if (i == unsorted.size())
               break;
            subN.add(i, unsorted.get(i));
            totalP += unsorted.get(i).getProbability();
         }
      }
      HuffmanNodeRary node = new HuffmanNodeRary("", totalP, radix, subN);
      int n = 0;
      if (totalP == top)
      {
         unsorted.clear();
         unsorted.add(node);
      }
      else
      {
         if (first)
         {
            for (int i = 0; i < firstStep; i++)
            {
               unsorted.remove(0);
            }
            first = false;
         }
         else
         {
            if (unsorted.size() > 1)
            {
               for (int j = 0; j < radix; j++)
                  unsorted.remove(0);
            }
         }
         while (n < unsorted.size() && unsorted.size() > 1 && unsorted.get(n).getProbability() < totalP)
            n++;
         if (unsorted.size() == 1 && unsorted.get(0).getProbability() < totalP)
            unsorted.add(node);
         else if (n == unsorted.size())
            unsorted.add(node);
         else 
            unsorted.add(n, node);
         n = 0;
         create(node); 
      }
   }
   public static void traverse(HuffmanNodeRary start)
   {
      if (!start.getSubNodeArray().isEmpty())
      {
         for (int i = 0; i < start.getSubNodeArray().size(); i++)
         {
            c = c.concat(String.valueOf(i));
            start.getSubNodeArray().get(i).setCode(c);
            traverse(start.getSubNodeArray().get(i));
            if (start.getSymbol() == "")
               c = start.getCode();
         }
      }
      if (!start.getSymbol().equals(""))
      {
         System.out.printf("S: %-3s P: %-6s C: %-5s\n",start.getSymbol(),start.getProbability(), start.getCode());
         acwl += start.getProbability()/100*start.getCodeLength();
      }
         
   }
}