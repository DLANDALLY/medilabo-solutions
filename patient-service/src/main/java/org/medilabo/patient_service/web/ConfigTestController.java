package org.medilabo.patient_service.web;

import lombok.AllArgsConstructor;
import org.medilabo.patient_service.config.GloblaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConfigTestController {
//    @Value("${global.params.p1}")
//    private int p1;
//    @Value("${global.params.p2}")
//    private int p2;
//    @Value("${patient.params.x}")
//    private int x;
//    @Value("${patient.params.y}")
//    private int y;

    @Autowired
    private GloblaConfig globlaConfig;

//    @GetMapping("/testConfig")
//    public Map<String, Integer> configTest(){
//        return Map.of("p1", p1,"p2", p2,"x", x, "y", y);
//    }

    @GetMapping("/globalConfig")
    public GloblaConfig globlaConfig(){
        return globlaConfig;
    }
}
