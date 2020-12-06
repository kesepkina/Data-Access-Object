package com.epam.patient.test.model.service.impl;

import com.epam.patient.exception.FillingException;
import com.epam.patient.test.model.output.ResultsPrinting;
import com.epam.patient.test.model.reader.WarehouseFilling;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PatientServiceImplTest {

    private static final Logger logger = LogManager.getLogger();

    private static final WarehouseFilling filling = new WarehouseFilling();
    private static final PatientServiceImpl patientService = new PatientServiceImpl();
    private static final ResultsPrinting resultsPrinting = new ResultsPrinting();

    @BeforeClass
    public void fill(){
        try {
            filling.fillFromTxtFile();
        } catch (FillingException e) {
            logger.error("Error by filling: ", e);
        }
    }

    @Test
    public void testFindAll() {
        resultsPrinting.printListOfPatients(patientService.findAll());
    }
}
