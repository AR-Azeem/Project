package com.example.service;

import com.example.model.Company;
import com.example.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }


    public Company getCompaniesById(long id){
        return companyRepository.findById(id).orElse(null);
    }

    public void createCompany(Company company){
        companyRepository.save(company);
    }

    public boolean updateCompany(long id, Company company){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company updatedCompany = optionalCompany.get();
            updatedCompany.setName(company.getName());
            updatedCompany.setTitle(company.getTitle());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    public boolean deleteCompany(long id){
        try{
            companyRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
