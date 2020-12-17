package com.asignment3.asignment3.service.impl;

import com.asignment3.asignment3.model.dto.*;
import com.asignment3.asignment3.model.dto.map.AppointmentDTOM;
import com.asignment3.asignment3.model.dto.map.DrugPrescriptionDTOM;
import com.asignment3.asignment3.model.dto.map.PrescriptionDTOM;
import com.asignment3.asignment3.model.entity.*;
import com.asignment3.asignment3.model.mapper.AppointmentMapper;
import com.asignment3.asignment3.model.mapper.BankAccountMapper;
import com.asignment3.asignment3.model.mapper.DrugPrescriptionMapper;
import com.asignment3.asignment3.model.mapper.PrescriptionMapper;
import com.asignment3.asignment3.repository.*;
import com.asignment3.asignment3.service.PrescriptionService;
import com.asignment3.asignment3.util.Promotional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;
    private final DrugPrescriptionRepository drugPrescriptionRepository;
    private final DrugRepository drugRepository;
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;
    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository,
                                   PrescriptionMapper prescriptionMapper,
                                   DrugPrescriptionRepository drugPrescriptionRepository,
                                   DrugRepository drugRepository,
                                   BankAccountRepository bankAccountRepository,
                                   BankAccountMapper bankAccountMapper,
                                   AppointmentMapper appointmentMapper,
                                   AppointmentRepository appointmentRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionMapper = prescriptionMapper;
        this.drugPrescriptionRepository = drugPrescriptionRepository;
        this.drugRepository = drugRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.bankAccountMapper = bankAccountMapper;
        this.appointmentMapper = appointmentMapper;
        this.appointmentRepository = appointmentRepository;
    }


    @Override
    public PrescriptionDTO save(PrescriptionDTO prescriptionDTO) {
        Prescription prescription;

        if(prescriptionDTO.getId()!=null){
            prescription = prescriptionRepository.findById(prescriptionDTO.getId())
                            .orElseThrow(()->{throw new EntityNotFoundException("Cannot find prescription with ID:"+prescriptionDTO.getId());});
        }else{
            prescription = new Prescription();
            prescription.setDrugsPrescription(new ArrayList<>());
        }
        prescriptionMapper.toEntityUpdate(prescription,prescriptionDTO);
        return prescriptionMapper.toDTO(prescriptionRepository.save(prescription));

    }


    @Override
    public void delete(Long prescription_id) {
        Prescription prescription = prescriptionRepository.findById(prescription_id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find prescription with ID: " + prescription_id); } );


        prescriptionRepository.delete(prescription);
    }

    @Override
    public List<PrescriptionDTOM> findAllPrescriptions() {
        return prescriptionRepository.findAll()
                .stream()
                .map(prescriptionMapper::toDTOPrint)
                .collect(Collectors.toList());
    }

    @Override
    public boolean addDrug(Long prescription_id, Long drug_id, Integer quantity) {
        //daca nu mai e in stoc degeaba il adauga

        if(quantity==0) return false;

        Drug drug = drugRepository.findById(drug_id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find drug with ID: " + prescription_id); } );
        if(drug.getQuantity().equals(0)) return false;
        //pot pune aici Observer cu notify producator.

        Prescription prescription = prescriptionRepository.findById(prescription_id)
                .orElseThrow(() -> { throw new EntityNotFoundException("Cannot find prescription with ID: " + prescription_id); } );

        Optional<DrugPrescription> prescriptionOptional= prescription.getDrugsPrescription()
                                                .stream()
                                                .filter(drugPrescription -> drugPrescription.getDrug().getId().equals(drug_id))
                                                .findFirst();

        if(prescriptionOptional.isPresent()){
            //exista, ii adunam doar cantitatea
            DrugPrescription drugPrescription = prescriptionOptional.get();

            Integer exQuantity = drugPrescription.getQuantity();

            if(exQuantity + quantity > drug.getQuantity()) return false;
            drugPrescription.setQuantity(exQuantity + quantity);

            drugPrescriptionRepository.save(drugPrescription);
        }else{
            //daca nu este prezent, il adaugam
            DrugPrescription drugPrescription = new DrugPrescription();

            if(quantity > drug.getQuantity()) return false;

            drugPrescription.setDrug(drug);
            drugPrescription.setQuantity(quantity);
            drugPrescription.setPrescription(prescription);

            drugPrescriptionRepository.save(drugPrescription);
        }

        return true;
    }

    @Override
    public PrescriptionDTOM findByPrescriptionId(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                                    .orElseThrow(()->{throw new EntityNotFoundException("Cannot find prescription with ID:"+id);});

        return prescriptionMapper.toDTOPrint(prescription);
    }

    @Override
    public boolean transferDrugsToPatient(PrescriptionDTOM prescriptionDTO,
                                          AppointmentDTO appointmentDTO,
                                          boolean fromCard, String promotionalCode) {

        DoctorDTO doctorDTO=appointmentDTO.getDoctor();
        PatientDTO patientDTO=appointmentDTO.getPatient();

        List<AppointmentDTOM> apps=appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.getDoctor().getId().equals(doctorDTO.getId()))
                .map(appointmentMapper::toDTOPatient)
                .collect(Collectors.toList());

        List<DrugPrescriptionDTOM> drugs = prescriptionDTO.getDrugsPrescription();

        AtomicReference<Float> sum = new AtomicReference<>((float) 0);
        drugs.forEach(drug-> sum.updateAndGet(v -> (float) (v + (drug.getDrug().getPrice() * drug.getQuantity()))));

        Float total;
        if(apps.size()%5 == 0) total = sum.get();
        else total = sum.get() + doctorDTO.getConsultation_price();

        System.out.println(total);

        float promo = Promotional.getInstance().pricePromotion(promotionalCode);
        total -=promo;

        System.out.println(total);
        //aici vezi si de codul promotional

        BankAccount doctorBank = bankAccountRepository.findByUserId(doctorDTO.getUser().getId());
        BankAccount pharmacyBank =  bankAccountRepository.findByUserId(1L);

        BankAccountDTO doctorAccount = bankAccountMapper.toDTO(doctorBank) ;
        BankAccountDTO pharmacy =bankAccountMapper.toDTO(pharmacyBank);


        if(fromCard){
            BankAccount patientBank =  bankAccountRepository.findByUserId(patientDTO.getUser().getId());
            BankAccountDTO patientAccount =bankAccountMapper.toDTO(patientBank);

            if(patientBank==null) return false;

            if(patientAccount.getAmount() < total) {
                return false;
            }else{
                patientAccount.setAmount(patientAccount.getAmount() - total);
                doctorAccount.setAmount(doctorAccount.getAmount() + doctorDTO.getConsultation_price());
                pharmacy.setAmount(pharmacy.getAmount() + sum.get());

                bankAccountMapper.toEntityUpdate(patientBank,patientAccount);
                bankAccountRepository.save(patientBank);

            }
        }else{
            doctorAccount.setAmount(doctorAccount.getAmount() + doctorDTO.getConsultation_price());
            pharmacy.setAmount(pharmacy.getAmount() + sum.get());
        }

        //generare bon
        patientDTO.setMedical_history(patientDTO.getMedical_history() + "\n " +"consultation at " + doctorDTO.getSpeciality());

        bankAccountMapper.toEntityUpdate(doctorBank,doctorAccount);
        bankAccountRepository.save(doctorBank);
        bankAccountMapper.toEntityUpdate(pharmacyBank,pharmacy);
        bankAccountRepository.save(pharmacyBank);

        return true;
    }

    @Override
    public PrescriptionDTOM findByAppointmentId(Long app_id) {
        return prescriptionMapper.toDTOPrint(prescriptionRepository.findByAppointmentId(app_id));
    }


}
