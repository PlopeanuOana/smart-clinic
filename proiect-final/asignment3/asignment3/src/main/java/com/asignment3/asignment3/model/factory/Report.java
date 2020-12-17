package com.asignment3.asignment3.model.factory;

import com.asignment3.asignment3.model.dto.DrugDTO;
import com.asignment3.asignment3.model.entity.Drug;

import java.io.IOException;
import java.util.List;

public interface Report {
     void report(List<DrugDTO> drugs) throws IOException;
}
