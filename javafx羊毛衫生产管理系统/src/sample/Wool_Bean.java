package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/*定义数据模型*/
public class Wool_Bean {
	private final SimpleIntegerProperty id;
	private final SimpleStringProperty name;
	private final SimpleStringProperty color;
	private final SimpleStringProperty size;
	private final SimpleStringProperty price;
	private final SimpleStringProperty order_quantity;
	private final SimpleStringProperty production_progress;
	private final SimpleStringProperty material_info;
	private final SimpleStringProperty quality_status;


	public Wool_Bean(Integer id, String name, String color, String size, String price, String order_quantity, String production_progress, String material_info , String quality_status){
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.color = new SimpleStringProperty(color);
		this.size = new SimpleStringProperty(size);
		this.price = new SimpleStringProperty(price);
		this.order_quantity = new SimpleStringProperty(order_quantity);
		this.production_progress = new SimpleStringProperty(production_progress);
		this.material_info = new SimpleStringProperty(material_info);
		this.quality_status = new SimpleStringProperty(quality_status);
	}

	public Integer getId() {
		return this.id.get();
	}
	public String getName() {
		return this.name.get();
	}
	public String getColor() {
		return this.color.get();
	}
	public String getSize() {
		return this.size.get();
	}
	public String getPrice() {
		return this.price.get();
	}
	public String getOrder_quantity() {
		return this.order_quantity.get();
	}
	public String getProduction_progress() {
		return this.production_progress.get();
	}
	public String getMaterial_info() {
		return this.material_info.get();
	}
	public String getQuality_status() {
		return this.quality_status.get();
	}
}