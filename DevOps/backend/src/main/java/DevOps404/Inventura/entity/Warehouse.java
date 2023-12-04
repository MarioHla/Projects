package DevOps404.Inventura.entity;

public class Warehouse {

	private Long warehouseId;
	private String name;
	private String address;
	private Long bossId;
	
	public Warehouse() {}

	public Warehouse(Long warehouseId, String name, String address, Long bossId) {
		this.warehouseId = warehouseId;
		this.name = name;
		this.address = address;
		this.bossId = bossId;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getBossId() {
		return bossId;
	}

	public void setBossId(Long bossId) {
		this.bossId = bossId;
	};
	
	
	
	
}
