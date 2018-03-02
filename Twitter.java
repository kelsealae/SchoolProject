package classIsInSession;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Twitter 
{
	static Scanner myScan=new Scanner(System.in);
	static Twitter theOne=new Twitter();
	static Set<Integer> myHash=new HashSet<Integer>(30000);
	static Set<Integer> currentUsers=new HashSet<Integer>();
	static TwitterUser user=new TwitterUser(0, myHash);
	static ArrayList<TwitterUser> testUsers=new ArrayList<TwitterUser>();
	static ArrayList<TwitterUser> neighborhood;
	static ArrayList<Integer> allUsers;
	 
	public static void main(String[] args) 
	{
			importData2();
		
//		Set<Integer> newHash=new HashSet<Integer>();
//		newHash.add(2);
//		newHash.add(8);
//		newHash.add(5);
//		user.setUserID(0);
//		user.addFollowers(11);
//		user.setFollowers(newHash);
//		Set<Integer> newHash2=new HashSet<Integer>();
//		newHash2.add(1);
//		newHash2.add(7);
//		newHash2.add(9);
//		user.addFollowers(11);
//		user.setUserID(1);
//		user.setFollowers(newHash2);
//		user.getFollowers();
//		System.out.println("user: "+user.toString());
//		System.out.println("followers: "+user.getFollowers());
//		user.setUserID(0);
//		System.out.println("user: "+user.toString());
//		System.out.println("followers: "+user.getFollowers());
//			menu();
		
	}
	
		public static void menu() 
		{	
			System.out.println("\nMenu");
			System.out.println("\t1. getNeighborhood()");
			System.out.println("\t2. cloneUser()");
			System.out.println("\t3. cloneUser()");
			System.out.println("\t4. Quit");
					
					int choice=0;
					System.out.println("\nWhat would you like to do?(1-3)");
					choice=myScan.nextInt();
					myScan.nextLine();
					
					switch (choice)
					{	
					case 0: 
						System.out.println("Invalid choice, please try again.\n");
						menu();
						break;
					case 1:
						prepNeighborhood();
						break;
					case 2:
						cloneUser();
						break;
					case 3: 
						System.out.println("Select User.(to be added)");
						menu();
						break;
					case 4: 
						myScan.close();
						System.exit(0);
						break;
					default: System.out.println("Invalid choice, please try again.\n");
						break;
					}	
			}
				
	/*		Method: importData
	 * This method brings data into the program from the file.
	 *  
	 *  Here we need to read in the data from the file. the format is: i1 space i2 space i1 space i2... where i1 is the user who is following i2.
		We need to put this into TwitterUsers. TwitterUser 0 is following these users: 			 
		how to associate a unique classInt with a unique classHash?
						 
	 */
	public static void importData2() 
	{
		BufferedReader buffer;
		try 
		{
			buffer = new BufferedReader(new FileReader("social_network.edgelist"));
			String stringLine;
			int k1=0;
			int k2=0;

				while((stringLine=buffer.readLine()) != null) 
				{
					Thread.sleep(100);	//for testing

							String[] myLine = stringLine.split(" ");
//					    	System.out.println(Arrays.toString(myLine)); 	//for testing
							k1=Integer.parseInt(myLine[0]);
							k2=Integer.parseInt(myLine[1]);              
										
										if(currentUsers.contains(k1)) 
										{
											user.setUserID(k1);
											user.addFollowers(k2);
											testUsers.add(user);
											System.out.println("user.tostring: "+user.toString());	//for testing
										}
										
										if(!currentUsers.contains(k1)) 	
										{ 
											Set<Integer> newHash=new HashSet<Integer>();
											newHash.add(k2);
											TwitterUser user=new TwitterUser(k1, newHash);
											currentUsers.add(k1);
											testUsers.add(user);
											System.out.println("user.tostring: "+user.toString());	//for testing
//											System.out.println("size: "+currentUsers.size());	//for testing
										}	
										if(currentUsers.size()==150000) 
										{
											System.out.println("You're halfway there!");
										}
					}
		} 
		catch (NumberFormatException e) 
		{
				e.printStackTrace();
		} 
		catch (IOException e) 
		{
				e.printStackTrace();
		} 
		catch (InterruptedException e) 
		{
				e.printStackTrace();
		}
		System.out.println("List load completed: "+currentUsers.size()+" users");	
		System.out.println("This is user 0: "+testUsers.get(0).toString());
	}
	
	public static void prepNeighborhood() 
	{
		System.out.println("Which user would you like to get the followers of followers for?");
		int idNumber = myScan.nextInt();
		System.out.println("How many followers deep would you like to go?");
		int depth = myScan.nextInt();
		
		getNeighborhood(idNumber, depth);
	}
	/*		Method: getNeighborhood
	 * returns an arraylist of twitterusers that are following your user.
	 * the depth is how many users following users that you go.
	 * for each depth(method run-through), get the users that are following the userID.
	 */
	/*how to get the users following the userID?
	 * get the hashset of current user.
	 */
	 public static ArrayList<TwitterUser> getNeighborhood(int userID, int depth)
	 {
		System.out.println("toString: "+user.toString());
		user.setUserID(userID);	//invalid
		Set<Integer> z = user.getFollowers();
		neighborhood.add(user);		//cant do this because you can't set user like that
		depth=depth--;
		System.out.println("user " +user+" is following: "+z);
		
		if(depth<=0) return neighborhood;
		
		return getNeighborhood(userID, depth);
	}
	public static void cloneUser() {
		int userID;
		System.out.println("Which user would you like to clone?");
		userID=myScan.nextInt();
		user.setUserID(userID);
		System.out.println("Now cloning user"+user);
		menu();
		/*
		 *  setting the clone's "following" list to empty, and making
		sure the original object still has the contents of its following list (i.e. you want to
		make sure that changing an attribute of the clone does not affect the original)

		 */
	}
	public static void importData() 
	{
		BufferedReader buffer;
		try 
		{
			buffer = new BufferedReader(new FileReader("social_network.edgelist"));
			String stringLine;
			int k1=0;
			int k2=0;

				while((stringLine=buffer.readLine()) != null) 
				{
					Thread.sleep(0);	//for testing

							String[] myLine = stringLine.split(" ");
//					    	System.out.println(Arrays.toString(myLine)); 	//for testing
							k1=Integer.parseInt(myLine[0]);
							k2=Integer.parseInt(myLine[1]);              
										
										if(currentUsers.contains(k1)) 
										{
											user.setUserID(k1);
											user.addFollowers(k2);
//											System.out.println("user.tostring: "+user.toString());	//for testing
										}
										
										if(!currentUsers.contains(k1)) 	
										{ 
											Set<Integer> newHash=new HashSet<Integer>();
											newHash.add(k2);
											user.setUserID(k1);
											user.setFollowers(newHash);
											currentUsers.add(k1);
//											System.out.println("user.tostring: "+user.toString());	//for testing
//											System.out.println("size: "+currentUsers.size());	//for testing
										}	
										if(currentUsers.size()==150000) 
										{
											System.out.println("You're halfway there!");
										}
					}
		} 
		catch (NumberFormatException e) 
		{
				e.printStackTrace();
		} 
		catch (IOException e) 
		{
				e.printStackTrace();
		} 
		catch (InterruptedException e) 
		{
				e.printStackTrace();
		}
		System.out.println("List load completed: "+currentUsers.size()+" users");	
	}
}
