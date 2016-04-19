package net.davekieras.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import net.davekieras.json.CSVDeserializer;
import net.davekieras.properties.SnackFooProperties;

@XmlRootElement(name = "Snack")
public class Snack implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Snack() {
		super();
	}

	private Long id;
	private String name;
	private boolean optional;
	@JsonDeserialize(using = CSVDeserializer.class)
	private List<String> purchaseLocations; // May be empty.
	private long purchaseCount;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "M/d/yyyy", timezone=SnackFooProperties.TIME_ZONE_ID)
	private Date lastPurchaseDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public List<String> getPurchaseLocations() {
		return purchaseLocations;
	}

	public void setPurchaseLocations(List<String> purchaseLocations) {
		this.purchaseLocations = purchaseLocations;
	}

	public long getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(long purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public Date getLastPurchaseDate() {
		return lastPurchaseDate;
	}

	public void setLastPurchaseDate(Date lastPurchaseDate) {
		this.lastPurchaseDate = lastPurchaseDate;
	}

}
