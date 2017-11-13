import java.util.ArrayList;

public class HuffmanNodeRary
{
   //SymbolNode sNode;
   String   symbol;
   double   probability;
   int      radix;
   String   code;
   int      codeLength;
   ArrayList<HuffmanNodeRary> subnodes = new ArrayList<HuffmanNodeRary>();

   // HuffmanNode(SymbolNode sN, HuffmanNode lt,  HuffmanNode rt)
   public HuffmanNodeRary(String s, double p, int r, ArrayList<HuffmanNodeRary> subns)
   {
      //sNode = sN;
      symbol = s;
      probability = p;
      radix = r;
      subnodes = subns;
   }

   HuffmanNodeRary()
   {
   }
         
   void setCode(String c)
   {
      code = c;
      codeLength = c.length();
   }
   
   void setProbability(double p)
   {
      probability = p;
   }
   
   String getSymbol()
   {
      return symbol;
   } 
    
   double getProbability()
   {
      return probability;
   }  
   
   String getCode()
   {
      return code;
   }  
   
   int getCodeLength()
   {
      return codeLength;
   }
   
   ArrayList<HuffmanNodeRary> getSubNodeArray()
   {
      return subnodes;
   }  
   public String toString()
   {
      return "S:" + symbol + " P:" + probability + " C: " + code + "\n";
   }
 
}
