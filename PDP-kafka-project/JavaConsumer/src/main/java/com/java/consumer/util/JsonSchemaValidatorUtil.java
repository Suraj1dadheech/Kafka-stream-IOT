package com.java.consumer.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.consumer.service.ConsumerInvokerService;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class JsonSchemaValidatorUtil {

    @Autowired
    ObjectMapper objectMapper;

    Logger logger = LoggerFactory.getLogger(JsonSchemaValidatorUtil.class);

    public boolean validateJson(Object inputObject, String schemaName) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        String schema = getJSonSchema(schemaName);
        com.networknt.schema.JsonSchema jsonSchema = factory.getSchema(schema);
        JsonNode inputObjectNode = objectMapper.valueToTree(inputObject);
        Set<ValidationMessage> errors = jsonSchema.validate(inputObjectNode);
        if(!errors.isEmpty()){
            logger.error(errors.toString());
            return false;
        }else{
            logger.info("Document is valid.");
        }
        return true;
    }

    private String getJSonSchema(String schemaName) {
        if(schemaName.equals("HEALTH")){
            return """
                    {
                      "$schema": "http://json-schema.org/draft-07/schema#",
                      "type": "object",
                      "properties": {
                        "uuid": {
                          "type": "string",
                          "minLength": 1
                        },
                        "heartRate": {
                          "type": "integer",
                          "minimum": 0
                        },
                        "spo2": {
                          "type": "integer",
                          "minimum": 0
                        },
                        "sleepDuration": {
                          "type": "integer",
                          "minimum": 0
                        },
                        "caloriesBurned": {
                          "type": "integer",
                          "minimum": 0
                        },
                        "stepsTaken": {
                          "type": "integer",
                          "minimum": 0
                        }
                      },
                      "required": ["uuid", "heartRate", "spo2"]
                    }
                                        
                    """;
        }else{
            return null;
        }
    }

}
