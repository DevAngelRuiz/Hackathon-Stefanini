package com.stefanini.hackathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.hackathon.dto.AlunoDTO;
import com.stefanini.hackathon.exception.AlunoNotFoundException;
import com.stefanini.hackathon.exception.TurmaNotFoundException;
import com.stefanini.hackathon.service.AlunoService;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@GetMapping()
	public ResponseEntity<List<AlunoDTO>> getAllAlunos() {

		List<AlunoDTO> findAllAlunos = alunoService.findAllAlunos();

		return ResponseEntity.status(HttpStatus.OK).body(findAllAlunos);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<AlunoDTO> getByID(@PathVariable Long id) throws AlunoNotFoundException  {

		AlunoDTO alunoDTO = alunoService.findById(id);

		return ResponseEntity.status(HttpStatus.OK).body(alunoDTO);
	}
	
	@PostMapping
	public ResponseEntity<AlunoDTO> saveAluno(@RequestBody AlunoDTO alunoDTO) throws TurmaNotFoundException {

		AlunoDTO saved = alunoService.save(alunoDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AlunoDTO> deleteByID(@PathVariable Long id) throws AlunoNotFoundException {

		alunoService.deleteById(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
