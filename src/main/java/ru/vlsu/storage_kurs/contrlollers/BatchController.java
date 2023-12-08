package ru.vlsu.storage_kurs.contrlollers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlsu.storage_kurs.entity.Batch;
import ru.vlsu.storage_kurs.entity.BatchItem;
import ru.vlsu.storage_kurs.entity.status.BATCH_STATUS;
import ru.vlsu.storage_kurs.service.BatchService;
import ru.vlsu.storage_kurs.service.ProductService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/batches")
public class BatchController {
    private final BatchService batchService;
    private final ProductService productService;

    @Autowired
    public BatchController(BatchService batchService, ProductService productService) {
        this.batchService = batchService;
        this.productService = productService;
    }

    @GetMapping()
    public String listBatches(Model model) {
        List<Batch> batches = batchService.getAllBatches();
        model.addAttribute("batches", batches);
        return "batch/list";
    }

    @PostMapping("/changeStatus/{id}")
    public String changeBatchStatus(@PathVariable Long id, @RequestParam String newStatus) {
        if (BATCH_STATUS.Выполнена.toString().equals(newStatus)){
            Optional<Batch> opt = batchService.getBatchById(id);
            Batch batch = opt.get();
            log.info(batch.getBatchStatus() +" --");
            batchService.addItems(batch);
        }
        batchService.changeBatchStatus(id, newStatus);
        return "redirect:/batches/view/{id}";
    }


    @GetMapping("/view/{id}")
    public String viewBatchDetails(@PathVariable Long id, Model model) {
        Batch batch = batchService.getBatchById(id).orElse(null);
        List<BatchItem> batchItems = batch.getBatchItems();
        model.addAttribute("batch", batch);
        model.addAttribute("batchItems", batchItems);
        model.addAttribute("newBatchItem", new BatchItem());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("batchStatusValues", BATCH_STATUS.values());

        return "batch/view";
    }

    @PostMapping("/view/{id}/addBatchItem")
    public String addBatchItem(@PathVariable Long id, @ModelAttribute BatchItem newBatchItem) {
        batchService.addBatchItem(id, newBatchItem);
        return "redirect:/batches/view/{id}";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("batch", new Batch());
        return "batch/create";
    }

    @PostMapping("/create")
    public String createBatch(@ModelAttribute Batch batch) {
        log.info("batch to create " + batch.toString());
        batch.setBatchStatus(BATCH_STATUS.Создана);
        batchService.saveBatch(batch);
        return "redirect:/batches";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Batch batch = batchService.getBatchById(id).orElse(null);
        model.addAttribute("batch", batch);
        return "batch/edit";
    }

    @PostMapping("/edit/{id}")
    public String editBatch(@PathVariable Long id, @ModelAttribute Batch batch) {
        batch.setId(id);
        batchService.saveBatch(batch);
        return "redirect:/batches/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteBatch(@PathVariable Long id) {
        batchService.deleteBatch(id);
        return "redirect:/batches/list";
    }
}
