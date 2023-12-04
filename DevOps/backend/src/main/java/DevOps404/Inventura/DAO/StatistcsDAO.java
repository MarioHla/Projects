package DevOps404.Inventura.DAO;

import java.util.List;

import org.springframework.stereotype.Service;

import DevOps404.Inventura.DTOI.StatisticsDTOI;
import DevOps404.Inventura.entity.StatisticsError;
import DevOps404.Inventura.entity.StatisticsUserInfo;

@Service
public class StatistcsDAO {
	
	private StatisticsDTOI statisticsDTOI;
	
	public StatistcsDAO(StatisticsDTOI statisticsDTOI) {
		this.statisticsDTOI = statisticsDTOI;
	}

	public StatisticsError getGlobalStatistics() {
		int allChanges = statisticsDTOI.getAllChanges();
		int allErrors = statisticsDTOI.getAllErrors();
		return new StatisticsError(allChanges - allErrors, allErrors);
	}
	
	public StatisticsError getWorkerStatistics(Long workerId) {
		int allWorkerChanges = statisticsDTOI.getWorkerChanges(workerId);
		int allWorkerErrors = statisticsDTOI.getWorkerErrors(workerId);
		return new StatisticsError(allWorkerChanges - allWorkerErrors, allWorkerErrors);
	}
	
	public List<StatisticsUserInfo> getAllWorkers() {
		return statisticsDTOI.getAllWorkers();
	}
	
}
