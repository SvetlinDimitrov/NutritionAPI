package com.example.nutritionapi.service;

import com.example.nutritionapi.domain.constants.entity.Electrolyte;
import com.example.nutritionapi.domain.constants.entity.Pair;
import com.example.nutritionapi.domain.dtos.viewDtos.ElectrolyteView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ElectrolyteServiceImpTest {

    @Mock
    private  Map<String, Electrolyte> electrolyteEntityMap;
    @InjectMocks
    private ElectrolyteServiceImp electrolyteServiceImp;

    private final List<Electrolyte> electrolyteList = new ArrayList<>();
    private final List<ElectrolyteView> electrolyteViewsList = new ArrayList<>();

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {

        Field field = ElectrolyteServiceImp.class.getDeclaredField("electrolyteEntityMap");
        field.setAccessible(true);
        field.set(electrolyteServiceImp, electrolyteEntityMap);


        electrolyteList.addAll(
                List.of(
                        new Electrolyte()
                                .setName("Sodium1")
                                .setDescription("Sodium is a vital mineral and electrolyte that plays a central role in maintaining various physiological functions in the body. It's essential for regulating fluid balance, nerve transmission, muscle function, and blood pressure. While sodium is important for health, excessive intake can lead to health issues like high blood pressure and increased risk of heart disease.")
                                .setFunctions(List.of(
                                        new Pair()
                                                .setKey("Fluid Balance")
                                                .setValue("Sodium helps regulate the balance of fluids inside and outside cells. It's a major contributor to osmotic pressure, which determines the movement of water between cells and their surroundings."),
                                        new Pair()
                                                .setKey("Nerve Transmission")
                                                .setValue("Sodium is essential for generating electrical signals that allow nerve cells to communicate with each other and with muscles. This is critical for muscle contraction, sensation, and other nervous system functions."),
                                        new Pair()
                                                .setKey("Muscle Contraction")
                                                .setValue("Sodium is involved in muscle contraction, including the contractions of skeletal muscles and the heart."),
                                        new Pair()
                                                .setKey("Blood Pressure Regulation")
                                                .setValue("Sodium levels influence blood volume and blood pressure. Excess sodium intake can cause the body to retain more water, leading to increased blood volume and higher blood pressure.")))
                                .setSources(List.of(
                                        new Pair()
                                                .setKey("Table salt")
                                                .setValue("sodium chloride"),
                                        new Pair()
                                                .setKey("Processed and packaged foods")
                                                .setValue("canned soups, snacks, sauces"),
                                        new Pair()
                                                .setKey("-")
                                                .setValue("Fast food and restaurant meals"),
                                        new Pair()
                                                .setKey("Some natural foods")
                                                .setValue("dairy products, meat, seafood")
                                ))
                                .setHealthConsiderations(List.of(
                                        new Pair()
                                                .setKey("High Blood Pressure")
                                                .setValue("Consuming too much sodium can lead to increased blood pressure, a major risk factor for heart disease and stroke."),
                                        new Pair()
                                                .setKey("Cardiovascular Health")
                                                .setValue("High sodium intake is associated with an increased risk of heart disease and stroke."),
                                        new Pair()
                                                .setKey("Fluid Retention")
                                                .setValue("Excess sodium can lead to water retention, causing swelling and bloating.")))
                                .setFemaleHigherBoundIntake(new BigDecimal("2300"))
                                .setFemaleLowerBoundIntake(new BigDecimal("2300"))
                                .setMaleHigherBoundIntake(new BigDecimal("2300"))
                                .setMaleLowerBoundIntake(new BigDecimal("2300"))
                                .setMeasure("milligrams (mg)"),
                       new Electrolyte()
                                .setName("Sodium2")
                                .setDescription("Sodium is a vital mineral and electrolyte that plays a central role in maintaining various physiological functions in the body. It's essential for regulating fluid balance, nerve transmission, muscle function, and blood pressure. While sodium is important for health, excessive intake can lead to health issues like high blood pressure and increased risk of heart disease.")
                                .setFunctions(List.of(
                                        new Pair()
                                                .setKey("Fluid Balance")
                                                .setValue("Sodium helps regulate the balance of fluids inside and outside cells. It's a major contributor to osmotic pressure, which determines the movement of water between cells and their surroundings."),
                                        new Pair()
                                                .setKey("Nerve Transmission")
                                                .setValue("Sodium is essential for generating electrical signals that allow nerve cells to communicate with each other and with muscles. This is critical for muscle contraction, sensation, and other nervous system functions."),
                                        new Pair()
                                                .setKey("Muscle Contraction")
                                                .setValue("Sodium is involved in muscle contraction, including the contractions of skeletal muscles and the heart."),
                                        new Pair()
                                                .setKey("Blood Pressure Regulation")
                                                .setValue("Sodium levels influence blood volume and blood pressure. Excess sodium intake can cause the body to retain more water, leading to increased blood volume and higher blood pressure.")))
                                .setSources(List.of(
                                        new Pair()
                                                .setKey("Table salt")
                                                .setValue("sodium chloride"),
                                        new Pair()
                                                .setKey("Processed and packaged foods")
                                                .setValue("canned soups, snacks, sauces"),
                                        new Pair()
                                                .setKey("-")
                                                .setValue("Fast food and restaurant meals"),
                                        new Pair()
                                                .setKey("Some natural foods")
                                                .setValue("dairy products, meat, seafood")
                                ))
                                .setHealthConsiderations(List.of(
                                        new Pair()
                                                .setKey("High Blood Pressure")
                                                .setValue("Consuming too much sodium can lead to increased blood pressure, a major risk factor for heart disease and stroke."),
                                        new Pair()
                                                .setKey("Cardiovascular Health")
                                                .setValue("High sodium intake is associated with an increased risk of heart disease and stroke."),
                                        new Pair()
                                                .setKey("Fluid Retention")
                                                .setValue("Excess sodium can lead to water retention, causing swelling and bloating.")))
                                .setFemaleHigherBoundIntake(new BigDecimal("2300"))
                                .setFemaleLowerBoundIntake(new BigDecimal("2300"))
                                .setMaleHigherBoundIntake(new BigDecimal("2300"))
                                .setMaleLowerBoundIntake(new BigDecimal("2300"))
                                .setMeasure("milligrams (mg)")
                )
        );
        electrolyteViewsList.addAll(
                List.of(
                        new ElectrolyteView(electrolyteList.get(0)),
                        new ElectrolyteView(electrolyteList.get(1))
                )
        );
    }

    @Test
    void getAllViewElectrolytes_convertingToElectrolyteView_Successful() {
        when(electrolyteEntityMap.values()).thenReturn(electrolyteList);

        List<ElectrolyteView> result = electrolyteServiceImp.getAllViewElectrolytes();

        assertEquals(electrolyteViewsList , result);

        verify(electrolyteEntityMap, times(1)).values();
    }

    @Test
    void getElectrolyteViewByName() {

    }

    @Test
    void getAllElectrolytesNames() {
    }
}