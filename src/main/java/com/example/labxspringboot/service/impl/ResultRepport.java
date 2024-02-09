package com.example.labxspringboot.service.impl;

import com.example.labxspringboot.dto.RapportDto;
import com.example.labxspringboot.service.IRapportResultatService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import com.example.labxspringboot.repository.IAnalyseRepository;

import java.io.File;
import java.util.*;

@Service
public class ResultRepport implements IRapportResultatService {

    @Autowired
    private IAnalyseRepository iAnalyseRepository;

    private final String outputPath = "C:\\Users\\hamza\\Desktop";

    public byte[] exportRepport(Long id) {
        try {
            List<Object[]> results = iAnalyseRepository.getAnalysisReport(id);
            List<RapportDto> rapportDtos = new ArrayList<>();

            for (Object[] result : results) {
                RapportDto rapportDto = new RapportDto();
                rapportDto.setNomanalyse(result[0].toString());
                rapportDto.setNom(result[1].toString());
                rapportDto.setPrenom(result[2].toString());
                rapportDto.setNumero(result[3].toString());
                rapportDto.setAdresse(result[4].toString());
                rapportDto.setDatenaissancepatient(result[5].toString());
                rapportDto.setNomtypeanalyse(result[6].toString());
                rapportDto.setNomtest(result[7].toString());
                rapportDto.setResultat(result[8].toString());
                rapportDto.setLibellenorme(result[9].toString());
                rapportDto.setMaxvaluenorme(Double.parseDouble(result[10].toString()));
                rapportDto.setMinvaluenorme(Double.parseDouble(result[11].toString()));
                rapportDto.setUnitenorme(result[12].toString());

                rapportDtos.add(rapportDto);
            }

            File file = ResourceUtils.getFile("classpath:AnalyseRepport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rapportDtos);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "Hamza Benmoussa");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Save the report file to the specified path
            String reportFilePath = outputPath + File.separator + "AnalyseReport.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportFilePath);

            System.out.println(jasperPrint.toString());
            byte[] reportContent = JasperExportManager.exportReportToPdf(jasperPrint);

            return reportContent;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return new byte[0];
    }
}
