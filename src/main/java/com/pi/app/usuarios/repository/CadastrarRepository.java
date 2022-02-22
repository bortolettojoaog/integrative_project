package com.pi.app.usuarios.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pi.app.usuarios.models.CadastrarModels;

@Repository
public interface CadastrarRepository extends CrudRepository<CadastrarModels, Long> {

	CadastrarModels findById(long id);

}
