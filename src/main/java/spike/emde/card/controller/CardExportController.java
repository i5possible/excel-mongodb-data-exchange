package spike.emde.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spike.emde.card.exception.CannotWriteToWorkbookException;
import spike.emde.card.service.CardExportService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
public class CardExportController {
    @Autowired
    CardExportService cardExportService;

    @Value(value = "export.excel.suffix")
    String suffix;

    @Value(value = "export.home")
    String homePath;

    @GetMapping(value = "cards/{cardId}/export",
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity exportCardsExcel(@PathVariable(value = "cardId") String cardId) {
        Map<String, String> filterMap = new HashMap();
        filterMap.put("cardId", cardId);
        filterMap.put("fileName", "testFile");
        return getExportCardsResponseEntity(filterMap);
    }

    @GetMapping(value = "cards/export",
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity getCardsExcelBySize(@MatrixVariable Map<String, String> filterMap) {
        return getExportCardsResponseEntity(filterMap);
    }


    private ResponseEntity getExportCardsResponseEntity(Map<String, String> filterMap) {
        Optional<Resource> resource;
        try {
            resource = cardExportService.exportCardsExcel(filterMap);
        } catch (CannotWriteToWorkbookException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Cannot write to workbook!");
        }
        return resource.map(this::buildExportResponse).orElse(ResponseEntity.notFound().build());
    }

    private ResponseEntity buildExportResponse(Resource resource) {
        String fileName = resource.getFilename();
        fileName = (fileName == null) ? resource.getDescription() : fileName;
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Disposition",
                        "attachment; filename=\"" + fileName + suffix + "\"")
                .body(resource);
    }

    @GetMapping(value = "/cards/export/local/{cardId}")
    private ResponseEntity exportCardsExcelToLocal (@PathVariable(value = "cardId") String cardId,
                                                    @RequestParam(value = "fileName") String fileName) {
        Map<String, String> map= new HashMap<>();
        map.put("cardId",cardId);
        map.put("fileName",fileName);
        Runnable runnable = ()-> cardExportService.exportCardsExcelToLocal(map);
        runnable.run();
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
