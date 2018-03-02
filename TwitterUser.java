package classIsInSession;
import java.util.HashSet;
import java.util.Set;

public class TwitterUser implements Comparable<Object>, Cloneable
{
	 int userID;
	 Set<Integer> followers=new HashSet<Integer>();
	 
	 TwitterUser(int myInt, Set<Integer> myHash)
	{
		setUserID(myInt);
		setFollowers(myHash);
	}

	public int getUserID() 
	{
		return userID;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((followers == null) ? 0 : followers.hashCode());
		result = prime * result + userID;
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TwitterUser other = (TwitterUser) obj;
		if (followers == null) {
			if (other.followers != null)
				return false;
		} else if (!followers.equals(other.followers))
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}

	public void setUserID(int userID) 
	{
		this.userID = userID;
	}

	public Set<Integer> getFollowers() 
	{
		return followers;
	}

	public void setFollowers(Set<Integer> followers) 
	{
		this.followers = followers;
	}
	public void addFollowers(int i) 
	{
		this.followers.add(i);
	}

	public String toString() 
	{
		return userID+" is following "+followers+"\n";
	}
	
	//for sorting the users:
	//or play with treeset?
	@Override
	public int compareTo(Object o) 
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
}
