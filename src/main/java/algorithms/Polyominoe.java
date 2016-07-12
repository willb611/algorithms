import java.util.*;
import java.awt.Point;
public class FillPlease
{
  public static void main(String[] args) throws Exception
  {
    FillPlease fp=new FillPlease();
  }

      Scanner scan = new Scanner(System.in);// DEBUG
  public FillPlease() throws Exception
  {
    // read in the grid
    //s=scan.nextInt();
    s=5;
    // clear the way
    //scan.nextLine();
    int grid[][]=new int[s][s];
    for(int i=0;i<s;i++)
      Arrays.fill(grid[i],-1);
    /*for(int i=0;i<s;i++)
    {
      String line=scan.nextLine();
      for(int j=0;j<s;j++)
        if(line.charAt(j)!='.')
          grid[i][j]=Integer.parseInt(Character.toString(line.charAt(j)));
    }*/

    //start for hacky quick testing
    grid[0][0]=3;
grid[0][1]=-1;
grid[0][2]=-1;
grid[0][3]=6;
grid[0][4]=6;
grid[1][0]=5;
grid[1][1]=-1;
grid[1][2]=4;
grid[1][3]=-1;
grid[1][4]=6;
grid[2][0]=-1;
grid[2][1]=5;
grid[2][2]=4;
grid[2][3]=-1;
grid[2][4]=6;
grid[3][0]=-1;
grid[3][1]=1;
grid[3][2]=-1;
grid[3][3]=6;
grid[3][4]=-1;
grid[4][0]=-1;
grid[4][1]=-1;
grid[4][2]=3;
grid[4][3]=1;
grid[4][4]=2;
    for(int i=0;i<s;i++)
      for(int j=0;j<s;j++)
        // ;("grid["+i+"]["+j+"]="+grid[i][j]+";");
      // for hacky quick testing
    printGrid(grid);
    System.out.println("Grid is : " + s + " by " + s);
    if(complete(grid))
    {
      for (int i=0;i<s;i++)
        for(int j=0;j<s;j++)
          System.out.print(grid[i][j]);
        System.out.println();
    }else System.out.println(0);
  } // constructor

  /**
  * If there is any way of completing this pocket, this method finds it.
  * Return true if it completed the pocket, with grid changed.
  * @param pocket The pocket to fill
  * @param grid The current grid representing the Fillomino as it stands.
  */
  private boolean completeThisPocketIDontCareHow(Collection<Point> pocket, int[][] grid)
  {
    if(pocket.size()==0)return true;
    int j=0;
    // Loop through all points in the pocket.
    for(Iterator<Point> k=pocket.iterator();k.hasNext();k.next(),j++) //ugly hack to get around list/set mismatch, think it works.
    {
      // Try to complete a pocket, try all values in turn.
      for(int sol=9;sol>=0;sol--)
      {
        // Copy the whole grid.
        int[][] tempGrid=new int[s][s];
        for (int i=0;i<s;i++)
          tempGrid[i] = Arrays.copyOf(grid[i], s);
        // Copy the pocket.
        List<Point> tempPocket=new ArrayList<Point>();
        for(Point p:pocket)
          if(!tempPocket.contains(p))
            tempPocket.add(new Point(p));
          // Try to fill the pocket starting at pocket.get(j), with value sol
          tempGrid[tempPocket.get(j).x][tempPocket.get(j).y]=sol;
          if(completeAPolyominoWithBlackMagic(tempGrid,tempPocket.get(j)))
          {
            if(completeThisPocketIDontCareHow(tempPocket,tempGrid))
            {
              // point the old grid to the value of tempGrid
              for (int i = 0; i<s;i++)
                grid[i] = Arrays.copyOf(tempGrid[i], s);
              pocket.clear();
              pocket.addAll(tempPocket);
              return true;
            }
          } // good, but we need to fill whole pocket.
          // completing that polynomial failed,
        //}
      }
    } // for iterate through pocket
    return false;
  } // method completeThisPocketIDontCareHow


