package DevOps404.Inventura.DTOI;

import java.util.List;

import DevOps404.Inventura.entity.Warehouse;

public interface WarehouseDTOI {

	public List<Warehouse> getAllWarehouses();
	public Warehouse getWarehouseById(Long warehouseId);
	public Warehouse getWarehouseByBossId(Long bossId);
	public Long getWerehouseByUserId(Long userId);
	
}
