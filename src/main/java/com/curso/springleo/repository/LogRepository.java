package com.curso.springleo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.springleo.entity.Log;

/**
 * The Interface LogRepository.
 */
@Repository("logRepository")
public interface LogRepository extends JpaRepository<Log, Serializable>{

}
