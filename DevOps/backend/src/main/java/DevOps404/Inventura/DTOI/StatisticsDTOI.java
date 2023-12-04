package DevOps404.Inventura.DTOI;

import java.util.List;

import DevOps404.Inventura.entity.StatisticsUserInfo;

public interface StatisticsDTOI {

	public Integer getAllChanges();
	public Integer getAllErrors();
	public Integer getWorkerChanges(Long workerId);
	public Integer getWorkerErrors(Long workerId);
	public List<StatisticsUserInfo> getAllWorkers();
	
}
