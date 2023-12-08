package ru.vlsu.storage_kurs.contrlollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlsu.storage_kurs.entity.Batch;
import ru.vlsu.storage_kurs.entity.InventoryItem;
import ru.vlsu.storage_kurs.entity.Shipment;
import ru.vlsu.storage_kurs.entity.status.BATCH_STATUS;
import ru.vlsu.storage_kurs.entity.status.INVENTORY_ITEM_STATUS;
import ru.vlsu.storage_kurs.entity.status.SHIPMENT_STATUS;
import ru.vlsu.storage_kurs.service.InventoryItemService;
import ru.vlsu.storage_kurs.service.ShipmentService;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/shipments")

public class ShipmentController {
    private final ShipmentService shipmentService;
    private final InventoryItemService inventoryItemService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService, InventoryItemService inventoryItemService) {
        this.shipmentService = shipmentService;
        this.inventoryItemService = inventoryItemService;
    }
    @GetMapping("")
    public String showShipments(Model model) {
        model.addAttribute("shipments", shipmentService.getAllShipments());
        return "shipment/shipments";
    }

    @PostMapping("/changeStatus/{id}")
    public String changeBatchStatus(@PathVariable Long id, @RequestParam String newStatus) {
        if (SHIPMENT_STATUS.Выполнена.toString().equals(newStatus)){
            Shipment shipment = shipmentService.getShipmentById(id);
            shipment.setStatus(SHIPMENT_STATUS.Выполнена);
            shipmentService.editShipment(shipment);
        }
        return "redirect:/shipments/view/{id}";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Shipment shipment = shipmentService.getShipmentById(id);
        List<InventoryItem> inventoryItemList = inventoryItemService.getAllStoredInventoryItems();
        model.addAttribute("shipment", shipment);
        model.addAttribute("inventoryItems", inventoryItemList);
        model.addAttribute("shipmentStatusValues", SHIPMENT_STATUS.values());
        model.addAttribute("shipmentInventoryItems", shipment.getInventoryItems());


        return "shipment/edit-shipment";
    }

    @PostMapping("/edit")
    public String editShipment(@ModelAttribute Shipment editedShipment) {
        shipmentService.editShipment( editedShipment);
        return "redirect:/shipments/edit/"+editedShipment.getId();
    }

    @PostMapping("/addItem/{shipId}")
    public String editShipment(@PathVariable long shipId,@RequestParam("newItemId") Long newItemId) {

        Shipment shipmentById = shipmentService.getShipmentById(shipId);
        Optional<InventoryItem> inventoryItemById = inventoryItemService.getInventoryItemById(newItemId);
        if (inventoryItemById.isPresent()) {
            inventoryItemById.get().setShipment(shipmentById);
            inventoryItemById.get().setStatus(INVENTORY_ITEM_STATUS.SHIPPED);
            shipmentById.getInventoryItems().add(inventoryItemById.get());
        }
        shipmentService.editShipment(shipmentById);

        return "redirect:/shipments/edit/"+shipId;
    }

    @PostMapping("/new")
    public String createShipment(@ModelAttribute Shipment shipment) {
        shipment.setStatus(SHIPMENT_STATUS.Создана);
        shipmentService.createShipment(shipment);
        return "redirect:/shipments"; // Redirect to the form again or another appropriate page
    }
}
