package com.asignment3.asignment3.service;

import com.asignment3.asignment3.model.dto.AppointmentDTO;
import com.asignment3.asignment3.model.dto.DoctorDTO;
import com.asignment3.asignment3.model.dto.PatientDTO;
import com.asignment3.asignment3.model.dto.PrescriptionDTO;
import com.asignment3.asignment3.model.dto.map.PrescriptionDTOM;


import java.util.List;

public interface PrescriptionService {

    PrescriptionDTO save(PrescriptionDTO prescriptionDTO);
    void delete(Long prescription_id);
    List<PrescriptionDTOM> findAllPrescriptions();
    boolean addDrug(Long prescription_id, Long drug_id, Integer quantity);
    PrescriptionDTOM findByPrescriptionId(Long id);
    boolean transferDrugsToPatient(PrescriptionDTOM prescriptionDTO,
                                   AppointmentDTO appointmentDTO,
                                   boolean fromCard, String promotionalCod);
    PrescriptionDTOM findByAppointmentId(Long app_id);

}
