package com.nit.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="emp")
public class Employee implements Serializable{
	@Id
	@SequenceGenerator(name="gen1",sequenceName="emp_id_seq12",initialValue =1,allocationSize=1 )
	@GeneratedValue(generator="gg1",strategy=GenerationType.SEQUENCE)
private Integer empno;
@Column(length=20)
private String ename="Rana";
@Column(length=20)
private String job;
private Double sal;
private Integer deptno;
}
