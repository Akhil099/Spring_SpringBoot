package com.in28minutes.rest.webservices.restful_web_services.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @GetMapping("/filtering")
    public MappingJacksonValue filtering(){
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        return getMappingJacksonValue(new MappingJacksonValue(someBean), SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3"));
    }
    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList(){

        List<SomeBean> someBeansList = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value4", "value5", "value6"));
        List<String> fieldsIncluded = Arrays.asList("field2", "field3");
        return getMappingJacksonValue(new MappingJacksonValue(someBeansList), SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3"));
    }

    private static MappingJacksonValue getMappingJacksonValue(MappingJacksonValue someBeansList, SimpleBeanPropertyFilter filterList) {
        MappingJacksonValue mappingJacksonValue = someBeansList;
        FilterProvider filtersList = new SimpleFilterProvider().addFilter("SomeBeanFilter", filterList);
        mappingJacksonValue.setFilters(filtersList);
        return mappingJacksonValue;
    }
}
