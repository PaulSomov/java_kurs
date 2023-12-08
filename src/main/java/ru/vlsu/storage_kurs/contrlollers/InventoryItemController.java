package ru.vlsu.storage_kurs.contrlollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlsu.storage_kurs.entity.InventoryItem;
import ru.vlsu.storage_kurs.service.InventoryItemService;

@Controller
@RequestMapping("/inventoryItems")
public class InventoryItemController {
    @Autowired
    private InventoryItemService inventoryItemService;

    @GetMapping()
    public String listInventoryItems(Model model) {
        model.addAttribute("inventoryItems", inventoryItemService.getAllInventoryItems());
        return "inventoryItems/list";
    }
}
