package com.example.service;


import com.example.dto.JobCompanyDto;
import com.example.model.Company;
import com.example.model.Job;
import com.example.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;


    public List<JobCompanyDto> getAllJobs(){
        List<Job> jobs =  jobRepository.findAll();
        RestTemplate restTemplate = new RestTemplate();
        List<JobCompanyDto> jobCompanyDtos = new ArrayList<>();
        for(Job job :jobs){
            JobCompanyDto jobCompanyDto = new JobCompanyDto();
            jobCompanyDto.setJob(job);
            Company company = restTemplate.getForObject("http://localhost:8081/company/"+job.getCompanyId(), Company.class);
            jobCompanyDto.setCompany(company);
            jobCompanyDtos.add(jobCompanyDto);
        }
        return jobCompanyDtos;
    }

    public void createJob(Job job){
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
