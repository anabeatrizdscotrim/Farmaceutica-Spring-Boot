package com.farmaceutica.demo.services;

import com.farmaceutica.demo.models.Funcionario;
import com.farmaceutica.demo.repository.FuncionarioRepository;
import com.farmaceutica.demo.security.FuncionarioDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioDetailsService implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String identificacao) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepository.findByIdentificacao(identificacao)
                .orElseThrow(() -> new UsernameNotFoundException("Funcionário não encontrado com identificacao: " + identificacao));

        // Aqui você pode usar sua classe FuncionarioDetails que implementa UserDetails
        return new FuncionarioDetails(funcionario);
    }
}
