package pro.sky.telegram_chat_zooShelter.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegram_chat_zooShelter.model.PhotoPet;
import pro.sky.telegram_chat_zooShelter.model.Report;
import pro.sky.telegram_chat_zooShelter.services.ReportService;

import java.util.List;

@RestController
@RequestMapping("/report")
@Tag(name = "\uD83D\uDCCB Report store")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Operation(
            tags = "\uD83D\uDCCB Report store",
            summary = "Искать все отчеты",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденные отчеты",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = List.class)
                            )
                    ),

            }
    )
    @GetMapping
    public ResponseEntity<List<Report>> findAllReportFindAllReport() {
        return ResponseEntity.ok(reportService.findAllReports());
    }

    @Operation(
            tags = "\uD83D\uDCCB Report store",
            summary = "Поиск отчета по его идентификатору в базе данных.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Сообщить в формате JSON",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Report[].class)
                            )
                    )
            })
    @GetMapping("/{id}")
    public ResponseEntity<Report> findReportById(@PathVariable Long id) {
        Report report = reportService.findReportById(id);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }

    @Operation(
            tags = "\uD83D\uDCCB Report store",
            summary = "Удалить отчет по его идентификатору в базе данных.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Сообщить в формате JSON",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Report[].class)
                            )
                    )
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Report> deleteReport(@PathVariable Long id) {
        Report report = reportService.deleteReport(id);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }

    @Operation(
            tags = "\uD83D\uDCCB Report store",
            summary = "Обновить отчет по его идентификатору в базе данных.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Обновить JSON",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Report[].class)
                            )
                    )
            })
    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@RequestBody Report report) {
        Report found = reportService.updateReport(report);
        if (found == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(found);
    }

    @Operation(
            tags = "\uD83D\uDCCB Report store",
            summary = "Создать отчет в базе данных.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Создать JSON",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Report.class)
                            )
                    )
}
    )
@PostMapping
public ResponseEntity<Report> createReport(@RequestBody Report report) {
        Report created = reportService.createReport(report);
        return ResponseEntity.ok(created);
        }
    @Operation(
            tags = "\uD83D\uDCCB Report store",
            summary = "Search for all photos by report id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found reports",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = List.class)
                            )
                    ),

            }
    )
    @GetMapping("{id}/photos")
    public ResponseEntity<List<PhotoPet>> getPhotosByReportId(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getAllPhotoByReportId(id));
    }

}
