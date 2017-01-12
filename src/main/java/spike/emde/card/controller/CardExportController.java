package spike.emde.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping(value = "/cards/export/local")
    private ResponseEntity exportCardsExcelToLocal (@MatrixVariable Map<String, String> varMap) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
