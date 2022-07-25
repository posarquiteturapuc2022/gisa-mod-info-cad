package br.com.posarquiteturapuc2022.utils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

@EntityListeners(EntityAbstractListeners.class)
@MappedSuperclass
public abstract class EntityAbstract implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false)
	private UUID id;
	
    @JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate createdAt;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate updateAt;
    
	private Boolean deteled = false;

	protected EntityAbstract() {
	}

	protected EntityAbstract(UUID id) {
		super();
		this.id = id;
	}

	protected EntityAbstract(UUID id, LocalDate createdAt, LocalDate updateAt) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}

	@Override
	public int hashCode() {
		final var prime = 31;
		var result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityAbstract other = (EntityAbstract) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}


	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDate updateAt) {
		this.updateAt = updateAt;
	}

	public Boolean getDeteled() {
		return deteled;
	}

	public void setDeteled(Boolean deteled) {
		this.deteled = deteled;
	}
}
