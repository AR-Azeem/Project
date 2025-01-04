package com.example.demo.service;

import com.example.demo.model.Company;
import com.example.demo.model.Job;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;
    @Autowired
    CompanyRepository companyRepository;

    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }

    public void createJob(Job job){
        Company company = job.getCompany();
        if(company.getId() != null){
            company = companyRepository.findById(company.getId()).orElseThrow(()-> new RuntimeException("company not found"));
            job.setCompany(company);
        }
        jobRepository.save(job);
    }

    public void updateJob(long id,Job job){
        Optional<Job> optionalUpdatedJob = jobRepository.findById(id);
        if(optionalUpdatedJob.isPresent()){
            Job updatedJob = optionalUpdatedJob.get();
            updatedJob.setDescription(job.getDescription());
            updatedJob.setTitle(job.getTitle());
            updatedJob.setLocation(job.getLocation());
            updatedJob.setMinSalary(job.getMinSalary());
            updatedJob.setMaxSalary(job.getMaxSalary());
            jobRepository.save(updatedJob);
        }
    }

    public void deleteJob(long id){
        jobRepository.deleteById(id);
    }
}
