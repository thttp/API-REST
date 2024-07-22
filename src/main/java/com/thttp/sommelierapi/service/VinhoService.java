package com.thttp.sommelierapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.thttp.sommelierapi.model.Vinho;
import com.thttp.sommelierapi.respository.VinhoRepository;
import com.thttp.sommelierapi.service.exceptions.RecursoNaoEncontradoException;

@Service
public class VinhoService {
	
	@Autowired
	private VinhoRepository vinhoRepository;
	
	public List<Vinho> listar() {
		return vinhoRepository.findAll();
	}
	
	public Vinho buscarPeloId(Long id) {
		
		Vinho vinho = vinhoRepository.findOne(id);
		
		if(vinho == null) {
			
			throw new RecursoNaoEncontradoException("Nenhum vinho foi encontrado");
		}
		
		return vinho;
	}
	
	public Vinho salvar(Vinho vinho) {
		
		
		vinho.setId(null);
		return vinhoRepository.save(vinho);
	}
	
    /**
	 * Metodo para verificar a existencia de um vinho.
	 * Chama o metodo buscarPeloId que se nao encontrar
	 * o vinho lanca uma excecao que lanca um NOT FOUND 
	 * @param vinho
	 */
	public void verificarExistencia(Vinho vinho) {
		buscarPeloId(vinho.getId());
	}
	
	public void atualizar(Vinho vinho) {
		
		verificarExistencia(vinho);
		vinhoRepository.save(vinho);
	}
	
	public void deletar(Long id) {
		
		try {			
			vinhoRepository.delete(id);			
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNaoEncontradoException("O vinho não foi encontrado");
		}
	}

}