package com.example.demo.app.controllers;

import com.example.demo.app.entities.Client;
import com.example.demo.app.services.ClientService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

// @Controller используется для аннотирования
// классов реализации контроллеров.
@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping("/list")
    public String clientsList(Model model) {
        List<Client> clients = service.getClients();
        model.addAttribute("clients", clients);
        return "clients-list"; // Имя представления
    }

    @GetMapping("/showForm")
    public String showFormToAdd(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "client-form";
    }

    @PostMapping("/saveClient")
    public String saveClient(@ModelAttribute("client") Client client) {
        service.saveClient(client);
        return "redirect:/clients/list";
    }

    @GetMapping("/updateForm")
    public String showUpdateForm(@RequestParam("clientId") int id, Model model) {
        Client client = service.getClient(id);
        model.addAttribute("client", client);
        return "client-update-form"; // Имя представления
    }

    @PostMapping("/update")
    public String updateClient(@ModelAttribute("client") Client client) {
        service.saveClient(client); // Перезаписываем клиента с обновленными данными
        return "redirect:/clients/list"; // Перенаправление на список клиентов
    }

    @GetMapping("/deleteLink/{id}")
    public String deleteClient(@PathVariable("id") int id) {
        // Здесь вам нужно удалить клиента по id
        service.deleteClient(id);
        return "redirect:/clients/list"; // Перенаправление на список клиентов
    }


    // Загрузка данных JSON
    @PostMapping("/importJson")
    public String importJsonData(@RequestParam("jsonFile") MultipartFile jsonFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Client> clients = objectMapper.readValue(jsonFile.getInputStream(), new TypeReference<List<Client>>() {
            });

            for (Client client : clients) {
                service.saveClient(client); // Сохраняет или обновляет клиента в базе данных
            }

            return "redirect:/clients/list";
        } catch (Exception e) {
            // Обработка ошибок
            return "redirect:/clients/list?error=Failed to import data: " + e.getMessage();
        }
    }

    // Выгрузка данных JSON
    @GetMapping("/exportJson")
    public ResponseEntity<Resource> exportJsonData() {
        List<Client> clients = service.getClients();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(clients);

            ByteArrayResource resource = new ByteArrayResource(json.getBytes());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clients.json");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ByteArrayResource(("Failed to export data: " + e.getMessage()).getBytes()));
        }
    }
}



