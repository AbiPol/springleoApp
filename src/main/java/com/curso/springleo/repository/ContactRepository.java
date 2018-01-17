package com.curso.springleo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.springleo.entity.Contacts;

@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<Contacts, Serializable>{
//Con esta interfaz que extiende el JPARepository ya tenemos disponibles todos los metodos de la entity Contacts
}
