package com.client.client.gateway;

import com.client.client.DTO.PrescriptionDTO;
import com.client.client.DTO.map.AddDrugDTO;
import com.client.client.DTO.map.CheckOutDTO;
import com.client.client.DTO.map.PrescriptionDTOM;

public interface PrescriptionGateway {
    PrescriptionDTO save(PrescriptionDTO prescriptionDTO);
    PrescriptionDTOM findByAppointmentId(Long id);
    PrescriptionDTOM findById(Long id);
    Boolean saveArticle(AddDrugDTO addDrugDTO);
    Boolean check_out(CheckOutDTO check);

}
