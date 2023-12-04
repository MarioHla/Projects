package DevOps404.Inventura.entity;

public class NumberOfArticleInWarehouse {

	private Long warehouseId;
	private String name;
	private Long amount;
	
	public NumberOfArticleInWarehouse() {}
	
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
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	
}
