package com.leonardo.apirelatoriovendas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.apirelatoriovendas.repositories.SellerRepository;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

}
