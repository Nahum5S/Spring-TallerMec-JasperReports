package com.proyecto.estadia.Car.Controller;

import com.proyecto.estadia.Car.Model.Car;
import com.proyecto.estadia.Car.Model.CarRepository;
import com.proyecto.estadia.Utils.Message;
import com.sun.istack.NotNull;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service
@Transactional
public class CarService {

    @Autowired
    CarRepository carRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll(){
        return new ResponseEntity<>(new Message("ok", false, carRepository.findAll()), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(long id){
        if(carRepository.existsById(id)){
            return new ResponseEntity<>(new Message("Encontrado", false, carRepository.findById(id)), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Message("No encontrado", true, null), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findByUser_Id(long id){
        if(carRepository.existsById(id)){
            return new ResponseEntity<>(new Message("Encontrado", false, carRepository.findByUser_Id(id)), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new Message("No encontrado", true, null), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Car car) {
        Optional<Car> existCar = carRepository.findByCarPLates(car.getCarPLates());
        if (existCar.isPresent()) {
            return new ResponseEntity<>(new Message("El auto ya existe", true, null), HttpStatus.BAD_REQUEST);
        }
        Car saveCar = carRepository.saveAndFlush(car);
        return new ResponseEntity<>(new Message("Auto registrado correctamente", false, saveCar), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> updateCar(Car car) {
        if (carRepository.existsById(car.getId())) {
            return new ResponseEntity<>(new Message("Actualizado correctamente", false, carRepository.saveAndFlush(car)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message("No se encontro", true, null), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> updateStateCar(Car car) {
        if (carRepository.existsById(car.getId())) {
            return new ResponseEntity<>(new Message("Actualizado correctamente", false, carRepository.saveAndFlush(car)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Message("No se encontro", true, null), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public void delete(long id){
        carRepository.deleteAllByUserId(id);
    }

    @Transactional(rollbackFor = {SQLException.class})
    @NotNull
    public ResponseEntity<Resource> exportInvoice(long idUser, long id){
        Optional<Car> otpPedido = this.carRepository.findByIdAndUserId(idUser, id);
        Double rpta = this.carRepository.totalByIdCar(idUser, id);
        if(otpPedido.isPresent()){

            try{
                final Car car = otpPedido.get();
                final File file = ResourceUtils.getFile("classpath:exportInvoice.jasper");
                final File imgLogo = ResourceUtils.getFile("classpath:images/logo.svg");
                final JasperReport report =  (JasperReport) JRLoader.loadObject(file);
                final HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("nombreCliente" ,car.getUser().getNombreCompleto());
                parameters.put("imgLogo", new FileInputStream(imgLogo));
                parameters.put("price", rpta);
                parameters.put("dsInvoice", new JRBeanCollectionDataSource((Collection<?>) this.carRepository.findById(id, idUser)));

                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
                byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
                String sdf = (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
                StringBuilder stringBuilder =  new StringBuilder().append("InvoicePDF:");
                ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                        .filename(stringBuilder.append(car.getId())
                                .append("generateDate:")
                                .append(sdf)
                                .append(".pdf")
                                .toString())
                        .build();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentDisposition(contentDisposition);
                return ResponseEntity.ok().contentLength(reporte.length)
                        .contentType(MediaType.APPLICATION_PDF)
                        .headers(headers).body(new ByteArrayResource(reporte));
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            return ResponseEntity.noContent().build(); //No se encontro el reporte
        }
        return null;
    }

}
