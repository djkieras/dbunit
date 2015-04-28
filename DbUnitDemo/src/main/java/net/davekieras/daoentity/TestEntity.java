package net.davekieras.daoentity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "test_table")
public class TestEntity {

	public static final String KEY_COLUMN = "test_table_pk_col";
	
	@Column(name = KEY_COLUMN, updatable = false)
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "date_created", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@Column(name= "date_modified", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModified;
	
	@Column(name = "transaction_id")
	private String transactionId;
	
	@Column(name = "version")
	@Version
	private long version;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = TestEntity.KEY_COLUMN)
	private List<TestDetailEntity> details;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public List<TestDetailEntity> getDetails() {
		return details;
	}

	public void setDetails(List<TestDetailEntity> details) {
		this.details = details;
	}

}
