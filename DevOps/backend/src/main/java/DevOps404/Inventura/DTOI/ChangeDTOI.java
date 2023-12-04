package DevOps404.Inventura.DTOI;

import java.util.Date;
import java.util.List;

import DevOps404.Inventura.entity.Change;

public interface ChangeDTOI {

	public List<Change> getChangesForWorker(Long workerId);
	public void addChange(String location, Long amount, Long warehouseId, Long articleId, Long userId);
	public Change getChangesByDateFromWorkers(Date time,Long articleId, Long werehouseId);
	
}
