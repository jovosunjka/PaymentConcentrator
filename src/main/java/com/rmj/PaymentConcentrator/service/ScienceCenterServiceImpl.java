package com.rmj.PaymentConcentrator.service;

import com.rmj.PaymentConcentrator.exception.AlreadyExistsException;
import com.rmj.PaymentConcentrator.model.ScienceCenter;
import com.rmj.PaymentConcentrator.repository.ScienceCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ScienceCenterServiceImpl implements ScienceCenterService {

    @Autowired
    private ScienceCenterRepository scienceCenterRepository;


    @Override
    public void add(ScienceCenter scienceCenter) {
        if (scienceCenterRepository.exists(Example.of(scienceCenter))) {
            throw new AlreadyExistsException(
                    String.format("There is already a science center with the name %s", scienceCenter.getName()));
        }
        scienceCenterRepository.save(scienceCenter);
    }
}
