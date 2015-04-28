CREATE TABLE IF NOT EXISTS test_table (
	test_table_pk_col IDENTITY NOT NULL,
	date_created TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP),
	date_modified TIMESTAMP,
	transaction_id VARCHAR(255),
	version BIGINT(10) DEFAULT(0),
	PRIMARY KEY (test_table_pk_col),
	CONSTRAINT uk1_test_table UNIQUE INDEX (transaction_id)
);

CREATE TABLE IF NOT EXISTS test_table_detail (
	test_table_det_pk_col IDENTITY NOT NULL,
	test_table_pk_col BIGINT(10) NOT NULL,
	date_created TIMESTAMP NOT NULL DEFAULT (CURRENT_TIMESTAMP),
	detail VARCHAR(4000) NOT NULL,
	PRIMARY KEY (test_table_det_pk_col),
	CONSTRAINT fk1_test_table_detail FOREIGN KEY (test_table_pk_col) REFERENCES test_table (test_table_pk_col)
);
