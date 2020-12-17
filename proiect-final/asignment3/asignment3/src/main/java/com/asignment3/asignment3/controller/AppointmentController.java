package com.asignment3.asignment3.controller;

import com.asignment3.asignment3.model.dto.AppointmentDTO;
import com.asignment3.asignment3.model.dto.map.AppointmentDTOM;
import com.asignment3.asignment3.service.AppointmentService;
import com.asignment3.asignment3.validare.AppointmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( value = "/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentValidator appointmentValidator;

    @Autowired
    public AppointmentController(AppointmentService appointmentService,
                                 AppointmentValidator appointmentValidator) {
        this.appointmentService = appointmentService;
        this.appointmentValidator=appointmentValidator;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody @Valid AppointmentDTO appointmentDTO, BindingResult result) {
        System.out.println(appointmentDTO);

        appointmentValidator.validateAppointment(appointmentDTO, result);

        List<AppointmentDTOM> list = appointmentService.findAllAppointmentsForADoctor(appointmentDTO.getDoctor().getId());
        Optional<AppointmentDTOM> l=list.stream().filter(app->app.getDay()==appointmentDTO.getDay()
                            && app.getMonth()==appointmentDTO.getMonth()
                            && app.getYear()==appointmentDTO.getYear()
                            && (Integer.parseInt(app.getHour()) - Integer.parseInt(appointmentDTO.getHour()))<1)
                .findFirst();

        if(l.isPresent()) return null;
        if(result.hasErrors()) {
            return new ResponseEntity<Object>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        AppointmentDTO save = appointmentService.save(appointmentDTO);
        return ResponseEntity.ok(save);
    }

    @RequestMapping(value = "/listByDoctorId", method = RequestMethod.GET)
    public List<AppointmentDTOM> printAllAppointmentsForDoctor(@RequestParam Long id){
        return appointmentService.findAllAppointmentsForADoctor(id);
    }

    @RequestMapping(value = "/listByPatientId", method = RequestMethod.GET)
    public List<AppointmentDTOM> printAllAppointmentsForPatient(@RequestParam Long id){
        return appointmentService.findAllAppointmentsForAPatient(id);
    }

    @GetMapping(value = "/all")
    public List<AppointmentDTO> printAll(){
        return appointmentService.findAll();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete( @RequestParam Long id) {
        appointmentService.delete(id);
    }

    @GetMapping(value = "/find")
    public AppointmentDTO findById(@RequestParam Long id){
        return appointmentService.findById(id);
    }

}
