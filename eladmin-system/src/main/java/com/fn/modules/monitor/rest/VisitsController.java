package com.fn.modules.monitor.rest;

import com.fn.modules.monitor.service.VisitsService;
import com.fn.modules.monitor.service.VisitsService;
import com.fn.utils.RequestHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leo
 * @date 2018-12-13
 */
@RestController
@RequestMapping("api")
public class VisitsController {

    @Autowired
    private VisitsService visitsService;

    @PostMapping(value = "/visits")
    public ResponseEntity create(){
        visitsService.count(RequestHolder.getHttpServletRequest());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/visits")
    public ResponseEntity get(){
        return new ResponseEntity(visitsService.get(),HttpStatus.OK);
    }

    @GetMapping(value = "/visits/chartData")
    public ResponseEntity getChartData(){
        return new ResponseEntity(visitsService.getChartData(),HttpStatus.OK);
    }
}
