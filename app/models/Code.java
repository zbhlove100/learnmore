package models;

import javax.persistence.Entity;
import javax.persistence.Table;

import play.db.jpa.Model;
@Entity
@Table(name="code_table")
public class Code extends Model{

	public String codeName;
	
	public String codeValue;
	
	public String discription;
	
	public String state;
	
	public long parentCode;
	
	public static final long ROOT = 0L;
}
