package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "MyEntity")
public class MyEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "deleted", nullable = true)
    private Long deleted;

    private MyEntity() {
        // default constructor for hibernate
    }

    public MyEntity(
        final UUID id,
        final String name,
        final Long deleted
    ) {
        this.id = id;
        this.name = name;
        this.deleted = deleted;
    }

    public MyEntity(
        final String name
    ) {
        this(UUID.randomUUID(), name, null);
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getDeleted() {
        return deleted;
    }

    public void setDeleted(final Long deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyEntity myEntity = (MyEntity) o;
        return Objects.equals(id, myEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyEntity{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", deleted=").append(deleted);
        sb.append('}');
        return sb.toString();
    }
}
