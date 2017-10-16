package br.ufrj.cos482.service.impl;

import br.ufrj.cos482.service.UsuarioService;
import br.ufrj.cos482.domain.Usuario;
import br.ufrj.cos482.repository.UsuarioRepository;
import br.ufrj.cos482.service.dto.UsuarioDTO;
import br.ufrj.cos482.service.mapper.UsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing Usuario.
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

    private final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    /**
     * Save a usuario.
     *
     * @param usuarioDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        log.debug("Request to save Usuario : {}", usuarioDTO);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    /**
     *  Get all the usuarios.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Usuarios");
        return usuarioRepository.findAll(pageable)
            .map(usuarioMapper::toDto);
    }


    /**
     *  get all the usuarios where Aluno is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<UsuarioDTO> findAllWhereAlunoIsNull() {
        log.debug("Request to get all usuarios where Aluno is null");
        return StreamSupport
            .stream(usuarioRepository.findAll().spliterator(), false)
            .filter(usuario -> usuario.getAluno() == null)
            .map(usuarioMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  get all the usuarios where Professor is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<UsuarioDTO> findAllWhereProfessorIsNull() {
        log.debug("Request to get all usuarios where Professor is null");
        return StreamSupport
            .stream(usuarioRepository.findAll().spliterator(), false)
            .filter(usuario -> usuario.getProfessor() == null)
            .map(usuarioMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one usuario by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UsuarioDTO findOne(Long id) {
        log.debug("Request to get Usuario : {}", id);
        Usuario usuario = usuarioRepository.findOneWithEagerRelationships(id);
        return usuarioMapper.toDto(usuario);
    }

    /**
     *  Delete the  usuario by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Usuario : {}", id);
        usuarioRepository.delete(id);
    }
}
