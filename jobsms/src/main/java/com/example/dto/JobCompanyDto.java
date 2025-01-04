package com.example.dto;

import com.example.model.Company;
import com.example.model.Job;
import lombok.Data;

@Data
public class JobCompanyDto {
    private Job job;
    private Company company;
}
