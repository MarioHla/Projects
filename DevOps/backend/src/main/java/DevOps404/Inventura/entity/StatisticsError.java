package DevOps404.Inventura.entity;

public class StatisticsError 
{
	private int valid;
	private int invalid;
	
	public StatisticsError() {
		
	}
	
	public StatisticsError(int valid, int invalid) {
		this.valid = valid;
		this.invalid = invalid;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
	public int getInvalid() {
		return invalid;
	}
	public void setInvalid(int invalid) {
		this.invalid = invalid;
	}
	
	
}
