package DevOps404.Inventura.DAO;

import java.util.List;

import org.springframework.stereotype.Service;

import DevOps404.Inventura.DTOI.ChangeDTOI;
import DevOps404.Inventura.entity.Change;
import DevOps404.Inventura.utills.Coordiante;
import DevOps404.Inventura.utills.LocationUtill;

@Service
public class ChangeDAO {

	private ChangeDTOI changeDTOI;
	private LocationUtill location;

	public ChangeDAO(ChangeDTOI changeDTOI, LocationUtill location) {
		this.changeDTOI = changeDTOI;
		this.location = location;
	}
	
	public List<Change> getChangesForWorker(Long idWorker) {
		return changeDTOI.getChangesForWorker(idWorker);
	}
	
	public void addChange(Double xlocation, Double yLocation , Long amount, Long warehouseId, Long articleId, Long userId) {
		changeDTOI.addChange(location.getWerehouseByCoordinate(new Coordiante(xlocation, yLocation)), amount, warehouseId, articleId, userId);
	}
	
}
