package DevOps404.Inventura.DTOI;

import java.util.Date;

public interface ErrorDTOI {

	public void addError(Long badAmount, Long realAmount, Date time, Long werehouseId, Long articleId, Long workerId);
	
}
