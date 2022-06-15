package com.stefanini.hackathon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefanini.hackathon.dto.AlunoDTO;
import com.stefanini.hackathon.exception.AlunoNotFoundException;
import com.stefanini.hackathon.exception.TurmaNotFoundException;
import com.stefanini.hackathon.mapper.AlunoDTOService;
import com.stefanini.hackathon.model.Aluno;
import com.stefanini.hackathon.repository.AlunoRepository;

@Service

public class AlunoService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AlunoDTOService alunoDTOService;
	
	@Autowired
    private AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<AlunoDTO> findAllAlunos(){
        
    	List<Aluno> alunos = alunoRepository.findAll();
    	
    	return alunos.stream().map(aluno -> mapper.map(aluno, AlunoDTO.class)).collect(Collectors.toList());
    }

    public AlunoDTO findById(Long id) throws AlunoNotFoundException {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(()-> new AlunoNotFoundException(id));
        return mapper.map(aluno, AlunoDTO.class);
    }

    public AlunoDTO save(AlunoDTO aluno) throws TurmaNotFoundException{
    	
    	Aluno newAluno = alunoDTOService.mapAluno(aluno);
    	
    	return mapper.map(alunoRepository.save(newAluno), AlunoDTO.class);
    }
    
    public void deleteById(Long id) throws AlunoNotFoundException {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(()-> new AlunoNotFoundException(id));
        alunoRepository.deleteById(id);
    }

}