  /** The row/column size of the grid. */
  private int s;
  /** start by going through grid, find all polyominoes.
  * if it'snot complete, add an array[0-maxNumbers (iefor 4: 3)][2(coords)]
  * to a global list of incomplete polyominoes.
  */
  private boolean complete(int[][] grid)
  {
    // Copy the whole grid.
    int[][] tempGrid=new int[s][s];
    for (int i = 0; i<s;i++)
      tempGrid[i] = Arrays.copyOf(grid[i], s);
    gabenGrid=tempGrid;
    List<Point> incomplete=new ArrayList<Point>();
    // Check if grid is complete, or invalid.
    if(isValid(tempGrid)&&isComplete(tempGrid))
    {
      System.out.println("Success");
      printGrid(tempGrid);
      scan.nextLine();
      die("found solution",9);
    }
    else if(!isValid(tempGrid))
    {
      die("INVALID FROM MAIN, ", 12);
      return false;

    }
    // Look for any polyonimoes that are incomplete
    try{Thread.sleep(100);}catch(InterruptedException e){} // DEBUG
    for(int row=0;row<s;row++)
      for(int col=0;col<s;col++)
      {
        if (tempGrid[row][col]!=-1)
          if(findAdj(new Point(row,col),tempGrid,null,tempGrid[row][col]).size()!=tempGrid[row][col])
          { 
            printSet(findAdj(new Point(row,col),tempGrid,null,tempGrid[row][col]));
            if(completeAPolyominoWithBlackMagic(tempGrid,new Point(row,col))) // try to complete a polyomino
            {
              System.out.println("Completed a polymino at "+row+","+col);printGrid(tempGrid);
              if(complete(tempGrid))
              {
                // point the old grid to the value of tempGrid
                for (int i = 0; i<s;i++)
                  grid[i] = Arrays.copyOf(tempGrid[i], s);
                return true;
              } else return false; // can't complete the thing, return false.
            } else return false; // can't complete the polyomino, return false.
          } // if
      } // for
    // By here no incomplete polyominos, find some empty space and try to fill it.
    for (int row=0;row<s;row++)
      for (int col=0;col<s;col++)
        if (tempGrid[row][col]==-1)
        {
          Set<Point> pocket = findAdj(new Point(row,col), tempGrid, null,-1);
          if(completeThisPocketIDontCareHow(pocket,tempGrid))
          {
            if(complete(tempGrid))
            {
              // point the old grid to the value of tempGrid
              for (int i = 0; i<s;i++)
                grid[i] = Arrays.copyOf(tempGrid[i], s);
              return true;
            } else return false;
          } else return false;
        }
    die("How did you get here",1);
    return false;
  } // method complete

  /**
  * Returns true if there is ANY way to complete a polyomino
  */
  private boolean completeAPolyominoWithBlackMagic(int[][] grid, Point coords)
  {
    List<Point> pocket;
    Set<Point> currentPolyomino;
    printGrid(grid);
    // Find the space taken up by the current polyomino.
    currentPolyomino=findAdj(coords,grid,null,grid[coords.x][coords.y]);
    pocket=new ArrayList<Point>(findAdjAndEmpty(coords,grid,null,grid[coords.x][coords.y]));
    int val=grid[coords.x][coords.y];
    int limit=val-currentPolyomino.size();
    if (pocket.size()<limit)//bad error checking - doesn't work asexpected. checks if 
    {
      die("inside completeAPolyominoWithBlackMagic, failed as pocket size too small: " + pocket.size() + " limit: " + limit,13);
      return false;
    }
    else if (anyPossibleSequenceForPolyomino(pocket,val,val,new ArrayList<Point>(currentPolyomino),0,grid))
      return true;
    else return false;
  } // method completePolyomino
private boolean anyPossibleSequenceForPolyomino(List<Point>pocket,int val,int limit,List<Point>sequence,int currentIndex,int[][]grid){return anyPossibleSequenceForPolyomino(pocket,val,limit,sequence,currentIndex,grid,0);}
  /**
  * Given a pocket (of space to fill), a value to fill it with,
  * A sequence (of objects in the pocket), and a current index(in the sequence)
  * Recursively iterates through all possible combinations of a sequence and tests
  * if any sequence of filling the space can make a polyomino. Modifies grid if filling the space can generate a valid polyomino, then returns true.
  * @param limit The amount of spaces needed to fill.
  * @return whether or not a polyomino can be made.
  */
  private boolean anyPossibleSequenceForPolyomino(List<Point>pocket,int val,int limit,List<Point>sequence,int currentIndex,int[][]grid, int stack)
  {
     System.out.println("stack: " +stack+" limit "+ limit+" seq size "+sequence.size()+" currentIndex "+currentIndex);
    gabenGrid=grid;

    if(sequence==null)sequence=new ArrayList<Point>();
    printSet(sequence);
    if (currentIndex<limit && sequence.size() <limit)
    {
      for (int i=0;i<pocket.size();i++)
      {
        // If not already used in the sequence, add it, recurse through the rest of the sequence.
        if(!sequence.contains(pocket.get(i)) && grid[pocket.get(i).x][pocket.get(i).y]!=val && sequence.add(pocket.get(i)))
        {
          if(anyPossibleSequenceForPolyomino(pocket,val,limit,sequence,currentIndex+1,grid,stack+1)) // I found a space for this polyomino
          {
            die("this sequence sucess",2);// die
            if(complete(grid))
              return true;
            else
              sequence.remove(pocket.get(i));
          }
          else // This sequence failed.
          { 
            sequence.remove(pocket.get(i));
            //return false;
          }
        }
      }
      // If it succeded, it will have returned from in the loop.
      return false;
    } else if (currentIndex > limit || sequence.size() > limit)
      return false;
      else
    {
      // Copy the whole grid.
      int[][] tempGrid=new int[s][s];
      gabenGrid=tempGrid;
      for (int i = 0; i<s;i++)
        for(int k=0;k<s;k++)
          tempGrid[i][k]=grid[i][k];
      for(Point c:sequence)
      {
        tempGrid[c.x][c.y]=val;
      }
      if(isValid(tempGrid))
      {
        // point the old grid to the value of tempGrid
        // Success here.... check the rest of the thing Fillomino out
        if(complete(tempGrid))
        {
          for (int i = 0; i<s;i++)
            grid[i] = Arrays.copyOf(tempGrid[i], s);
          die("complete(tempGrid) is true, ",4);
          return true;
        }
        else
        {
          //die("complete(tempGrid) failed", 5);
          return false;
        }
      } else
      {
        System.out.println("invalid grid");
        printGrid(tempGrid);
        return false;
        //die("isValid is falser",6);
      }
    } // if-else
  } // method anyPossibleSequenceForPolyomino
  int[][] gabenGrid;
  private void die(String out, int i){System.err.println(out + i);printGrid(gabenGrid);System.exit(i);}private void die(String msg){die(msg,0);}

