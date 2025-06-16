package com.farmaceutica.demo.services;

import com.farmaceutica.demo.repository.BeneficiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeneficiosService {
    @Autowired
    BeneficiosRepository beneficiosRepository;


}
