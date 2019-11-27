package com.rmj.IntegrationMicroservice.controller;

import com.rmj.IntegrationMicroservice.model.ScienceCenter;
import com.rmj.IntegrationMicroservice.service.ScienceCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/integration")
public class IntegrationController {

    @Autowired
    private ScienceCenterService scienceCenterService;


    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody ScienceCenter scienceCenter)
    {
        scienceCenterService.add(scienceCenter);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