  /**
  * Check if a grid is valid. NB: not reliable in general, only if anything on the grid is empty space
  * or board.<p>
  * Returns false if a polymino of value n has more than n squares
  * joined horizontally or vertically.
  * @return if this grid is a valid fillomino
  */
  private boolean isValid(int[][] grid)
  {
    for(int i=0;i<s;i++)
      for(int j=0;j<s;j++)
        // If this grid at i,j has value !=-1 ie if it's partof a polyomino, search the local pocket of that value
        // If the pocket size is bigger than the value, return false
        if(grid[i][j]!=-1)
        {
          Set<Point> same=findAdj(new Point(i,j),grid,null,grid[i][j]);
          if(same.size()>grid[i][j])
          {
            System.out.println("too many elements at i: " +i + " j: " + j + ", found " + same.size() + " elements needed " + grid[i][j] + "elements. Found;w");
            printSet(same);
            return false;
          }
          // If it's not possible to fill this polyomino.
          // This doesn not check for overfilling a polyomino...
          Set<Point> fae =findAdjAndEmpty(new Point(i,j),grid,null,grid[i][j]);
          if(findAdjAndEmpty(new Point(i,j),grid,null,grid[i][j]).size()<grid[i][j])
          {
            System.out.println("Found pocket of size " + fae.size() + ", needed size: " + grid[i][j] +", at i: "+i+", j: " +j); 
            return false;
          }
        }
    return true;
  } // method isValid

  /**
  * Find all the boxes adjacent to the given cell, including the current cell 
  * if the current cells value == i
  * @param i the value to find all adjacent boxes for.
  * 
  */
  private Set<Point> findAdjAndEmpty(Point p, int[][] grid, Set<Point> squaresInPocket, int i)
  {
    squaresInPocket=(squaresInPocket==null)?new HashSet<Point>():squaresInPocket;
    if(grid[p.x][p.y]==i||grid[p.x][p.y]==-1)squaresInPocket.add(p);
    int x=p.x,y=p.y;
    Point t=new Point();
    if (x+1<s&&(grid[x+1][y]==i||grid[x+1][y]==-1))
    {
      t=new Point(x+1,y);
      if(squaresInPocket.add(t))
        squaresInPocket=findAdjAndEmpty(t,grid,squaresInPocket,i);
    }
    if(y+1<s&&(grid[x][y+1]==i||grid[x][y+1]==-1))
    {
      //t.x=x;t.y=y+1;
      t=new Point(p.x, p.y + 1);
      if(squaresInPocket.add(t))
        squaresInPocket=findAdjAndEmpty(t,grid,squaresInPocket,i);
    }
    if(x-1>=0&&(grid[x-1][y]==i||grid[x-1][y]==-1))
    {
      //t.x=x-1;t.y=y;
      t=new Point(p.x-1 , p.y);
      if(squaresInPocket.add(t))
        squaresInPocket=findAdjAndEmpty(t,grid,squaresInPocket,i);
    }
    if(y-1>=0&&(grid[x][y-1]==i||grid[x][y-1]==-1))
    {
      //t.x=x;t.y=y-1;
      t=new Point(p.x, p.y - 1);      
      if(squaresInPocket.add(t))
        squaresInPocket=findAdjAndEmpty(t,grid,squaresInPocket,i);
    }
    return squaresInPocket;
  } // method findAdjAndEmpty

