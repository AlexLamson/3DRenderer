package main;

public class Timer
{
	public long current = 0, max = 1;
	boolean firing = false;
	
	//1 = fire every tick, 2 = every 2nd tick, n = every nth tick
	public Timer(long max)
	{
		this.max = max;
		current = max-1;
	}
	
	public void tick()
	{
		if(current == 0 && firing)
			firing = false;
		
		current++;
		
		if(current >= max)
		{
			firing = true;
			current = 0;
		}
	}
	
	public boolean isFiring()
	{
		return firing;
	}
}
