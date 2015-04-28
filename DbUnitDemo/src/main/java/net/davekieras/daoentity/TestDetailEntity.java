package net.davekieras.daoentity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "test_table_detail")
public class TestDetailEntity {

	public static final String KEY_COLUMN = "test_table_det_pk_col";
	
	@Column(name = KEY_COLUMN, updatable = false)
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "test_table_pk_col", updatable = false)
	private Long parentId;
	
	@Column(name = "date_created", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@Column(name = "detail")
	private String detail;
	
}
