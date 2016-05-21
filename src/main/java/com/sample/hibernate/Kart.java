package com.sample.hibernate;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

 
@Entity
@Table(name="KART")
public class Kart {
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="kart_id")
    private long id;
     
    @Column(name="total")
    private double total;
     
    @ManyToMany(targetEntity = Item.class, cascade = { CascadeType.ALL })
    @JoinTable(name = "KART_ITEMS", 
                joinColumns = { @JoinColumn(name = "kart_id") }, 
                inverseJoinColumns = { @JoinColumn(name = "item_id") })
    private Set<Item> items;



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	public Set<Item> getItems() {
		return items;
	}



	public void setItems(Set<Item> items) {
		this.items = items;
	}
    
     
    
}
