package vn.dunglq.datasource.second.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BAR")
public class Bar {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "BAR")
	private String bar;

	Bar(String bar) {
		this.bar = bar;
	}

	Bar() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

}