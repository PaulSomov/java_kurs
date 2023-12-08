package ru.vlsu.storage_kurs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vlsu.storage_kurs.entity.Shipment;
import ru.vlsu.storage_kurs.repo.ShipmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public void editShipment( Shipment editedShipment){
       shipmentRepository.save(editedShipment);
    }
    public Shipment getShipmentById(Long id ){
       Optional<Shipment> shipmentOptional  = shipmentRepository.findById(id);
        if (shipmentOptional.isPresent()) {
            return shipmentOptional.get();
        }
        else return null;
    }
    public List<Shipment> getAllShipments(){
        return shipmentRepository.findAll();
    }
    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }
}
