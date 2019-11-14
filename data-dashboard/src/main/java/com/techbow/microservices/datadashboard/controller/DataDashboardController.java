package com.techbow.microservices.datadashboard.controller;

import com.techbow.microservices.common.model.dao.DataDao;
import com.techbow.microservices.common.model.dvo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController  //:== @Controller + @ResponseBody
@RequestMapping("api/v1")
public class DataDashboardController {

    @Autowired  // we achieve decoupling by DI
    private DataDao dataDao;

    @PostMapping("data")
    public Data createData(@RequestBody Data data) {
        return dataDao.save(data);
    }


    @Cacheable(value = "dataTechbowCache", key = "#id")  // "dataTechbowCache::10"
    @GetMapping("/data/{id}")
    public Data findDataById(@PathVariable Long id) {
        System.out.println("\nCalling getDataById from DAO for data: " + id + "\n");
        return dataDao.findById(id);
    }

    @CachePut(value = "dataTechbowCache", key = "#id")
    @PutMapping("/data/{id}")
    public Data updateDataById(@PathVariable Long id, @RequestBody Data data) {
        Data res = dataDao.findById(id);
        if (res == null) {
            // throw new RuntimeException("id not exist");
            return null;
        } else {
            res.setClientId(data.getClientId());
            res.setStepCount(data.getStepCount());
            res.setTemperature(data.getTemperature());
            dataDao.save(res);
            return res;
        }
    }

    @GetMapping("/data")
    public List<Data> getAllData(@RequestParam(name = "limit", required = false) String limit) {
        return dataDao.findAll(limit);
    }

    // query by data field (step count), value range, sort
    @GetMapping("/data/client/{clientId}")
    public List<Data> findDataByClientId(
            @PathVariable Long clientId,
            @RequestParam(name = "field", required = false) String field,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "start", required = false) String start,
            @RequestParam(name = "end", required = false) String end) {

        return dataDao.findByClientId(clientId, field, sort, start, end);
    }
}
