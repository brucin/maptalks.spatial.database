DROP TABLE IF EXISTS SEEGOO_SAMPLE;

/**********************************/
/* Table Name: SEEGOO_SAMPLE */
/**********************************/
CREATE TABLE SEEGOO_SAMPLE(
		SAMPLE_ID                     		BIGINT(19)		 NOT NULL PRIMARY KEY COMMENT 'SAMPLE_ID',
		SAMPLE_NAME                   		CHAR(60)		 NOT NULL COMMENT 'SAMPLE_NAME',
		SAMPLE_STATUS                 		TINYINT(2)		 NOT NULL COMMENT 'SAMPLE_STATUS',
		SAMPLE_CREATEDATE             		BIGINT(19)		 NOT NULL COMMENT 'SAMPLE_CREATEDATE',
		SAMPLE_MONEY                  		DECIMAL(10)		 NOT NULL COMMENT 'SAMPLE_MONEY'
) COMMENT='SEEGOO_SAMPLE';

