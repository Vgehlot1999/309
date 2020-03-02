package com.example.demo.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "chat")
public class chat {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="chat_id")
	private Integer chat_id;
	
	@Column(name="chat_name")
	private String chat_name;
	
	public Integer getChat_id() {
		return chat_id;
	}

	public String getChat_name() {
		return chat_name;
	}
	public void setChat_name(String chat_name) {
		this.chat_name = chat_name;
	}
	
	@ManyToMany(mappedBy = "chatRooms")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	List<Students> chat_stud_list;
}