  /**
  * Find all the boxes adjacent to the given cell, including the current cell 
  * if the current cells value == i
  * @param i the value to find all adjacent boxes for.
  * 
  */
  private Set<Point> findAdj(Point p, int[][] grid, Set<Point> squaresInPocket, int i)
  {
    squaresInPocket=(squaresInPocket==null)?new HashSet<Point>():squaresInPocket;
    if(grid[p.x][p.y]==i)squaresInPocket.add(p);
    int x=p.x,y=p.y;
    Point t=new Point(p);
    if (x+1<s&&grid[x+1][y]==i)
    {
      t=new Point(x+1,y);
      if(squaresInPocket.add(t))
        squaresInPocket=findAdj(t,grid,squaresInPocket,i);
    }
    if(y+1<s&&grid[x][y+1]==i)
    {
      t.x=x;t.y=y+1;
      if(squaresInPocket.add(t))
        squaresInPocket=findAdj(t,grid,squaresInPocket,i);
    }
    if(x-1>=0&&grid[x-1][y]==i)
    {
      t.x=x-1;t.y=y;
      if(squaresInPocket.add(t))
        squaresInPocket=findAdj(t,grid,squaresInPocket,i);
    }
    if(y-1>=0&&grid[x][y-1]==i)
    {
      t.x=x;t.y=y-1;
      if(squaresInPocket.add(t))
        squaresInPocket=findAdj(t,grid,squaresInPocket,i);
    }
    return squaresInPocket;
  } // method findAdj

  /**
  * Check if a grid is completed.
  */
  private boolean isComplete(int grid[][])
  {
    // If any polyomino has too many/too few squares linked, return false.
    for(int i=0;i<s;i++)
      for(int j=0;j<s;j++)
        // If the pocket size is != value, return false, polynomial obv failed.
        if(findAdj(new Point(i,j),grid,null,grid[i][j]).size()!=grid[i][j])
          return false;
      // Ya got this far, so you must be right!
    return true;
  } // method isComplete
  private void printGrid(int[][] grid)
  {
    System.out.println("Printing grid---------------------------------");
    for(int i=0;i<s;i++)
    {
      for(int j=0;j<s;j++)
      {
        System.out.printf("%2s", grid[i][j]==-1?".":Integer.toString(grid[i][j]));
      }
      System.out.println(" ");
    }
  } // method printGrid DEBUG
  private void printSet(Collection<Point> set)
  {
    System.out.println("Inside printSet, size: " + set.size() + " of class: " + set.getClass());
    for(Iterator<Point> it = set.iterator();it.hasNext();)
    {
      Point next = it.next();
      if(next instanceof Point)
      {
        System.out.print(set);
        break;
      }
      System.out.println("row " +next.x + " col: " +next.y);
    }
    //try{Thread.sleep(1000);}catch(InterruptedException e){}
    int c=0,c2=0;
    for(Iterator<Point> it = set.iterator();it.hasNext();c++)
    {
      c2=0;
      Point p1 = it.next();
      for(Iterator<Point> it2 = set.iterator();it2.hasNext();c2++)
      {
        Point p2 =it2.next();
        if(p1.equals(p2) && c!=c2)
        {
          //System.out.println("what the actual fuck, " + p1 + " equals " + p2+" c " + c+ " c2 "+c2 );
          System.out.println("I think I broke java... Collection I'm using: " + set.getClass());
          System.out.println("Contains " + p1 + " and " + p2);
          System.out.println("With hashcodes: " + p1.hashCode() + " and " + p2.hashCode() + " respectively ");
          System.out.println("Do they point to the same place? " + (p1==p2));

          System.out.println("Here's the set: " + set);
        }
      }
    }
  } // method printSet DEBUG
} // class FillominoSolver
