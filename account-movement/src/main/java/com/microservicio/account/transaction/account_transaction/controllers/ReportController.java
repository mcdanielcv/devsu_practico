package com.microservicio.account.transaction.account_transaction.controllers;

import com.microservicio.account.transaction.account_transaction.services.AccountService;
import com.microservicio.cuenta.movimiento.cuenta_movimiento.com.microservicio.account.transaction.account_transaction.models.ReportDTO;
import com.microservicio.cuenta.movimiento.cuenta_movimiento.com.microservicio.account.transaction.account_transaction.models.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    

    @Autowired
    private AccountService accountService;


    @GetMapping
    public ResponseEntity<?> getReporteByRange(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) List<LocalDate> fecha) {
        try {
            if (fecha.size() != 2) {
                throw new IllegalArgumentException("Se deben proporcionar dos fechas en el rango.");
            }

            LocalDate startLocalDate = fecha.get(0);
            System.out.println("startDate->"+startLocalDate);
            LocalDate endLocalDate = fecha.get(1);
            System.out.println("endDate->"+endLocalDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Date startDate =  Date.from(startLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date endDate =  Date.from(endLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());


            List<ReportDTO> reporte = accountService.generateReport(startDate, endDate);
            return new ResponseEntity<>(reporte, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseVo(false, e.getMessage()));
        }
    }


    /*@GetMapping
    public ResponseEntity<?> getReporteByRangeDate(
            @RequestParam("clientId") Long clientId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        try {
            List<ReportDTO> reporte = accountService.generateReport(clientId, startDate, endDate);
            return new ResponseEntity<>(reporte, HttpStatus.OK);
        } catch (RuntimeException | ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseVo(false, e.getMessage()));
        }
    }*/
  
}
